class Solution {
    public int[][] solution(int n, int[][] buildFrame) {
        boolean[][] pillars = new boolean[n + 2][n + 2];
        boolean[][] beams = new boolean[n + 2][n + 2];

        for (int[] command : buildFrame) {
            applyCommand(command, pillars, beams, n);
        }

        int count = 0;
        for (int x = 0; x <= n; x++)
            for (int y = 0; y <= n; y++) {
                if (pillars[x][y]) count++;
                if (beams[x][y]) count++;
            }

        int[][] result = new int[count][];
        int idx = 0;
        for (int x = 0; x <= n; x++)
            for (int y = 0; y <= n; y++) {
                if (pillars[x][y]) result[idx++] = new int[]{x, y, 0};
                if (beams[x][y]) result[idx++] = new int[]{x, y, 1};
            }
        return result;
    }

    private void applyCommand(int[] command, boolean[][] pillars, boolean[][] beams, int n) {
        int x = command[0], y = command[1], type = command[2], action = command[3];
        boolean[][] target = (type == 0) ? pillars : beams;
        boolean install = (action == 1);

        target[x][y] = install;
        if (!isAllValid(pillars, beams, n)) {
            target[x][y] = !install;
        }
    }

    private boolean isAllValid(boolean[][] pillars, boolean[][] beams, int n) {
        for (int x = 0; x <= n; x++)
            for (int y = 0; y <= n; y++) {
                if (pillars[x][y] && !canPlacePillar(x, y, pillars, beams)) return false;
                if (beams[x][y] && !canPlaceBeam(x, y, pillars, beams)) return false;
            }
        return true;
    }

    private boolean canPlacePillar(int x, int y, boolean[][] pillars, boolean[][] beams) {
        return y == 0
            || beams[x][y]
            || (x > 0 && beams[x - 1][y])
            || (y > 0 && pillars[x][y - 1]);
    }

    private boolean canPlaceBeam(int x, int y, boolean[][] pillars, boolean[][] beams) {
        return pillars[x][y - 1]
            || pillars[x + 1][y - 1]
            || (x > 0 && beams[x - 1][y] && beams[x + 1][y]);
    }
}