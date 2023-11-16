package org.example.util;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    private static final int QR_CODE_WIDTH = 200;
    private static final int QR_CODE_HEIGHT = 200;

    public static BufferedImage genQrCode(String content) {
        QrConfig config = QrConfig.create()
                .setWidth(QR_CODE_WIDTH)
                .setHeight(QR_CODE_HEIGHT)
                .setErrorCorrection(ErrorCorrectionLevel.Q);

        BitMatrix bitMatrix = QrCodeUtil.encode(content, BarcodeFormat.QR_CODE, config);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static void genImage(String content) throws IOException {

        BufferedImage qrCode = genQrCode(content);

        BufferedImage backgroundImage = ImageIO.read(
                new File("C:\\Users\\xxx\\Pictures\\picography-white-space-vintage-camera-768x400.jpg"));
        Graphics2D g = backgroundImage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(qrCode, (backgroundImage.getWidth() - qrCode.getWidth()) / 2,
                (backgroundImage.getHeight() - qrCode.getHeight()) / 2, null);
        g.dispose();
        ImageIO.write(backgroundImage, "jpeg", new File("output.jpeg"));
    }

    public static void main(String[] args) throws IOException {
        genImage("https://www.baidu.com");
    }
}
