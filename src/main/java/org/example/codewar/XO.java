package org.example.codewar;

public class XO {

    public static boolean getXO(String str) {
        int amtX = 0, amtO = 0;
        char[] cs = str.toLowerCase().toCharArray();
        for (char c : cs) {
            if (c == 'x') amtX++;
            else if (c == 'o') amtO++;
        }
        return amtO == amtX;
    }
}
