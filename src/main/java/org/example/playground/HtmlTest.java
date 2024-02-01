package org.example.playground;

import static j2html.TagCreator.*;

public class HtmlTest {

    public static void main(String[] args) {
        String pageTitle = "Centered HTML Page";
        String pageText = "Hello, World!";
        String lang = "zh-CN";

        String html = html(
                head(
                        meta().withCharset("UTF-8"),
                        title(pageTitle)
                ),
                body(
                        div(
                                h1(pageText).withStyle("text-align: center;")
                        ).withStyle("display: flex; justify-content: center; align-items: center; height: 100vh;")
                )
        ).withLang(lang).renderFormatted();

        String finalHtml = "<!DOCTYPE html>\n" + html; // 添加 <!DOCTYPE html>
        System.out.println(finalHtml);
    }
}
