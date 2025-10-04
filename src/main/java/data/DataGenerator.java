package data;

import java.util.Random;

public class DataGenerator {
    private static Random randomGenerator = new Random();

    /**
     * Generates an array of random integers.
     *
     * @return an array of random numbers, or null if the number of elements requested is <= 0
     */
    public static int[] getRandomArray(){
        if(numElements <= 0){
            return null;
        }

        int [] values = new int[numElements];
        for (int i = 0; i < values.length; i++) {
            values[i] = randomGenerator.nextInt();
        }

        return values;
    }
}
