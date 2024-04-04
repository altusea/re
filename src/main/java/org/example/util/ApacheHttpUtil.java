package org.example.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
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

public class ApacheHttpUtil {

    private static final PoolingHttpClientConnectionManager CONN_MANAGER;

    private static final HttpClient HTTP_CLIENT;

    static {
        CONN_MANAGER = PoolingHttpClientConnectionManagerBuilder.create()
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
        HTTP_CLIENT = HttpClients.custom()
                .setConnectionManager(CONN_MANAGER)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(StandardCookieSpec.IGNORE)
                        .build())
                .build();
    }

    private static String executeRequest(ClassicHttpRequest request) {

        try {
            return HTTP_CLIENT.execute(request, response -> {
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

    public static ClassicRequestBuilder basicBuilder(Method method, String url, Map<String, String> headers) {
        ClassicRequestBuilder requestBuilder = ClassicRequestBuilder.create(method.name());
        requestBuilder.setUri(url);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }
        return requestBuilder;
    }

    public static <R> R getForEntity(String url, Map<String, String> headers, Map<String, String> queries, Class<R> responseType) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.GET, url, headers);
        if (MapUtils.isNotEmpty(queries)) {
            queries.forEach(requestBuilder::addParameter);
        }

        String bodyStr = executeRequest(requestBuilder.build());
        return JacksonUtil.fromJson(bodyStr, responseType);
    }

    public static <R> R postForEntity(String url, Map<String, String> headers, Object obj, Class<R> responseType) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.POST, url, headers);
        requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        requestBuilder.setEntity(new StringEntity(JacksonUtil.toJson(obj), ContentType.APPLICATION_JSON));

        String bodyStr = executeRequest(requestBuilder.build());
        return JacksonUtil.fromJson(bodyStr, responseType);
    }
}
