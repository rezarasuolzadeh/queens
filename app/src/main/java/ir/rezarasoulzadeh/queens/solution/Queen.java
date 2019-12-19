package ir.rezarasoulzadeh.queens.solution;

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

    /**
     * The Genetic Algorithm, based on the pseudocode provided in lecture.
     *
     * @param boards
     * @return a solved Board
     */
    public static Board GeneticAlgorithm(Board[] boards) {
        Board[] population = boards;
        Random random = new Random();
        boolean solutionFound = false;
        int generation = 0;

        while (solutionFound == false) {
            Board[] newPopulation = new Board[population.length];

            sortByFitness(population);

            //checking if the best board in the population is at the maximum fitness value
            if (population[population.length - 1].getFitness() == population[0].maxFitness) {
                totalGenerationCount = totalGenerationCount + generation;
                return population[population.length - 1];
            }

            for (int i = 0; i < population.length; i++) {
                Board boardX = getChild(population);
                Board boardY = getChild(population);
                Board child = boardX.reproduceWith(boardY);

                if (random.nextDouble() < MUTATION_PROBABILITY) { /* small random probability*/
                    child = child.mutate();
                }

                newPopulation[i] = child;
            }

            population = newPopulation;
            generation++;
        }

        return null;
    }

    /**
     * Sorting the population of boards[] by the fitness value. The fitness value is the number of non-attacking queen pairs.
     * For Genetic Algorithm
     *
     * @param boards
     */
    public static void sortByFitness(Board[] boards) {
        double total = 0;    //stores the total fitness of all boards in the boards[] array

        //sum the total fitness of the population
        for (int i = 0; i < boards.length; i++) {
            total = total + boards[i].getFitness();
        }

        //to turn each fitnessArray[] value to a percentage of its value divided by the total fitness
        for (int i = 0; i < boards.length; i++) {
            boards[i].fitnessProbability = boards[i].fitness / total;
        }

        //perform selection sort
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

    /**
     * For Simulated Annealing
     *
     * @param boards, which are already be sorted by fitness
     * @return a Board randomly selected among the top 10 boards with the best fitness
     */
    public static Board getChild(Board[] boards) {
        Random random = new Random();
        //this calculation returns a random int among the last 10 index values
        int index = (boards.length - 10) + random.nextInt(10);
        return boards[index];
    }

}