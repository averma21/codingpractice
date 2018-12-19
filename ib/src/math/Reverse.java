package math;

public class Reverse {

    public int reverse(int A) {
        long B = 0;
        boolean negate = false;
        if (A < 0) {
            negate = true;
            A = -A;
        }
        while(A>0) {
            B = B*10 + A%10;
            A=A/10;
        }
        int C;
        try {
            C = Math.toIntExact(B);
        } catch(ArithmeticException e) {
            C = 0;
        }
        if(negate)
            C = -C;
        return C;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverse(-654));
        System.out.println(reverse.reverse(0));
        System.out.println(reverse.reverse(1000000009));
        System.out.println(reverse.reverse(321));
    }

}
