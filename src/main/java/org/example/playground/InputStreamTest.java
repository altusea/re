package org.example.playground;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.google.common.io.ByteSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class InputStreamTest {

    public static void main(String[] args) throws IOException {

        byte[] bytes = new byte[10];
        try (InputStream inputStream = ByteSource.wrap(bytes).openStream()) {
            var imageMap = Collections.singletonMap("xxx", inputStream);
            MailUtil.send(new MailAccount(), Collections.singletonList("xxx"), "xxx", "xxx", imageMap, true);
        }
    }
}
