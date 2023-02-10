package org.example.codewar;

public class MyFirstInterpreter {

    private final String code;

    public MyFirstInterpreter(String code) {
        this.code = code;
    }

    public String interpret() {
        int cellVal = 0;
        StringBuilder res = new StringBuilder();
        for (char c : code.toCharArray()) {
            if (c == '+') {
                cellVal = cellVal + 1;
                cellVal = cellVal % 256;
            } else if (c == '.') {
                res.append((char) cellVal);
            }
        }
        return res.toString();
    }
}
