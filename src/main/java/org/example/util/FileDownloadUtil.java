package org.example.util;

import feign.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class FileDownloadUtil {

    public static void convert(Response feignResponse, HttpServletResponse httpServletResponse) throws IOException {
        Object contentDis = feignResponse.headers().get("Content-Disposition");
        if (contentDis instanceof List) {
            httpServletResponse.setHeader("Content-Disposition", ((List<?>) contentDis).get(0).toString());
        } else {
            httpServletResponse.setHeader("Content-Disposition", contentDis.toString());
        }
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(feignResponse.body().asInputStream(), byteArrayOutputStream);
        httpServletResponse.getOutputStream().write(byteArrayOutputStream.toByteArray());
    }
}
