public class Palindrome {
    // java Palindrome madam racecar apple kayak song noon
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            String s = args[i];
            // Output
            if(isPalindrome(s)) System.out.println(s + " is Palindrome");
            else System.out.println(s + " is not Palindrome");
        }
    }
    // Reversing string
    public static String reverseString(String s){ 
        String reverse_s = "";
        for(int i = s.length() - 1; i >= 0; i--){
            reverse_s += s.charAt(i);
        }
        return reverse_s;
    }
    // Checking string for Palindrome
    public static boolean isPalindrome(String s){
        String s1 = s;
        String s2 = reverseString(s);
        if(s1.equals(s2)) return true;
        return false;
    }
}
