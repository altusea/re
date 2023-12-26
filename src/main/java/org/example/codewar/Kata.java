package org.example.codewar;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

public class Kata {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        int number = 0;
        for (int bit : binary)
            number = number << 1 | bit;
        return number;
    }

    public static int[] digitize(long n) {
        if (n == 0) return new int[]{0};
        List<Integer> res = new ArrayList<>();
        while (n != 0) {
            res.add((int) (n % 10));
            n /= 10;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public static List<Integer> treeByLevels(Node node) {
        if (node == null) {
            return Collections.emptyList();
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            res.add(cur.value);
            if (cur.left != null) queue.addLast(cur.left);
            if (cur.right != null) queue.addLast(cur.right);
        }
        return res;
    }

    public static int[] sortArray(int[] array) {
        List<Integer> oddNums = new ArrayList<>();
        for (int i : array) {
            if (i % 2 != 0) {
                oddNums.add(i);
            }
        }
        oddNums.sort(Comparator.naturalOrder());
        int n = array.length;
        int index = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 != 0) {
                res[i] = oddNums.get(index++);
            } else {
                res[i] = array[i];
            }
        }
        return res;
    }

    public static int[] countBy(int x, int n) {
        return IntStream.rangeClosed(1, n).map(i -> i * x).toArray();
    }

    public static String seriesSum(int n) {
        double res = IntStream.range(0, n).map(i -> 3 * i + 1).mapToDouble(i -> 1.0 / i).sum();
        return String.format("%.2f", res);
    }

    public static int bouncingBall(double h, double bounce, double window) {
        if (h <= 0.0 || bounce >= 1.0 || bounce <= 0.0 || window >= h) return -1;
        int count = 0;
        double newH;
        while (h > window) {
            // fall
            count++;
            // ground
            newH = h * bounce;
            if (newH <= window) {
                return count;
            }
            // bounce
            h = newH;
            count++;
        }
        return count;
    }

    public static String[] towerBuilder(int nFloors) {
        String[] res = new String[nFloors];
        for (int i = 0; i < nFloors; i++) {
            int numStars = 2 * i + 1, numSpaces = nFloors - 1 - i;
            res[i] = " ".repeat(numSpaces) + "*".repeat(numStars) + " ".repeat(numSpaces);
        }
        return res;
    }

    public static int maxSubArraySum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int pre = 0;
        int res = arr[0];
        for (int num : arr) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return Math.max(res, 0);
    }

    public static String oddOrEven(int[] array) {
        if (array == null || array.length == 0) array = new int[]{0};
        return Arrays.stream(array).sum() % 2 == 0 ? "even" : "odd";
    }

    public static int findOdd(int[] arr) {
        int res = 0;
        for (int i : arr) {
            res = res ^ i;
        }
        return res;
    }

    public static double[] tribonacci(double[] s, int n) {
        double[] res = new double[n];
        if (n == 0) return res;
        int i = 0;
        while (i < Math.min(n, 3)) {
            res[i] = s[i];
            i++;
        }
        if (n < 4) return res;
        double a = res[i - 3], b = res[i - 2], c = res[i - 1], tmp;
        while (i < n) {
            tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
            res[i] = tmp;
            i++;
        }
        return res;
    }

    public static int findEvenIndex(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        int sumLeft = 0, sumRight = sum;
        for (int i = 0; i < arr.length; i++) {
            sumRight -= arr[i];
            if (sumLeft == sumRight) return i;
            sumLeft += arr[i];
        }
        return -1;
    }

    public static String highAndLow(String numbers) {
        var stats = Arrays.stream(numbers.split(" ")).mapToInt(Integer::parseInt).summaryStatistics();
        return stats.getMax() + " " + stats.getMin();
    }

    public static int[] countPositivesSumNegatives(int[] input) {
        if (input == null || input.length == 0) return new int[0];
        int countPos = 0, sumNeg = 0;
        for (int i : input) {
            if (i > 0) {
                countPos = countPos + 1;
            } else {
                sumNeg = sumNeg + i;
            }
        }
        return new int[]{countPos, sumNeg};
    }

    public static String abbrevName(String name) {
        int idxWhitespace = name.trim().indexOf(' ');
        return Character.toUpperCase(name.charAt(0)) + "." + Character.toUpperCase(name.charAt(idxWhitespace + 1));
    }

    public static int makeNegative(final int x) {
        return x > 0 ? -x : x;
    }

    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    public static String longToIP(long ip) {
        long all_1 = 0xff;

        long r1 = (ip >> 24) & all_1;
        long r2 = (ip >> 16) & all_1;
        long r3 = (ip >> 8) & all_1;
        long r4 = (ip >> 0) & all_1;

        return String.format("%d.%d.%d.%d", r1, r2, r3, r4);
    }

    public static String sumStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) sb.append(1);
        String tmpRes = sb.reverse().toString();
        return StringUtils.stripStart(tmpRes, "0");
    }

    public static int fusc(int n) {
        if (n == 0 || n == 1) return n;
        if (n % 2 == 0) return fusc(n / 2);
        return fusc(n / 2) + fusc(n / 2 + 1);
    }
}
