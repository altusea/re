package org.example.playground;

import org.apache.hc.client5.http.ConnectTimeoutException;

import java.net.SocketTimeoutException;

public class ExceptionTest {

    public static void main(String[] args) {
        System.out.println(SocketTimeoutException.class.isAssignableFrom(ConnectTimeoutException.class));
        System.out.println(ConnectTimeoutException.class.isAssignableFrom(SocketTimeoutException.class));
    }
}
