package others.leetc.contests.twohundred.sixty.eight;

import util.Verifier;

public class WateringPlants {

    public int wateringPlants(int[] plants, int capacity) {
        int water = capacity;
        int steps = 0;
        for (int i = 0; i < plants.length - 1; i++) {
            int pi = plants[i];
            water -= pi;
            steps++;
            if (water < plants[i+1]) {
                water = capacity;
                steps += 2*(i+1);
            }
        }
        steps++;
        return steps;
    }

    public static void main(String[] args) {
        WateringPlants wp = new WateringPlants();
        Verifier.verifyEquals(wp.wateringPlants(new int[] {2,2,3,3}, 5), 14);
        Verifier.verifyEquals(wp.wateringPlants(new int[] {1,1,1,4,2,3}, 4), 30);
        Verifier.verifyEquals(wp.wateringPlants(new int[] {7,7,7,7,7,7,7}, 8), 49);
    }

}
