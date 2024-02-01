package org.example.playground;

import java.util.Arrays;
import java.util.HexFormat;

public class HexFormatTest {

    public static void main(String[] args) {
        HexFormat format = HexFormat.of();

        byte[] input = new byte[]{127, 0, -50, 105};
        String hex = format.formatHex(input);
        System.out.println(hex);

        byte[] output = format.parseHex(hex);
        assert Arrays.compare(input, output) == 0;
    }
}
