package org.example.playground;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.Decimal64Field;

public class MathTest {

    public static void main(String[] args) {
        System.out.println("=====> EuclideanDistance");
        System.out.println(new EuclideanDistance().compute(new double[]{0.0, 0.0}, new double[]{3.0, 4.0}));

        System.out.println("\n=====> Field");
        Field<Decimal64> field = Decimal64Field.getInstance();
        System.out.println(field.getZero());
        System.out.println(field.getOne());
        System.out.println(field.getRuntimeClass());
    }
}
