package org.example.playground.a;

import java.util.ArrayList;
import java.util.List;

public class ForLoopTest {

    public static void main(String[] args) {
        List<Runnable> toDoList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int j = i;
            toDoList.add(() -> System.out.println("Do item #" + j));
        }
        for (Runnable runnable : toDoList) {
            runnable.run();
        }
    }
}
