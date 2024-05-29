package org.example.util.http;

import org.apache.commons.collections4.MapUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.example.util.JacksonUtil;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class ApacheHttpUtil {

    private static final PoolingHttpClientConnectionManager CONN_MANAGER;

    private static final HttpClient HTTP_CLIENT;

    private static final HttpClientResponseHandler<String> DEFAULT_RESPONSE_HANDLER = new BasicHttpClientResponseHandler();

    static {
        CONN_MANAGER = PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(new DefaultClientTlsStrategy(
                        SSLContexts.createSystemDefault(),
                        NoopHostnameVerifier.INSTANCE))
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
            return HTTP_CLIENT.execute(request, DEFAULT_RESPONSE_HANDLER);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String executeRequestWithProxy(ClassicHttpRequest request, HttpHost proxy) {
        try {
            return HTTP_CLIENT.execute(proxy, request, DEFAULT_RESPONSE_HANDLER);
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

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet);
    }

    public static String getWithProxy(String url, HttpHost proxy) {
        HttpGet httpGet = new HttpGet(url);
        return executeRequestWithProxy(httpGet, proxy);
    }

    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        System.out.println(get(url));

        printSeparateLine();
        HttpHost proxy = new HttpHost("127.0.0.1", 1234);
        System.out.println(getWithProxy(url, proxy));
    }
}
