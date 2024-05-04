package org.example.codewar;

public class Base36 {

    public static String convertToBase36(final int decimalNumber) {
        if (decimalNumber < 0) throw new IllegalArgumentException();
        if (decimalNumber == 0) return "0";
        var decimal = decimalNumber;
        StringBuilder sb = new StringBuilder();
        while (decimal != 0) {
            int remainder = decimal % 36;
            sb.append(getCharacter(remainder));
            decimal /= 36;
        }
        return sb.reverse().toString();
    }

    private static char getCharacter(int value) {
        if (value < 10) {
            return (char) (value + '0');
        } else {
            return (char) (value - 10 + 'A');
        }
    }
}
