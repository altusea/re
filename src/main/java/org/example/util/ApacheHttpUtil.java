package org.example.util;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

public class ApacheHttpUtil {

    static class ResponseHandler implements HttpClientResponseHandler<String> {

        static final HttpClientResponseHandler<String> INSTANCE = new ResponseHandler();

        @Override
        public String handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
            var statusCode = response.getCode();
            var entity = response.getEntity();
            if (statusCode >= 300) {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusCode, response.getReasonPhrase());
            }
            return EntityUtils.toString(entity);
        }
    }

    private static final HttpClient HTTP_CLIENT;

    static {
        HTTP_CLIENT = HttpClientBuilder.create().build();
    }

    public static <R> R getForEntity(String url, Class<R> responseType) {
        ClassicHttpRequest httpGet = ClassicRequestBuilder
                .get(url)
                .build();
        try {
            String bodyStr = HTTP_CLIENT.execute(httpGet, ResponseHandler.INSTANCE);
            return JacksonUtil.fromJson(bodyStr, responseType);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred ...", e);
        }
    }

    public static <R> R postForEntity(String url, Object obj, Class<R> responseType) {
        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .post(url)
                .setHeader("Content-Type", "application/json")
                .setEntity(JacksonUtil.toJson(obj))
                .build();
        try {
            String bodyStr = HTTP_CLIENT.execute(httpPost, ResponseHandler.INSTANCE);
            return JacksonUtil.fromJson(bodyStr, responseType);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred ...", e);
        }
    }
}
