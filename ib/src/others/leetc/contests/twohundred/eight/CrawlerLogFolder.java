package others.leetc.contests.twohundred.eight;

public class CrawlerLogFolder {

    public int minOperations(String[] logs) {
        int i = 0;
        for (String s : logs) {
            if (s.equals("./")) {
                continue;
            } else if (s.equals("../")) {
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
        }
        return i;
    }

}
