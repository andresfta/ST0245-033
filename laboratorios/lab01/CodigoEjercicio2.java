public class CodingBat
{
    // Recursion 1

    public String parenBit(String str) {
        if(str.startsWith("(") && str.endsWith(")"))return str;
        if(str.startsWith("(") && !str.endsWith(")"))return parenBit(str.substring(0,str.length() - 1));
        if(!str.startsWith("(") && str.endsWith(")"))return parenBit(str.substring(1));
        return parenBit(str.substring(1,str.length() - 1));
    }

    public boolean nestParen(String str) {
        if(str.length() == 0)return true;
        if(str.length() < 2)return false;
        if(str.length() == 2 && str.equals("()"))return true;
        if(str.length() == 2 && !str.equals("()"))return false;
        if(str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')')return nestParen(str.substring(1, str.length() - 1));
        return false;
    }

    public int strCount(String str, String sub) {
        if(str.length() < sub.length())return 0;
        if(str.startsWith(sub))return 1 + strCount(str.substring(sub.length()), sub);
        else return strCount(str.substring(1), sub);
    }

    public boolean strCopies(String str, String sub, int n) {
        if(str.length() < sub.length())return n == 0;
        if(str.startsWith(sub))return strCopies(str.substring(1), sub, n - 1);
        if(!str.startsWith(sub))return strCopies(str.substring(1), sub, n);
        return false;
    }

    public int strDist(String str, String sub) {
        if(str.length() < sub.length())return 0;
        if(str.length() == sub.length() && str.equals(sub))return sub.length();
        if(str.length() == sub.length() && !str.equals(sub))return 0;
        if(str.startsWith(sub) && str.endsWith(sub))return str.length();
        if(!str.startsWith(sub) && str.endsWith(sub))return strDist(str.substring(1), sub);
        if(str.startsWith(sub) && !str.endsWith(sub))return strDist(str.substring(0,str.length() - 1), sub);
        else return strDist(str.substring(1, str.length() - 1), sub);
    }

    // Recursion 2

    public boolean groupNoAdj(int start, int[] nums, int target) {
        if(start >= nums.length)return target == 0;
        return groupNoAdj(start + 2, nums, target - nums[start]) || groupNoAdj(start + 1, nums, target);
    }

    public boolean groupSum5(int start, int[] nums, int target) {
        if(start == nums.length)return target == 0;
        if(nums[start] % 5 == 0){
            if(start == nums.length - 1)return groupSum5(start + 1, nums, target - nums[start]);
            if(nums[start + 1] == 1)return groupSum5(start + 2, nums, target - nums[start]);
            if(nums[start + 1] != 1)return groupSum5(start + 1, nums, target - nums[start]);
        }
        return groupSum5(start + 1, nums, target) || groupSum5(start + 1, nums, target - nums[start]); 
    }

    public boolean splitArray(int[] nums) {
        return splitArray(nums, 0, 0);
    }

    boolean splitArray(int[] nums, int index, int target){
        if(index == nums.length)return target == 0;
        return splitArray(nums, index + 1, target - nums[index]) ||
        splitArray(nums, index + 1, target + nums[index]);
    }

    public boolean splitOdd10(int[] nums) {
        return splitOdd10(nums, 0, 0, 0);
    }

    boolean splitOdd10(int[] nums, int index, int target1, int target2){
        if(index == nums.length)return target1 % 10 == 0 && target2 % 2 == 1;
        return splitOdd10(nums, index + 1, target1 + nums[index], target2) || 
        splitOdd10(nums, index + 1, target1, target2 + nums[index]);
    }

    public boolean split53(int[] nums) {
        return split53(nums, 0, 0);
    }

    public boolean split53(int[] nums, int index, int target){
        if(index == nums.length)return target == 0;
        if(nums[index] % 5 == 0)return split53(nums, index + 1, target + nums[index]);
        if(nums[index] % 3 == 0)return split53(nums, index + 1, target - nums[index]);
        return split53(nums, index + 1, target + nums[index]) || split53(nums, index + 1, target - nums[index]);
    }
}