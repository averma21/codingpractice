package util;

public class Pair {

    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSameAs(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isAnyValueSame(int x, int y) {
        return this.x == x || this.y == y;
    }

    @Override
    public boolean equals(Object P) {
        return P instanceof Pair && x == ((Pair)P).x && y == ((Pair)P).y;
    }

    @Override
    public int hashCode() {
        return x+y;
    }

}
