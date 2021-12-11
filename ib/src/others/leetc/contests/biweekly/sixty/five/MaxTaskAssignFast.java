package others.leetc.contests.biweekly.sixty.five;

import util.Verifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MaxTaskAssignFast {

    int [] tasks;
    TreeMap<Integer, Integer> workers;
    TreeMap<Integer, Integer> superWorkers;
    int strength;
    int pillsCount;

    private int checkFeasible(int taskStrengthRequirement, TreeMap<Integer, Integer> strengthsDB,
                              TreeMap<Integer, Integer> strengthsCache) {
        Integer workerStrength = strengthsDB.ceilingKey(taskStrengthRequirement);
        if (workerStrength == null) {
            return -1;
        }
        int assigned = -1;
        while (assigned == -1 && workerStrength != null) {
            Integer possibleWorkers = strengthsCache.get(workerStrength);
            if (possibleWorkers == null) {
                possibleWorkers = strengthsDB.get(workerStrength);
            }
            if (possibleWorkers != null && possibleWorkers > 0) {
                assigned = workerStrength;
            } else {
                workerStrength = strengthsDB.higherKey(workerStrength);
            }
        }
        return assigned;
    }

    private void updateCount(int workerStrength, TreeMap<Integer, Integer> db, TreeMap<Integer, Integer> cache,
                             Map<Integer, Integer> zeroWCache) {
        int updatedWorkerCount = cache.getOrDefault(workerStrength, db.get(workerStrength)) - 1;
        if (updatedWorkerCount == 0) {
            zeroWCache.put(workerStrength, db.get(workerStrength));
            cache.remove(workerStrength);
            db.remove(workerStrength);
        } else {
            cache.put(workerStrength, updatedWorkerCount);
        }
    }

    private boolean checkFeasible(int taskCount) {
        int pills = pillsCount;
        TreeMap<Integer, Integer> workerCache = new TreeMap<>();
        TreeMap<Integer, Integer> superWorkerCache = new TreeMap<>();
        Map<Integer, Integer> zeroWorkerCache = new HashMap<>();
        Map<Integer, Integer> zeroSuperWorkerCache = new HashMap<>();
        boolean feasible = true;
        for (int i = taskCount-1; i >= 0; i--) {
            int taskStrengthRequirement = tasks[i];
            int ws = checkFeasible(taskStrengthRequirement, workers, workerCache);
            if (ws == -1) {
                if (pills > 0) {
                    ws = checkFeasible(taskStrengthRequirement, superWorkers, superWorkerCache);
                    if (ws == -1) {
                        //System.out.println("Could not find");
                        feasible = false;
                        break;
                    }
                    pills--;
                    updateCount(ws - strength, workers, workerCache, zeroWorkerCache);
                    updateCount(ws, superWorkers, superWorkerCache, zeroSuperWorkerCache);
                } else {
                    //System.out.println("Pills over");
                    feasible = false;
                    break;
                }
            } else {
                updateCount(ws, workers, workerCache, zeroWorkerCache);
                updateCount(ws + strength, superWorkers, superWorkerCache, zeroSuperWorkerCache);
            }
        }
        for (Map.Entry<Integer, Integer> entry : zeroWorkerCache.entrySet()) {
            workers.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Integer, Integer> entry : zeroSuperWorkerCache.entrySet()) {
            superWorkers.put(entry.getKey(), entry.getValue());
        }
        return feasible;
    }

    private int findMaxDoable(int workerCount) {
        int low = 0, high = Math.min(tasks.length, workerCount);
        int maxPossible = 0;
        while (low <= high) {
            int mid = (low + high)/2;
            if (checkFeasible(mid)) {
                maxPossible = Math.max(maxPossible, mid);
                low = mid+1;
            } else {
                high = mid - 1;
            }
        }
        return maxPossible;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        this.tasks = tasks;
        this.workers = new TreeMap<>();
        this.superWorkers = new TreeMap<>();
        this.pillsCount = pills;
        this.strength = strength;
        for (int workerStrength : workers) {
            int superStrength = workerStrength + strength;
            this.workers.compute(workerStrength, (k, v) -> v == null ? 1 : v + 1);
            this.superWorkers.compute(superStrength, (k, v) -> v == null ? 1 : v + 1);
        }
        return findMaxDoable(workers.length);
    }

    static int [] getLargeTasks(BufferedReader br) throws Exception {
        String [] tasksS = br.readLine().split(",");
        int [] tasks =  new int[tasksS.length];
        int i = 0;
        for (String task : tasksS) {
            tasks[i++] = Integer.parseInt(task);
        }
        return tasks;
    }

    static int [] getLargeWorkers(BufferedReader br) throws Exception {
        String [] workersS = br.readLine().split(",");
        int [] workers =  new int[workersS.length];
        int i = 0;
        for (String task : workersS) {
            workers[i++] = Integer.parseInt(task);
        }
        return workers;
    }

    public static void main(String[] args) throws Exception {
        MaxTaskAssignFast mta = new MaxTaskAssignFast();
        long time = System.currentTimeMillis();
        Verifier.verifyEquals(mta.maxTaskAssign(new int[] {3,2,1}, new int[]{0,3,3}, 1, 1), 3);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[] {5,3}, new int[]{0,0,0}, 1, 5), 1);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[] {10,15,30}, new int[]{0,10,10,10,10}, 3, 10), 2);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[] {5,9,8,5,9}, new int[]{1,6,4,2,6}, 1, 5), 3);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[]{10,20,40}, new int[] {6,15,39}, 1, 5), 2);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[]{10,20,40}, new int[] {6,15,39}, 2, 5), 2);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[]{10,20,40}, new int[] {6,15,39}, 3, 5), 3);
        Verifier.verifyEquals(mta.maxTaskAssign(new int[]{7908,9988,9571,5279,4047,9760,3274,7098,6367,4774,9975,5544,8811,2564,3835,6634,5648,9052,8143,
                        5502,1285,7300,5630,7578,6522,2243,7284,6534,3766,1835,4153,4333,6338,6373,7517,6930,8190,2834,2218,2945,
                        6929,4170,9254,9312,9789,1324,3851,4038,9497,6486,7949,5781,7787,5185,2726,8538,3698,6929,2613,6860,
                        6981,7506,5294,6213,9848,4539,1234,8108,3832,5068,2712,6301,8340,7950,1320,9111,5411,8075,9752,6882,
                        9679,9463,6580,9986,5114,4483,7816,1878,2204,4364,1603,3131,3492,8579,3026,2521,5679,8630,8667,3827,
                        1555,3296,1118,4644,4866,1312,7632,9550,6914,9195,1009,2145,5184,4996,2913,6914,3584,4866,8505,7708,
                        1309,2780,1794,6103,6161,9576,9885,1843,2180,9261,6516,7411,8606,4633,5653,8562,9533,8516,2503,3270,5264,
                        1737,1603,1376,1834,6990,8234,8975,4194,2276,7633,3401,3521,6003,7685,2139,9061,2786,8637,7517,
                        1411,3732,9200,9772,8017,4008,9189,4728,5141,8600,5512,4239,5236,6909,9942,4712,7017,9755,2365,3130,
                        4668,9101,1414,7787,4533,7845,3755,2534,7896,4710,7581,2301,5079,4236,7276,8656,4777,1857},
                new int[] {4612,4669,4273,1929,1925,1342,4565,2152,1909,4897,2697,1419,1998,4789,2940,3388,4619,1427,1798,917,
                        2270,1639,1633,4669,2674,2348,3714,3909,4359,3151,1639,4533,3444,3531,887,528,2346,1481,831,2345,3610,
                        4507,2890,4740,3959,648,1468,3063,2416,4091,1151,2196,999,2708,578,978,4113,4229,2052,2154,2149,583,1029,2020,
                        4498,4638,4932,2986,2749,1047,2434,971,1645,570,1395,3603,4581,3607,3158,2758,1046,1171,2204,1421,3708,4769,
                        3399,981,3396,4837,3633,4067,4141,2037,1622,2257,1529,1371,4090,3273,4767,3543,1098,503,3905,4427,3875,2594,
                        4878,2252,912,4838,509,2989,3304,4268,1520,4027,3691,993,600,3529,3762,1854,592,1579,2191,3051,1897,4050,
                        1345,3416,1108,4862,4587,3065,3486,2138,1272,4237,4235,4107,1951,3747,2774,4944,3815,2560,3688,1838,2821,
                        3098,1361,1439,2486,4599,2215,1908,2432,1862,1780,1143,4117,3175,3839,3019,4576,4343,4186,1643,3472,
                        1637,693,4128,4709,4500,2639,4022,2886,1708,2721,4686,4870,772,557,1845,2788,821,3752},
                35, 2208), 118);
        System.out.println("Small test execution time = " + (System.currentTimeMillis() - time) + " ms");
        BufferedReader br = new BufferedReader(new FileReader("resources/LargeTestCaseTasks.txt"));
        int [] tasks, workers;
        try {
            tasks = getLargeTasks(br);
            workers = getLargeWorkers(br);
        } finally {
            br.close();
        }
        time = System.currentTimeMillis();
        Verifier.verifyEquals(mta.maxTaskAssign(tasks, workers, 3640, 683569), 4175);
        System.out.println("Large test execution time = " + (System.currentTimeMillis() - time) + " ms");
    }

}