package strategies;

public class WorstFit implements AllocationStrategy {
    @Override
    public int findBlock(int[] memory, int size) {
        int worstStart = -1;
        int worstSize = -1;
        int free = 0, start = -1;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (free == 0) start = i;
                free++;
            } else {
                if (free >= size && free > worstSize) {
                    worstSize = free;
                    worstStart = start;
                }
                free = 0;
            }
        }

        // Final check if the last block is worst
        if (free >= size && free > worstSize) {
            worstStart = start;
        }

        return worstStart;
    }
}
