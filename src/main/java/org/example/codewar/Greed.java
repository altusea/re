package org.example.codewar;

public class Greed {
    public static int greedy(int[] dice) {
        int[] count = new int[7];
        for (int d : dice) {
            count[d]++;
        }

        int score = 0;
        if (count[1] >= 3) {
            score += 1000;
            count[1] -= 3;
        }
        if (count[6] >= 3) {
            score += 600;
            count[6] -= 3;
        }
        if (count[5] >= 3) {
            score += 500;
            count[5] -= 3;
        }
        if (count[4] >= 3) {
            score += 400;
            count[4] -= 3;
        }
        if (count[3] >= 3) {
            score += 300;
            count[3] -= 3;
        }
        if (count[2] >= 3) {
            score += 200;
            count[2] -= 3;
        }
        score += count[1] * 100;
        score += count[5] * 50;
        return score;
    }
}
