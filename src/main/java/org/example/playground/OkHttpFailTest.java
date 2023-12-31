package org.example.playground;

import dev.failsafe.Fallback;
import dev.failsafe.RetryPolicy;
import dev.failsafe.okhttp.FailsafeCall;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpFailTest {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().build();

        Call call = client.newCall(request);
        RetryPolicy<Response> defaultRetryPolicy = RetryPolicy.ofDefaults();
        FailsafeCall failsafeCall = FailsafeCall
                .with(Fallback.of(() -> new Response.Builder().build()), defaultRetryPolicy)
                .compose(call);

        // Execute with retries
        try (Response response = failsafeCall.execute()) {
            if (response.body() != null) {
                System.out.println(response.body().contentType());
            }
        } catch (IOException e) {
            System.out.println("failed ...");
        }
    }
}
