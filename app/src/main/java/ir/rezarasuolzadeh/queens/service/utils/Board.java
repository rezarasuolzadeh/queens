package ir.rezarasuolzadeh.queens.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    Random random = new Random();

    int[] queens;
    boolean[] attackStatus;
    int n;
    double fitness;
    double fitnessProbability;
    double maxFitness;

    public Board(int n) {
        queens = new int[n];
        attackStatus = new boolean[n];
        Arrays.fill(attackStatus, false);
        this.n = n;
        setInitialPositions();
        maxFitness = n * (n - 1) / 2;
    }

    public void setInitialPositions() {
        for (int i = 0; i < queens.length; i++) {
            queens[i] = random.nextInt(n);
        }
    }

    public int getFitness() {
        int fitness = 0;

        for (int i = 0; i < n; i++) {
            int curRow = queens[i];

            for (int j = i + 1; j < n; j++) {
                int colDiff = Math.abs(i - j);
                int rowDiff = Math.abs(curRow - queens[j]);

                if (rowDiff != colDiff && curRow != queens[j]) {
                    fitness++;
                }

            }
        }

        this.fitness = fitness;
        return fitness;
    }

    public Board reproduceWith(Board boardY) {
        Board x = this;
        Board y = boardY;
        Board child = new Board(x.n);

        Random random = new Random();
        int c = random.nextInt(n);

        for (int i = 0; i < x.n; i++) {
            if (i < c) {
                child.queens[i] = x.queens[i];
            } else {
                child.queens[i] = y.queens[i];
            }
        }

        return child;
    }

    public Board mutate() {
        Random random = new Random();
        int randomColumn = random.nextInt(n);

        queens[randomColumn] = random.nextInt(n);

        return this;
    }

    ArrayList<String> print() {
        ArrayList<String> array = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i == n - 1 && queens[i] == j) {
                    array.add(j + " " + i);
                } else if (queens[i] == j) {
                    array.add(j + " " + i);
                }
            }
        }

        return array;
    }

}