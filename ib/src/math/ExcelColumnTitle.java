package math;

import java.util.HashMap;
import java.util.Map;

public class ExcelColumnTitle {

    private String convertToTitle(int A) {
        int number = A;
        char c = 'A';
        Map<Integer, Character> valueMap = new HashMap<>(26);
        for (int i = 1; i <= 26; i++) {
            valueMap.put(i, c++);
        }
        valueMap.put(0, 'Z');
        String s = "";
        while (number > 26) {
            int remainder = number%26;
            s = valueMap.get(remainder) + s;
            if (remainder == 0)
                number -= 26;
             number = number/26;
        }
        s = valueMap.get(number) + s;
        return s;
    }

    public static void main(String[] args) {
        ExcelColumnTitle excelColumnTitle = new ExcelColumnTitle();
        System.out.println(excelColumnTitle.convertToTitle(1));
        System.out.println(excelColumnTitle.convertToTitle(943566));
        System.out.println(excelColumnTitle.convertToTitle(26));
        System.out.println(excelColumnTitle.convertToTitle(27));
        System.out.println(excelColumnTitle.convertToTitle(53));
        System.out.println(excelColumnTitle.convertToTitle(703));
    }

}
