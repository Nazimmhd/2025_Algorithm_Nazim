package utils;

public class NumberUtils {

    /** find the first pos of a value in an array
     *
      */

    public static int getPos(int[] nums,int value){
        for (int i=0;i<nums.length;i++){
            if(nums[i]==value){
                return i;
            }
        }return -1;
    }
}
