package others.leetc.contests.biweekly.sixty.five;

import util.Verifier;

public class Robot {

    int curx, cury, width, height;
    int direction; // (0 - 4 -> east  - soutb)
    final int perimeter;

    public Robot(int width, int height) {
        this.curx = 0;
        this.cury = 0;
        this.width = width;
        this.height = height;
        this.perimeter = (width + height - 2) * 2;
        this.direction = 0;
    }

    public void step(int num) {
        if (num <= 0) {
            return;
        }
        if (num > perimeter) {
            num = num % perimeter;
            if (curx == 0 && cury == 0) {
                direction = 3;
            }
            if (curx == 0 && cury == height - 1) {
                direction = 2;
            }
            if (curx == width - 1 && cury == 0) {
                direction = 0;
            }
            if (curx == width - 1 && cury == height - 1) {
                direction = 1;
            }
        }
        if (direction == 0) {
            while (num > 0 && curx < width - 1) {
                curx++;
                num--;
            }
        }
        if (direction == 2) {
            while (num > 0 && curx > 0) {
                curx--;
                num--;
            }
        }
        if (direction == 1) {
            while (num > 0 && cury < height - 1) {
                cury++;
                num--;
            }
        }
        if (direction == 3) {
            while (num > 0 && cury > 0) {
                cury--;
                num--;
            }
        }
        if (num > 0) {
            direction = (direction + 1) % 4;
            step(num);
        }
    }

    public int[] getPos() {
        return new int [] {curx, cury};
    }

    public String getDir() {
        switch(direction) {
            case 0: return "East";
            case 1: return "North";
            case 2: return "West";
            default: return "South";
        }
    }

    public static void main(String[] args) {
        Robot robot = new Robot(20, 14);
        robot.step(32);
        robot.step(18);
        robot.step(18);
        Verifier.verifyEquals(robot.getPos(), new int[] {4,0});
        Verifier.verifyEquals(robot.getDir(), "East");
    }

}
