package util;

public class Range extends Pair {
    public Range(int x, int y) {
        super(x, y);
        if (x > y)
            throw new IllegalArgumentException("x (" + x + ") is greater than y(" + y + ")");
    }

    public boolean liesOutside(Pair p) {
        return this.getX() > p.getY() || this.getY() < p.getX();
    }

    public boolean liesInside(Pair p) {
        return this.getY() <= p.getY() && this.getX() >= p.getX();
    }
}
