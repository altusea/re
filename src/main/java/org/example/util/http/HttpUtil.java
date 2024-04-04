package org.example.util.http;

import org.apache.commons.collections4.MapUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

public class HttpUtil {

    private static final PoolingHttpClientConnectionManager connManager;

    private static final CloseableHttpClient httpClient;

    static {
        connManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(SSLContexts.createSystemDefault())
                        .setTlsVersions(TLS.V_1_3)
                        .build())
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMinutes(1))
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();
        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(StandardCookieSpec.IGNORE)
                        .build())
                .build();
    }

    public static ClassicRequestBuilder basicBuilder(Method method, String url) {
        ClassicRequestBuilder builder = ClassicRequestBuilder.create(method.name());
        builder.setUri(url);
        return builder;
    }

    private static String executeRequest(ClassicHttpRequest request) {

        try {
            return httpClient.execute(request, response -> {
                if (response.getCode() >= 300) {
                    throw new ClientProtocolException(new StatusLine(response).toString());
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                return EntityUtils.toString(responseEntity);
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> queries) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.GET, url);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }
        if (MapUtils.isNotEmpty(queries)) {
            queries.forEach(requestBuilder::addParameter);
        }

        return executeRequest(requestBuilder.build());
    }

    public static String doPostWithProxy(String url, Map<String, String> headers, String body) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.POST, url);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }
        requestBuilder.setEntity(body, ContentType.APPLICATION_JSON);

        return executeRequest(requestBuilder.build());
    }
}
