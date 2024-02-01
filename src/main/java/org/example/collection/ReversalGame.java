package org.example.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ReversalGame {

    private static final Logger log = LoggerFactory.getLogger(ReversalGame.class);

    private List<Integer> gameList;

    public ReversalGame() {
        initialize();
    }

    public void play() {
        int moveCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(gameList);
            System.out.println("Please enter a index to reverse from 2 to 9. Enter 99 to quit");
            int i = scanner.nextInt();
            if (i == 99) {
                break;
            }
            if (i < 2 || i > 9) {
                System.out.println("Invalid input");
            } else {
                moveCount++;
                reverse(i);
                if (isSorted()) {
                    System.out.println("Congratulations you solved this in " + moveCount + " moves!");
                    break;
                }
            }

        }
        scanner.close();
    }

    private void reverse(int position) {
        Collections.reverse(gameList.subList(0, position));
    }

    private boolean isSorted() {
        for (int i = 0; i < gameList.size() - 1; ++i) {
            if (gameList.get(i).compareTo(gameList.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void initialize() {
        this.gameList = new ArrayList<>(9);
        for (int i = 1; i < 10; ++i) {
            gameList.add(i);
        }
        while (isSorted()) {
            Collections.shuffle(gameList);
        }
    }

    public static void main(String[] args) {
        try {
            ReversalGame game = new ReversalGame();
            game.play();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
