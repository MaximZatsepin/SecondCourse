public class Palindrome {
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            String str = args[i];
        }
    }
    public static String reverseString(String str){
        String reverse_str = "";
        for(int i = str.length() - 1; i >= 0; i--){
            reverse_str += str.charAt(i);
        }
        return reverse_str;
    }
    public static boolean isPalindrome(String str){
        String str1 = str;
        String str2 = reverseString(str);
    }
}
