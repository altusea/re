package org.example.codewar;

import java.awt.*;
import java.util.List;
import java.util.*;

// https://www.codewars.com/kata/5abeaf0fee5c575ff20000e4/train/java
public class Finder {

    // todo

    enum Direction {

        UP(-1, 0, "up"),
        DOWN(1, 0, "down"),
        LEFT(0, -1, "left"),
        RIGHT(0, 1, "right");

        int dx;
        int dy;
        String desc;

        Direction(int dx, int dy, String desc) {
            this.dx = dx;
            this.dy = dy;
            this.desc = desc;
        }
    }

    static int TEMP = Integer.MAX_VALUE;
    static List<String> res = new ArrayList<>();

    static Deque<String> his = new ArrayDeque<>();

    static void dfs(int[][] t, boolean[][] mark, Point cur, Point finish, int rowLimit, int colLimit, int acc) {
        if (cur.x == -1 || cur.x == rowLimit || cur.y == -1 || cur.y == colLimit || mark[cur.x][cur.y] || acc >= TEMP) {
            return;
        }
        if (cur.x == finish.x && cur.y == finish.y) {
            if (acc < TEMP) {
                TEMP = acc;
                res = new ArrayList<>(his);
            }
            return;
        }

        for (Direction d : Direction.values()) {

            Point n = new Point(cur.x + d.dx, cur.y + d.dy);
            mark[n.x][n.y] = true;
            his.addLast(d.desc);
            dfs(t, mark, n, finish, rowLimit, colLimit, acc + t[cur.x][cur.y]);
            his.pollLast();
            mark[n.x][n.y] = false;
        }
    }

    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        // Your code here!!
        int rows = t.length, cols = t[0].length;
        TEMP = Integer.MAX_VALUE;
        boolean[][] mark = new boolean[rows][cols];
        mark[start.x][start.y] = true;
        dfs(t, mark, start, finish, rows, cols, 0);
        return res;
    }

    public static void sampleTest1() {
        int[][] tollMap = {{1, 9, 1}, {2, 9, 1}, {2, 1, 1}};
        Point start = new Point(0, 0), finish = new Point(0, 2);
        List<String> expected = Arrays.asList("down", "down", "right", "right", "up", "up");
        System.out.println(expected);
        System.out.println(Finder.cheapestPath(tollMap, start, finish));
    }

    public static void sampleTest2() {
        int[][] tollMap = {{1, 4, 1}, {1, 9, 1}, {1, 1, 1}};
        Point start = new Point(0, 0), finish = new Point(0, 2);
        List<String> expected = Arrays.asList("right", "right");
        System.out.println(expected);
        System.out.println(Finder.cheapestPath(tollMap, start, finish));
    }

    public static void sampleTest3_NoCost() {
        int[][] tollMap = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Point start = new Point(1, 1), finish = new Point(1, 1);
        List<String> expected = List.of();
        System.out.println(expected);
        System.out.println(Finder.cheapestPath(tollMap, start, finish));
    }

    public static void main(String[] args) {
        sampleTest1();
        sampleTest2();
        sampleTest3_NoCost();
    }
}
