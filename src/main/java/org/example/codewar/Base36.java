package org.example.codewar;

public class Base36 {

    public static String convertToBase36(int decimalNumber) {
        if (decimalNumber == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (decimalNumber != 0) {
            int remainder = decimalNumber % 36;
            sb.append(getCharacter(remainder));
            decimalNumber /= 36;
        }
        return sb.reverse().toString();
    }

    public static char getCharacter(int value) {
        if (value < 10) {
            return (char) (value + '0');
        } else {
            return (char) (value - 10 + 'A');
        }
    }

    public static void main(String[] args) {
        System.out.println(convertToBase36(0));
        System.out.println(convertToBase36(36));
        System.out.println(convertToBase36(37));
        System.out.println(convertToBase36(72));
    }
}
