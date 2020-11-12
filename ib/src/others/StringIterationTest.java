package others;

public class StringIterationTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append('a' + (i%26));
        }
        String s = sb.toString();
        long start = System.currentTimeMillis();
        int a = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            a += (c - 'a');
        }
        System.out.println("Time 1 =" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (char c : s.toCharArray()) {
            a += (c - 'a');
        }
        System.out.println("Time 2 =" + (System.currentTimeMillis() - start));
    }

}
