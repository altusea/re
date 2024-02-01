package org.example.util;

import com.google.common.util.concurrent.Runnables;
import com.machinezoo.noexception.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class ExceptionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);

    public static void throwCheckException() throws IOException {
        throw new SocketTimeoutException();
    }

    public static void main(String[] args) {
        new Thread(Runnables.doNothing()).start();

        LOGGER.info("test log ...");
        Exceptions.sneak().run(ExceptionTest::throwCheckException);
    }
}
