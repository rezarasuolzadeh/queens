package ir.rezarasuolzadeh.queens.service.utils;

import java.util.ArrayList;
import java.util.Random;

public class Queen {

    final static double MUTATION_PROBABILITY = .8;
    static int BOARD_SIZE = 8;
    final static int POPULATION_SIZE = 50;
    static int totalGenerationCount = 0;

    public static ArrayList<String> solution(int boardSize) {
        BOARD_SIZE = boardSize;
        Board[] boards = new Board[POPULATION_SIZE];
        for (int j = 0; j < boards.length; j++) {
            boards[j] = new Board(BOARD_SIZE);
        }
        Board solution = GeneticAlgorithm(boards);
        return solution.print();
    }

    public static Board GeneticAlgorithm(Board[] boards) {
        Board[] population = boards;
        Random random = new Random();
        boolean solutionFound = false;
        int generation = 0;

        while (solutionFound == false) {
            Board[] newPopulation = new Board[population.length];

            sortByFitness(population);

            if (population[population.length - 1].getFitness() == population[0].maxFitness) {
                totalGenerationCount = totalGenerationCount + generation;
                return population[population.length - 1];
            }

            for (int i = 0; i < population.length; i++) {
                Board boardX = getChild(population);
                Board boardY = getChild(population);
                Board child = boardX.reproduceWith(boardY);

                if (random.nextDouble() < MUTATION_PROBABILITY) {
                    child = child.mutate();
                }

                newPopulation[i] = child;
            }

            population = newPopulation;
            generation++;
        }

        return null;
    }

    public static void sortByFitness(Board[] boards) {
        double total = 0;

        for (Board board : boards) {
            total = total + board.getFitness();
        }

        for (Board board : boards) {
            board.fitnessProbability = board.fitness / total;
        }

        for (int i = 0; i < boards.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < boards.length; j++) {
                if (boards[j].fitnessProbability < boards[minIndex].fitnessProbability) {
                    minIndex = j;
                }
            }
            Board tmp = boards[minIndex];
            boards[minIndex] = boards[i];
            boards[i] = tmp;
        }
    }

    public static Board getChild(Board[] boards) {
        Random random = new Random();
        int index = (boards.length - 10) + random.nextInt(10);
        return boards[index];
    }

}