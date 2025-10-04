package utils;

import java.util.Arrays;

public class NumberUtils {

    /** find the first pos of a value in an array
     * @param nums the array of integers
     * @param value the value to find
     * @return  The index of the first occurence, or -1 if not found
      */

    public static int getPos(int[] nums,int value){
        for (int i=0;i<nums.length;i++){
            if(nums[i]==value){
                return i;
            }
        }return -1;
    }


    /**
     * Finds the last position of a value in an array
     * @param nums the array of integers
     * @param value teh value to find
     * @return the indec pf the last occurence -1 if not found
     */

    public static int getLastPos(int[] nums,int value){
        for(int i= nums.length-1;i>=0;i--){
            if(nums[i]==value){
                return i;
            }

        }
        return -1;

    }

    /**
     * checking if the arrays are identical
     * @param a1 first array
     * @param a2 second array
     *@return true if the arrays are identical
     */

    public static boolean isIdentical(int[]a1,int[]a2){
        if(a1.length!=a2.length)
            return false;
        for(int i=0;i<a1.length;i++){
            if(a1[i]!=a2[i])
               return false;
        }
        return true;
    }

    /**
     * check if two arrays have the same values
     * @param a1 firdt array
     * @param a2 second array
     * @return true if the array contain same element
     */
public static boolean isEquel(int[]a1,int[]a2){
    if(a1.length!=a2.length){
        return false;
    }
    int[] copya1 = Arrays.copyOf(a1,a1.length);
    int[] copya2 = Arrays.copyOf(a2,a2.length);
    Arrays.sort(copya1);
    Arrays.sort(copya2);
    return Arrays.equals(copya1,copya2);
}

/**
 * checks if array also is a subset of array b
 * @param a first array
 * @param b second array
 * @retirn true if A is a subsrt of b false otherwise
 */
public static boolean isSubset(int[] a , int[] b){
    if(a.length != b.length) {
        return false;
    }
    boolean found = true;

    for (int i=0;i<=a.length;i++){
        for(int j=0;j<=b.length;j++){
            if (a[i]==b[i]){
                found=true;
                break;//stop searching afte the same value  is found
            }

        }

        if(!found){
            return false;
        }
    }
    return true;
}
/**
 * return the max value in an array
 * @param The array of integers is nums
 * @return the max value in the array
 */

public static int getMax(int[] nums) {
    if (nums.length == 0) {
        throw new IllegalArgumentException("its empty");
    }

    int max =nums[0];
    for(int i=0;i<nums.length;i++){
        if(nums[i]>max){
            max=nums[i];
        }
    }
    return max;
}



}
