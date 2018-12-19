package math;

public class Palindrome {

    public int isPalindrome(int A) {
        if(A<0)
            return 0;
        int B = 0, C=A;
        while(A>0) {
            B = B*10 + A%10;
            A=A/10;
        }
        System.out.println(B);
        if (C==B)
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.print(palindrome.isPalindrome(2147447412));
    }

}
