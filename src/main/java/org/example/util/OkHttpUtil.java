package org.example.util;

import cn.hutool.core.net.url.UrlBuilder;
import dev.failsafe.RetryPolicy;
import dev.failsafe.okhttp.FailsafeCall;
import okhttp3.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    public static final MediaType JSON = MediaType.get("application/json");

    private static final OkHttpClient defaultClient;

    static {
        defaultClient = new OkHttpClient.Builder()
                .connectTimeout(50L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .build();
    }

    public static <R> R postFormForEntity(String url, List<Map.Entry<String, String>> formData, Class<R> responseType) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (CollectionUtils.isNotEmpty(formData)) {
            formData.forEach(e -> bodyBuilder.add(e.getKey(), e.getValue()));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(bodyBuilder.build())
                .build();
        return exec(request, responseType);
    }

    public static <R> R postForEntity(String url, Object obj, Class<R> responseType) {
        return postForEntity(url, obj, responseType, null);
    }

    public static <R> R postForEntity(String url, Object obj, Class<R> responseType, Proxy proxy) {
        RequestBody requestBody = RequestBody.create(GsonUtil.toJson(obj), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        if (proxy == null) {
            return exec(request, responseType);
        }
        OkHttpClient proxyHttpClient = defaultClient.newBuilder()
                .proxy(proxy)
                .build();
        return exec(request, responseType, proxyHttpClient);
    }

    public static <R> R getForEntity(String url, Map<String, String> queries, Class<R> responseType) {
        UrlBuilder urlBuilder = UrlBuilder.of(url);
        if (MapUtils.isNotEmpty(queries)) {
            queries.forEach(urlBuilder::addQuery);
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
        return exec(request, responseType);
    }

    private static <R> R exec(Request request, Class<R> responseType) {
        return exec(request, responseType, defaultClient);
    }

    private static <R> R exec(Request request, Class<R> responseType, OkHttpClient httpClient) {
        Call call = httpClient.newCall(request);
        RetryPolicy<Response> retryPolicy = RetryPolicy.ofDefaults();
        FailsafeCall failsafeCall = FailsafeCall.with(retryPolicy).compose(call);

        try (Response response = failsafeCall.execute()) {
            var responseBody = response.body();
            if (responseBody == null) {
                return null;
            }
            return GsonUtil.fromJson(responseBody.string(), responseType);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred ...");
        }
    }
}
