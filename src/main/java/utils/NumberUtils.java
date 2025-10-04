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
}
