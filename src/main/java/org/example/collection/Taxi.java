package org.example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class CubeSum implements Comparable<CubeSum> {
    public long x, y, value;

    public CubeSum(long x, long y) {
        this.x = x;
        this.y = y;
        this.value = x * x * x + y * y * y;
    }

    public String toString() {
        return String.format("%4d^3 + %4d^3", x, y);
    }

    public int compareTo(CubeSum that) {
        return Long.compare(value, that.value);
    }
}

class SumIterator implements Iterator<CubeSum> {
    PriorityQueue<CubeSum> pq = new PriorityQueue<>();
    long n = 0;

    public boolean hasNext() {
        return true;
    }

    public CubeSum next() {
        while (pq.isEmpty() || pq.peek().value >= n * n * n)
            pq.add(new CubeSum(++n, 1));

        CubeSum s = pq.remove();
        if (s.x > s.y + 1) pq.add(new CubeSum(s.x, s.y + 1));

        return s;
    }
}

class TaxiIterator implements Iterator<List<CubeSum>> {
    Iterator<CubeSum> sumIterator = new SumIterator();
    CubeSum last = sumIterator.next();

    public boolean hasNext() {
        return true;
    }

    public List<CubeSum> next() {
        CubeSum s;
        List<CubeSum> train = new ArrayList<>();

        while ((s = sumIterator.next()).value != last.value)
            last = s;

        train.add(last);

        do {
            train.add(s);
        } while ((s = sumIterator.next()).value == last.value);
        last = s;

        return train;
    }
}

public class Taxi {
    public static void main(String[] args) {
        Iterator<List<CubeSum>> taxi = new TaxiIterator();

        for (int i = 1; i <= 2006; i++) {
            List<CubeSum> t = taxi.next();
            if (i > 25 && i < 2000) continue;

            System.out.printf("%4d: %10d", i, t.get(0).value);
            for (CubeSum s : t)
                System.out.print(" = " + s);
            System.out.println();
        }
    }
}
