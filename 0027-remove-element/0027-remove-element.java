class Solution {
    public int removeElement(int[] nums, int val) {
        int re=0;
        int n = nums.length;
        for(int i = 0 ; i < n ; i++)
        {
            if(nums[i] == val){
                nums[i] = -9999;
            }
            else{
                re++;
            }
        }
        int j = 0;
        for(int i = 0 ; i < n ; i++){
            if(nums[i] != -9999){
                nums[j]=nums[i];
                j += 1;
            }

        }
        // while(j < n){

        // }
        return re;
        
    }
}