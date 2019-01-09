package strings;

import util.Verifier;

public class AmazingSubarrays {

    public int solve(String A) {
        if (A == null || A.length() == 0)
            return 0;
        long count = 0;
        int size = A.length();
        for (int i = 0; i < size; i++) {
            char c = A.charAt(i);
            if (c == 'a' || c == 'e'|| c == 'i'|| c == 'o'|| c == 'u'|| c == 'A'|| c == 'E'|| c == 'I'|| c == 'O'|| c == 'U') {
                count = (count + (size - i));
            }
        }
        return (int)(count%10003);
    }

    public static void main(String[] args) {
        AmazingSubarrays amazingSubarrays = new AmazingSubarrays();
        Verifier.verifyEquals(amazingSubarrays.solve(null), 0);
        Verifier.verifyEquals(amazingSubarrays.solve(""), 0);
        Verifier.verifyEquals(amazingSubarrays.solve("xyz"), 0);
        Verifier.verifyEquals(amazingSubarrays.solve("ABEC"), 6);
        Verifier.verifyEquals(amazingSubarrays.solve("AEIOU"), 15);
        Verifier.verifyEquals(amazingSubarrays.solve("aeiou"), 15);
    }

}
