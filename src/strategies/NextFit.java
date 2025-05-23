package strategies;

public class NextFit implements AllocationStrategy {
    private static int lastIndex = 0;

    @Override
    public int findBlock(int[] memory, int size) {
        int free = 0, start = -1;
        int len = memory.length;

        for (int offset = 0; offset < len; offset++) {
            int i = (lastIndex + offset) % len;

            if (memory[i] == 0) {
                if (free == 0) start = i;
                free++;
                if (free == size) {
                    lastIndex = (i + 1) % len;
                    return start;
                }
            } else {
                free = 0;
            }
        }
        return -1;
    }
}
