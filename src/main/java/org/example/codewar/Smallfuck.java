package org.example.codewar;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Smallfuck {
    // https://www.codewars.com/kata/58678d29dbca9a68d80000d7/train/java
    public static String interpreter(String code, String tape) {
        char[] tapeArr = tape.toCharArray();
        int tapeIndex = 0, tapeLen = tape.length();

        char[] codeArr = code.toCharArray();
        int codeIndex = 0, codeLen = code.length();

        // 处理 paring for '[' & ']'
        Map<Integer, Integer> pairIndexMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < codeLen; i++) {
            if (codeArr[i] == '[') {
                stack.offerLast(i);
            } else if (codeArr[i] == ']') {
                Integer leftIndex = stack.pollLast();
                pairIndexMap.put(leftIndex, i);
                pairIndexMap.put(i, leftIndex);
            }
        }

        while (codeIndex < codeLen) {
            char curOp = codeArr[codeIndex];
            if (curOp == '>') {
                tapeIndex = tapeIndex + 1;
                if (tapeIndex == tapeLen) {
                    break;
                }
            } else if (curOp == '<') {
                tapeIndex = tapeIndex - 1;
                if (tapeIndex < 0) {
                    break;
                }
            } else if (curOp == '*') {
                tapeArr[tapeIndex] = tapeArr[tapeIndex] == '0' ? '1' : '0';
            } else if (curOp == '[') {
                if (tapeArr[tapeIndex] == '0') {
                    codeIndex = pairIndexMap.get(codeIndex);
                }
            } else if (curOp == ']') {
                if (tapeArr[tapeIndex] != '0') {
                    codeIndex = pairIndexMap.get(codeIndex);
                }
            }

            codeIndex++;
        }

        return String.valueOf(tapeArr);
    }
}
