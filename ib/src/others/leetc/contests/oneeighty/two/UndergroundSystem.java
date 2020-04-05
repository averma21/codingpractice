package others.leetc.contests.oneeighty.two;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

    private static class StationEvent {
        final String stationName;
        final int t;

        public StationEvent(String stationName, int t) {
            this.stationName = stationName;
            this.t = t;
        }
    }

    private static class Average {
        Double value;
        int entryCount;

        public Average() {
            this.value = 0.0;
            this.entryCount = 0;
        }

        public void addEntry(int time) {
            value = ((value*entryCount) + time)/(entryCount + 1);
            entryCount++;
        }
    }

    Map<Integer, StationEvent> checkinMap;
    Map<String, Map<String, Average>> averages;

    public UndergroundSystem() {
        checkinMap = new HashMap<>();
        averages = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkinMap.putIfAbsent(id, new StationEvent(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        StationEvent event = checkinMap.remove(id);
        averages.putIfAbsent(event.stationName, new HashMap<>());
        averages.computeIfPresent(event.stationName, (k,map) -> {
            map.putIfAbsent(stationName, new Average());
            map.computeIfPresent(stationName, (s, a) -> {
                a.addEntry(t-event.t);
                return a;
            });
            return map;
        });
    }

    public double getAverageTime(String startStation, String endStation) {
        return averages.getOrDefault(startStation, new HashMap<>()).getOrDefault(endStation , new Average()).value;
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        Verifier.verifyEquals(undergroundSystem.getAverageTime("Paradise", "Cambridge"), 14.0);      // return 14.0. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
        Verifier.verifyEquals(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 11.0);         // return 11.0. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        Verifier.verifyEquals(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 11.0);          // return 11.0
        undergroundSystem.checkOut(10, "Waterloo", 38);
        Verifier.verifyEquals(undergroundSystem.getAverageTime("Leyton", "Waterloo"), 12.0);         // return 12.0
    }

}
