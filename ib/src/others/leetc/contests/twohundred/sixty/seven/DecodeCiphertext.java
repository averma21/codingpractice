package others.leetc.contests.twohundred.sixty.seven;

import util.Verifier;

public class DecodeCiphertext {

    public String decodeCiphertext(String encodedText, int rows) {
        if (encodedText == null || encodedText.length() == 0) {
            return encodedText;
        }
        int numberOfCols = encodedText.length() / rows;
        char [][] matrix = new char[rows][numberOfCols];
        int c = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                matrix[i][j] = encodedText.charAt(c++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int startCol = 0; startCol < numberOfCols; startCol++) {
            for (int i = 0, j = startCol; i < rows && j < numberOfCols; i++, j++) {
                sb.append(matrix[i][j]);
            }
        }
        for (int i = sb.length() - 1; i > 0; i--) {
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeCiphertext dc = new DecodeCiphertext();
        Verifier.verifyEquals(dc.decodeCiphertext("ch   ie   pr", 3), "cipher");
        Verifier.verifyEquals(dc.decodeCiphertext("iveo    eed   l te   olc", 4), "i love leetcode");
        Verifier.verifyEquals(dc.decodeCiphertext("coding", 1), "coding");
        Verifier.verifyEquals(dc.decodeCiphertext(" b  ac", 2), " abc");
    }

}
