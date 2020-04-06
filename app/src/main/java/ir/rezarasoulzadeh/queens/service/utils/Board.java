package ir.rezarasoulzadeh.queens.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    Random random = new Random();

    int[] queens;    //represents the queens' position on the game board
    boolean[] attackStatus;    //true if queen[i] is in an attack position
    int n;    //size of the board, n x n
    double fitness; //number of non-attacking queen pairs
    double fitnessProbability; //fitness / total fitness of the population (for Genetic Algorithm)
    double maxFitness;    //stores max fitness value for a board of size n x n

    /**
     * Constructor
     *
     * @param n, size of the board, n x n
     */
    public Board(int n) {
        queens = new int[n];
        attackStatus = new boolean[n];
        Arrays.fill(attackStatus, false);
        this.n = n;
        setInitialPositions();
        maxFitness = n * (n - 1) / 2;
    }

    /**
     * Randomly sets the queens' position. Used when initializing boards for Simulated Annealing.
     */
    public void setInitialPositions() {
        for (int i = 0; i < queens.length; i++) {
            queens[i] = random.nextInt(n);
        }
    }

    /**
     * @return the number of non-attacking pairs of queens
     */
    public int getFitness() {
        int fitness = 0;

        for (int i = 0; i < n; i++) {
            //int curColumn = i;
            int curRow = queens[i];

            for (int j = i + 1; j < n; j++) {
                //now for every column i we are checking, we will start with j which is starting at i + 1
                int colDiff = Math.abs(i - j);
                int rowDiff = Math.abs(curRow - queens[j]);

                if (rowDiff != colDiff && curRow != queens[j]) {
                    fitness++; //for the queens in column i and j, they do not land on the same row or same diagonal, and therefore are a non-attacking pair
                }

            }
        }

        this.fitness = fitness;
        return fitness;
    }

    /**
     * @param boardY
     * @return a new offspring of this.queens[] and boardY.queens[]
     */
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

    /**
     * @return a Board that has one column randomly changed, a mutation
     */
    public Board mutate() {
        Random random = new Random();
        int randomColumn = random.nextInt(n);

        queens[randomColumn] = random.nextInt(n);

        return this;
    }

    /**
     * Prints the chess board. Q's are queens.
     *
     * @return
     */
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