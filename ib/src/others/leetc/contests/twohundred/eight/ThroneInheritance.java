package others.leetc.contests.twohundred.eight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ThroneInheritance {

    private final String kingName;
    private final Map<String, List<String>> children;
    private final Map<String, String> parent;
    private Set<String> dead;
    int birthCount = 1;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        this.children = new HashMap<>();
        this.parent = new HashMap<>();
        this.dead = new HashSet<>();
    }

    public void birth(String parentName, String childName) {
        children.putIfAbsent(parentName, new ArrayList<>());
        children.computeIfPresent(parentName, (p,c) -> {c.add(childName); return c;});
        parent.put(childName, parentName);
        birthCount++;
    }

    public void death(String name) {
        this.dead.add(name);
        birthCount--;
    }

    String getSuccessor(String name, Set<String> currentOrder) {
        for (String child : children.getOrDefault(name, Collections.emptyList())) {
            if (!currentOrder.contains(child)) {
                return child;
            }
        }
        if (name.equals(kingName)) {
            return null;
        }
        return getSuccessor(parent.get(name), currentOrder);
    }

    public static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }

    public List<String> getInheritanceOrder() {
        Set<String> order = new LinkedHashSet<>(birthCount);
        order.add(kingName);
        String cur = kingName, suc;
        do {
            suc = getSuccessor(cur, order);
            if (suc != null) {
                order.add(suc);
            }
            cur = suc;
        } while (suc != null);
        return order.stream().filter(not(dead::contains)).collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }

}
