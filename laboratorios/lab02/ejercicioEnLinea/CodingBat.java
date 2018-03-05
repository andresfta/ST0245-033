public class CodingBat
{
    // Array 2

    public String[] fizzBuzz(int start, int end) {
        int count = 0;
        String[] s = new String[end - start];
        for(int i = start; i < end; i++){
            if(i % 3 == 0 && i % 5 != 0)s[count] = "Fizz";
            if(i % 3 != 0 && i % 5 == 0)s[count] = "Buzz";
            if(i % 3 == 0 && i % 5 == 0)s[count] = "FizzBuzz";
            if(i % 3 != 0 && i % 5 != 0)s[count] = "" + i;
            count++;
        }
        return s;
    }

    public int[] evenOdd(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 != 0){
                for(int j = i + 1; j < nums.length; j++){
                    if(nums[j] % 2 == 0){
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                    }
                }
            }
        }
        return nums;
    }

    public int[] zeroMax(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == 0){
                int max = 0;
                for(int j = i; j < nums.length; j++){
                    if(nums[j] % 2 != 0 && nums[j] > max)max = nums[j];
                }
                nums[i] = max;
            }
        }
        return nums;
    }

    public int[] withoutTen(int[] nums) {
        int[] t = new int[nums.length];
        int count = 0;
        for(int i = 0; i < nums.length; i++)if(nums[i] != 10){t[count] = nums[i]; count++;}
        return t;
    }

    public int[] zeroFront(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                for(int j = i; j < nums.length; j++){
                    if(nums[j] == 0){
                        nums[j] = nums[i];
                        nums[i] = 0;
                    }
                }
            }
        }
        return nums;
    }

    // Array 3

    public int maxSpan(int[] nums) {
        int spam = 0;
        for(int i = 0; i < nums.length; i++){
            int izq = i;
            int der = i;
            for(int j = i; j < nums.length; j++){
                if(nums[i] == nums[j])der = j;
            }
            spam = Math.max(spam, der - izq + 1);
        }
        return spam;
    }

    public int[] fix34(int[] nums) {
        int last = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 3){
                for(int j = last; j < nums.length; j++){
                    if(nums[j] == 4){
                        int temp = nums[i + 1];
                        nums[i + 1] = 4;
                        nums[j] = temp;
                        last = j;
                        break;
                    }
                }
            }
        }
        return nums;
    }

    public int[] fix45(int[] nums) {
        int last = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 4 && nums[i + 1] != 5){
                for(int j = last; j < nums.length; j++){
                    if(nums[j] == 5 && (j == 0 || nums[j - 1] != 4)){
                        int temp = nums[i + 1];
                        nums[i + 1] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
        return nums;
    }

    public boolean canBalance(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++)sum += nums[i];
        if(sum % 2 != 0)return false;
        int count = 0;
        for(int i = 0; i < nums.length; i++){count += nums[i]; if(count * 2 == sum)return true;}
        return false;
    }

    public boolean linearIn(int[] outer, int[] inner) {
        for(int i = 0; i < inner.length; i++){
            int count = 0;
            for(int j = 0; j < outer.length; j++){
                if(inner[i] == outer[j])count++;
            }
            if(count == 0)return false;
        }
        return true;
    }
}
