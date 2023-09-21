package org.example.collection;

import java.util.Objects;

/**
 * <a href="https://rosettacode.org/wiki/Find_if_a_point_is_within_a_triangle#Java">source</a>
 */
public class FindTriangle {

    private static final double EPS = 0.001;
    private static final double EPS_SQUARE = EPS * EPS;

    public record Point(double x, double y) {

        @Override
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }
    }

    public record Triangle(Point p1, Point p2, Point p3) {

        public Triangle(Point p1, Point p2, Point p3) {
            this.p1 = Objects.requireNonNull(p1);
            this.p2 = Objects.requireNonNull(p2);
            this.p3 = Objects.requireNonNull(p3);
        }

        private boolean pointInTriangleBoundingBox(Point p) {
            var xMin = Math.min(p1.x(), Math.min(p2.x(), p3.x())) - EPS;
            var xMax = Math.max(p1.x(), Math.max(p2.x(), p3.x())) + EPS;
            var yMin = Math.min(p1.y(), Math.min(p2.y(), p3.y())) - EPS;
            var yMax = Math.max(p1.y(), Math.max(p2.y(), p3.y())) + EPS;
            return !(p.x() < xMin || xMax < p.x() || p.y() < yMin || yMax < p.y());
        }

        private static double side(Point p1, Point p2, Point p) {
            return (p2.y() - p1.y()) * (p.x() - p1.x()) + (-p2.x() + p1.x()) * (p.y() - p1.y());
        }

        private boolean nativePointInTriangle(Point p) {
            boolean checkSide1 = side(p1, p2, p) >= 0;
            boolean checkSide2 = side(p2, p3, p) >= 0;
            boolean checkSide3 = side(p3, p1, p) >= 0;
            return checkSide1 && checkSide2 && checkSide3;
        }

        private double distanceSquarePointToSegment(Point p1, Point p2, Point p) {
            double p1_p2_squareLength = (p2.x() - p1.x()) * (p2.x() - p1.x()) + (p2.y() - p1.y()) * (p2.y() - p1.y());
            double dotProduct = ((p.x() - p1.x()) * (p2.x() - p1.x()) + (p.y() - p1.y()) * (p2.y() - p1.y())) / p1_p2_squareLength;
            if (dotProduct < 0) {
                return (p.x() - p1.x()) * (p.x() - p1.x()) + (p.y() - p1.y()) * (p.y() - p1.y());
            }
            if (dotProduct <= 1) {
                double p_p1_squareLength = (p1.x() - p.x()) * (p1.x() - p.x()) + (p1.y() - p.y()) * (p1.y() - p.y());
                return p_p1_squareLength - dotProduct * dotProduct * p1_p2_squareLength;
            }
            return (p.x() - p2.x()) * (p.x() - p2.x()) + (p.y() - p2.y()) * (p.y() - p2.y());
        }

        private boolean accuratePointInTriangle(Point p) {
            if (!pointInTriangleBoundingBox(p)) {
                return false;
            }
            if (nativePointInTriangle(p)) {
                return true;
            }
            if (distanceSquarePointToSegment(p1, p2, p) <= EPS_SQUARE) {
                return true;
            }
            if (distanceSquarePointToSegment(p2, p3, p) <= EPS_SQUARE) {
                return true;
            }
            return distanceSquarePointToSegment(p3, p1, p) <= EPS_SQUARE;
        }

        public boolean within(Point p) {
            Objects.requireNonNull(p);
            return accuratePointInTriangle(p);
        }

        @Override
        public String toString() {
            return String.format("Triangle[%s, %s, %s]", p1, p2, p3);
        }
    }

    private static void test(Triangle t, Point p) {
        System.out.println(t);
        System.out.printf("Point %s is within triangle? %s\n", p, t.within(p));
    }

    public static void main(String[] args) {
        var p1 = new Point(1.5, 2.4);
        var p2 = new Point(5.1, -3.1);
        var p3 = new Point(-3.8, 1.2);
        var tri = new Triangle(p1, p2, p3);
        test(tri, new Point(0, 0));
        test(tri, new Point(0, 1));
        test(tri, new Point(3, 1));
        System.out.println();

        p1 = new Point(1.0 / 10, 1.0 / 9);
        p2 = new Point(100.0 / 8, 100.0 / 3);
        p3 = new Point(100.0 / 4, 100.0 / 9);
        tri = new Triangle(p1, p2, p3);
        var pt = new Point(p1.x() + (3.0 / 7) * (p2.x() - p1.x()), p1.y() + (3.0 / 7) * (p2.y() - p1.y()));
        test(tri, pt);
        System.out.println();

        p3 = new Point(-100.0 / 8, 100.0 / 6);
        tri = new Triangle(p1, p2, p3);
        test(tri, pt);
    }
}
