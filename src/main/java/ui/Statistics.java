package ui;

import data.DataGenerator;
import utils.NumberUtils;

import java.util.Arrays;

public class Statistics {

    public static void main(String[] args) {
        // array copy
        int[] hardCoded ={1,2,1,4,5,3,7,2,1};
        //randomm array
        int[] randomArray = DataGenerator.getRandomArray(10);
        //Displaying Arrays
        System.out.println("hardCoded" + Arrays.toString(hardCoded));
        System.out.println("random" + Arrays.toString(randomArray));

        int max =NumberUtils.getMax(randomArray);
        int fPos =NumberUtils.getPos(randomArray,max);
        int lPos = NumberUtils.getLastPos(randomArray,max);

        System.out.println("max value" + max);
        System.out.println("first pos" + fPos);
        System.out.println("last pos" + lPos);

        System.out.println("is identical" + NumberUtils.isIdentical(hardCoded,randomArray));
        System.out.println("is equel" + NumberUtils.isEquel(hardCoded,randomArray));
        System.out.println("one is subset of another" + NumberUtils.isSubset(hardCoded,randomArray));
    }
}
