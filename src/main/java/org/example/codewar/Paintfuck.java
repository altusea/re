package org.example.codewar;

import java.util.*;
import java.util.stream.Collectors;

public class Paintfuck {
    // https://www.codewars.com/kata/5868a68ba44cfc763e00008d/train/java
    public static String interpreter(String code, int iterations, int width, int height) {
        int[][] grid = new int[height][width];
        int pointerX = 0, pointerY = 0;
        int iterCnt = 0;

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

        while (codeIndex < codeLen && iterCnt < iterations) {
            char curOp = codeArr[codeIndex];
            if (curOp == 'n') {
                pointerX = pointerX - 1;
                if (pointerX == -1) {
                    break;
                }
                iterCnt++;
            } else if (curOp == 'e') {
                pointerY = pointerY + 1;
                if (pointerY == width) {
                    break;
                }
                iterCnt++;
            } else if (curOp == 's') {
                pointerX = pointerX + 1;
                if (pointerX == height) {
                    break;
                }
                iterCnt++;
            } else if (curOp == 'w') {
                pointerY = pointerY - 1;
                if (pointerY == 0) {
                    break;
                }
                iterCnt++;
            } else if (curOp == '*') {
                grid[pointerX][pointerY] = 1 - grid[pointerX][pointerY];
                iterCnt++;
            } else if (curOp == '[') {
                if (grid[pointerX][pointerY] == 0) {
                    codeIndex = pairIndexMap.get(codeIndex);
                    iterCnt++;
                }
            } else if (curOp == ']') {
                if (grid[pointerX][pointerY] != 0) {
                    codeIndex = pairIndexMap.get(codeIndex);
                }
            }

            codeIndex++;
            codeIndex++;
        }

        // fixme

        return Arrays.stream(grid)
                .map(ln -> Arrays.stream(ln).
                        mapToObj(String::valueOf).
                        collect(Collectors.joining("")))
                .collect(Collectors.joining("\\r\\n"));
    }

    public static void main(String[] args) {
        // "000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000",
        System.out.println(Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 0, 6, 9));
        // "111100\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000",
        System.out.println(Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 7, 6, 9));
        // "111100\r\n000010\r\n000001\r\n000010\r\n000100\r\n000000\r\n000000\r\n000000\r\n000000",
        System.out.println(Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 19, 6, 9));
        // "111100\r\n100010\r\n100001\r\n100010\r\n111100\r\n100000\r\n100000\r\n100000\r\n100000",
        System.out.println(Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 42, 6, 9));
        // "111100\r\n100010\r\n100001\r\n100010\r\n111100\r\n100000\r\n100000\r\n100000\r\n100000",
        System.out.println(Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 100, 6, 9));
    }
}
