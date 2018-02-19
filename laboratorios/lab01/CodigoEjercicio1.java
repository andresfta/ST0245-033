import java.util.Random;
/**
 * Laboratorio 1
 *
 * @author Andrés Tamayo, Jamer Rebolledo
 * @version Febrero 2018
 */
public class Lab01
{
    static int sumArray(int[] nums){
        return sumArray(nums, 0);
    }
    
    private static int sumArray(int[] nums, int index){
        if(index == nums.length)return 0;
        return nums[index] + sumArray(nums, index + 1);
    }
    
    static int maxArray(int[] nums){
        return maxArray(nums, nums.length - 1);
    }
    
    private static int maxArray(int[] nums, int index){
        if(index == 0)return nums[index];
        int max = nums[index];
        int temp = maxArray(nums, index - 1);
        if(temp > max)max = temp;
        return max;
    }
    
    static long fibonacci(long n){
        if(n < 1)return 0;
        if(n == 1 || n == 2)return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    static int[] generarArray(int size){
        int[] array = new int[size];
        Random generator = new Random();
        for(int i = 0; i < size; i++)array[i] = generator.nextInt(size);
        return array;
    }
    
    static void main(String[] args){
        System.out.println("Prueba sumArray con un arreglo de 100'000.000 de posiciones");
        long startTime = System.currentTimeMillis();
        sumArray(generarArray(100000000));
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo: " + time);
        System.out.println();
        System.out.println("Prueba maxArray con un arreglo de 100'000.000 de posiciones");
        startTime = System.currentTimeMillis();
        sumArray(generarArray(100000000));
        time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo: " + time);
        System.out.println();
        System.out.println("Prueba de tiempo para números de fibonacci");
        startTime = System.currentTimeMillis();
        fibonacci(10);
        time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo para fibonacci de 10: " + time);
        System.out.println();
        startTime = System.currentTimeMillis();
        fibonacci(20);
        time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo para fibonacci de 20: " + time);
        System.out.println();
        startTime = System.currentTimeMillis();
        fibonacci(100);
        time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo para fibonacci de 100: " + time);
        System.out.println();
        startTime = System.currentTimeMillis();
        fibonacci(1000);
        time = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo para fibonacci de 1000: " + time);
        System.out.println();
    }
}
