package others.leetc.heapsandmaps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Question not stated clearly -
 * The below two cases seem to conflict -
 * Input: keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * Output: ["clare","leslie"]
 *
 * Input: keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
 * Output: []
 */

//https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
public class KeyCardAndAlerts {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Set<String> alertSet = new HashSet<>();
        Map<String, Integer> swipedMap = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            if (alertSet.contains(keyName[i])) {
                continue;
            }
            String [] time = keyTime[i].split(":");
            String mapKey = keyName[i] + time[0];
            int val = swipedMap.getOrDefault(mapKey, 0) + 1;
            if (val == 3) {
                alertSet.add(keyName[i]);
            } else if ("00".equals(time[1])) {
                int prevHour = Integer.parseInt(time[0]) - 1;
                if (prevHour == -1) {
                    prevHour = 23;
                }
                String prevHourString = prevHour < 10 ? "0" + prevHour : "" + prevHour;
                String mapKeyPrevHour = keyName[i] + prevHourString;
                int valPrevHour = swipedMap.getOrDefault(mapKeyPrevHour, 0) + 1;
                if (valPrevHour == 3) {
                    alertSet.add(keyName[i]);
                }
                swipedMap.put(mapKeyPrevHour, valPrevHour);
            }
            swipedMap.put(mapKey, val);
        }
        return alertSet.stream().sorted().collect(Collectors.toList());
    }

}
