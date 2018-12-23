package math;

import java.util.HashMap;
import java.util.Map;

public class ExcelColumnNumber {

    private int titleToNumber(String A) {
        int n = A.length();
        int sum = 0;
        Map<Character, Integer> valueMap = new HashMap<>();
        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            valueMap.put(c++, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            sum += Math.pow(26, i) * valueMap.get(A.charAt(n-i-1));
        }
        return sum;
    }

    public static void main(String[] args) {
        ExcelColumnNumber excelColumnNumber = new ExcelColumnNumber();
        System.out.println(excelColumnNumber.titleToNumber("AB"));
        System.out.println(excelColumnNumber.titleToNumber("C"));
        System.out.println(excelColumnNumber.titleToNumber("BA"));
        System.out.println(excelColumnNumber.titleToNumber("AAA"));
    }

}
