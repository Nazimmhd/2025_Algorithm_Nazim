package utils;

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







}
