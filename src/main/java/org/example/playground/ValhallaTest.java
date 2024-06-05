package org.example.playground;

import kotlin.OverloadResolutionByLambdaReturnType;

import java.util.*;

import static org.example.util.ConsoleUtil.printSeparateLine;

record Book(int a, String b) {
}

value record Pet(int age, String name) {
}

value class VC {

    String a;

    String b;

    public VC(String a, String b) {
        this.a = a;
        this.b = b;
    }
}

value record Color(byte red, byte green, byte blue) {
    public Color( int r, int g, int b){
        this(checkByte(r), checkByte(g), checkByte(b));
    }

    private static byte checkByte ( int x){
        if (x < 0 || x > 255) throw new IllegalArgumentException();
        return (byte) (x & 0xff);
    }

    public Color mix (Color that){
        return new Color(avg(red, that.red),
                avg(green, that.green),
                avg(blue, that.blue));
    }

    private static byte avg ( byte b1, byte b2){
        return (byte) (((b1 & 0xff) + (b2 & 0xff)) / 2);
    }
}

public class ValhallaTest {

    public static void main(String[] args) {
        System.out.println(Book.class.isValue());
        System.out.println(Pet.class.isValue());

        printSeparateLine();
        var dog1 = new Pet(12, "abc");
        var dog2 = new Pet(12, "abc");
        System.out.println(dog1.age());
        System.out.println(dog1.name());
        System.out.println(dog1 == dog2);
        System.out.println(dog1.hashCode());
        System.out.println(dog2.hashCode());

        printSeparateLine();
        System.out.println(Integer.class.isValue());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.println(list.size());

        printSeparateLine();
        Integer a = new Integer(130);
        Integer b = new Integer(130);
        System.out.println(a == b);
        System.out.println();
    }
}
