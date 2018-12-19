package math;

public class FizzBuzz {

    public String[] fizzBuzz(int A) {
        if (A <= 0) {
            return new String[0];
        }
        String [] s = new String[A];
        for (int i = 1; i <= A; i++) {
            String s1 = "" + i;
            if (i % 15 == 0) {
                s1 = "FizzBuzz";
            } else if (i % 5 == 0) {
                s1 = "Buzz";
            } else if (i % 3 == 0) {
                s1 = "Fizz";
            }
            s[i - 1] = s1;
        }
        return s;
    }

    private static void print(String [] s) {
        for (String s1: s) {
            System.out.print(s1 + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        print(fizzBuzz.fizzBuzz(16));
        print(fizzBuzz.fizzBuzz(1));
        print(fizzBuzz.fizzBuzz(-1));
        print(fizzBuzz.fizzBuzz(3));
        print(fizzBuzz.fizzBuzz(5));
    }

}
