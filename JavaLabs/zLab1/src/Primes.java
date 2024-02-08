public class Primes{
    public static void main(String[] args){
        // Output
        System.out.println("Простые числа:");
        for(int i = 2; i <= 100; i++){
            if(isPrime(i)){
                System.out.print(i + " ");
            }
        }
    }

    // Checking is number Prime
    public static boolean isPrime(int n){
        for(int i = 2; i < n-1;  i++){
            if(n % i == 0) return false;
        }
        return true;
    }

}