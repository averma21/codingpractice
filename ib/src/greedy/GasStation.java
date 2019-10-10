package greedy;

import util.Creator;
import util.Verifier;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GasStation {

    private static int index(List<Integer> gas, List<Integer> dist) {
        if (gas == null || dist == null || gas.size() != dist.size())
            return -1;
        int diff = 0;
        for (int i = 0; i < gas.size(); i++) {
            diff += gas.get(i);
            diff -= dist.get(i);
        }
        if (diff < 0)
            return -1;
        if (gas.size() == 1)
            return 0;

        Queue<Integer> queue = new LinkedList<>();
        int fuel = gas.get(0) - dist.get(0);
        queue.add(0);
        int start = 0;
        while (queue.size() < gas.size()) {
            while (fuel >= 0) {
                start = (start+1)%gas.size();
                if (!queue.isEmpty() && start == queue.peek())
                    return queue.peek();
                queue.add(start);
                fuel += gas.get(start);
                fuel -= dist.get(start);
            }

            while (queue.size() > 0 && fuel < 0) {
                int removed = queue.poll();
                fuel += dist.get(removed);
                fuel -= gas.get(removed);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(index(Creator.getList(1,2), Creator.getList(2,1)), 1);
        Verifier.verifyEquals(index(Creator.getList(1,2,5,10,5), Creator.getList(2,3,4,11,3)), 2);
        Verifier.verifyEquals(index(Creator.getList(204, 918, 18, 17, 35, 739, 913, 14, 76, 555, 333, 535, 653, 667, 52, 987, 422, 553, 599, 765, 494, 298, 16, 285, 272, 485, 989, 627, 422, 399, 258, 959, 475, 983, 535, 699, 663, 152, 606, 406, 173, 671, 559, 594, 531, 824, 898, 884, 491, 193, 315, 652, 799, 979, 890, 916, 331, 77, 650, 996, 367, 86, 767, 542, 858, 796, 264, 64, 513, 955, 669, 694, 382, 711, 710, 962, 854, 784, 299, 606, 655, 517, 376, 764, 998, 244, 896, 725, 218, 663, 965, 660, 803, 881, 482, 505, 336, 279),
                Creator.getList(273, 790, 131, 367, 914, 140, 727, 41, 628, 594, 725, 289, 205, 496, 290, 743, 363, 412, 644, 232, 173, 8, 787, 673, 798, 938, 510, 832, 495, 866, 628, 184, 654, 296, 734, 587, 142, 350, 870, 583, 825, 511, 184, 770, 173, 486, 41, 681, 82, 532, 570, 71, 934, 56, 524, 432, 307, 796, 622, 640, 705, 498, 109, 519, 616, 875, 895, 244, 688, 283, 49, 946, 313, 717, 819, 427, 845, 514, 809, 422, 233, 753, 176, 35, 76, 968, 836, 876, 551, 398, 12, 151, 910, 606, 932, 580, 795, 187)), 31);
    }

}
