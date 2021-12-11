package others.leetc.contests.twohundred.sixty.eight;

import util.Verifier;

public class SumOfKMirrorNumbers {

    private static class BaseNNextChar {

        private final char biggest;

        public BaseNNextChar(int n) {
            this.biggest = (char) ('0' + (n - 1));
        }

        public char getFirst() {
            return '0';
        }

        public char getNext(char c) {
            return c >= '0' && c < biggest ? (char)(c+1) : getFirst();
        }

        public char getGreatestChar() {
            return biggest;
        }
    }

    private static class BaseNPalindromeGenerator {

        String cur = "1";
        final BaseNNextChar nextCharGenerator;

        public BaseNPalindromeGenerator(BaseNNextChar nextCharGenerator) {
            this.nextCharGenerator = nextCharGenerator;
        }

        String getNext() {
            String c = cur;
            generateNext();
            return c;
        }

        boolean isGreatestNumberForCurDigitCount() {
            char greatestChar = nextCharGenerator.getGreatestChar();
            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) != greatestChar) {
                    return false;
                }
            }
            return true;
        }

        String getSmallestPalindromeForDigits(int digitCount) {
            if (digitCount == 1) {
                return "1";
            }
            char [] chars = new char[digitCount];
            chars[0] = '1';
            chars[digitCount - 1] = '1';
            for (int i = 1; i < digitCount - 1; i++) {
                chars[i] = '0';
            }
            return new String(chars);
        }

        void generateNext() {
            boolean isOddLength = cur.length() % 2 == 1;
            int leftIdx = isOddLength ? cur.length()/2 : cur.length()/2 - 1;
            int rightIdx = isOddLength ? cur.length()/2 : cur.length()/2;
            char [] chars = new char[cur.length()];
            while (leftIdx >= 0 && rightIdx < cur.length()) {
                char left = cur.charAt(leftIdx);
                char right = cur.charAt(rightIdx);
                if (left > right) {
                    chars[leftIdx--] = left;
                    chars[rightIdx++] = left;
                    while (leftIdx >= 0 && rightIdx < cur.length()) {
                        left = cur.charAt(leftIdx);
                        right = cur.charAt(rightIdx);
                        chars[leftIdx--] = left;
                        chars[rightIdx++] = right;
                    }
                    cur = new String(chars);
                    return;
                } else if (left < right) {
                    break;
                }
                chars[leftIdx--] = left;
                chars[rightIdx++] = right;
            }
            if (isGreatestNumberForCurDigitCount()) {
                cur = getSmallestPalindromeForDigits(cur.length() + 1);
                return;
            }
            leftIdx = isOddLength ? cur.length()/2 : cur.length()/2 - 1;
            rightIdx = isOddLength ? cur.length()/2 : cur.length()/2;
            while (leftIdx >= 0 && rightIdx < cur.length()) {
                char left = cur.charAt(leftIdx);
                char right = cur.charAt(rightIdx);
                char next = nextCharGenerator.getNext(left);
                chars[leftIdx] = next;
                chars[rightIdx] = next;
                leftIdx--;rightIdx++;
                if (next != nextCharGenerator.getFirst()) {
                    while (leftIdx >= 0 && rightIdx < cur.length()) {
                        left = cur.charAt(leftIdx);
                        right = cur.charAt(rightIdx);
                        chars[leftIdx--] = left;
                        chars[rightIdx++] = right;
                    }
                    cur = new String(chars);
                }
            }
        }
    }

    long getBase10(String n, int base) {
        long sum = 0;
        int pow = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            int lastDigit = n.charAt(i) - '0';
            sum += lastDigit*Math.pow(base, pow++);
        }
        return sum;
    }

    public long kMirror(int k, int n) {
        BaseNPalindromeGenerator bnp10 = new BaseNPalindromeGenerator(new BaseNNextChar(10));
        BaseNPalindromeGenerator bnpK = new BaseNPalindromeGenerator(new BaseNNextChar(k));
        int found = 0;
        long next10 = 0, nextK = 0;
        boolean inc10 = true, incK = true;
        long sum = 0;
        while (found < n) {
            next10 = inc10 ? Long.parseLong(bnp10.getNext()) : next10;
            nextK = incK ? getBase10(bnpK.getNext(), k) : nextK;
            if (next10 == nextK) {
                found++;
                sum += next10;
                inc10 = true;
                incK = true;
            } else if (next10 < nextK) {
                inc10 = true;
                incK = false;
            } else {
                inc10 = false;
                incK = true;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfKMirrorNumbers sumOfKMirrorNumbers = new SumOfKMirrorNumbers();
        Verifier.verifyEquals(sumOfKMirrorNumbers.kMirror(2, 5), 25);
        Verifier.verifyEquals(sumOfKMirrorNumbers.kMirror(2, 20), 2630758);
        Verifier.verifyEquals(sumOfKMirrorNumbers.kMirror(3, 7), 499);
        Verifier.verifyEquals(sumOfKMirrorNumbers.kMirror(7, 17), 20379000);
    }

}
