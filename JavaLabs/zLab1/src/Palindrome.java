public class Palindrome {
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            String s = args[i];
        }
    }
    public static String reverseString(String s){
        String reverse_s = "";
        for(int i = s.length() - 1; i >= 0; i--){
            reverse_s += s.charAt(i);
        }
        return reverse_s;
    }
}
