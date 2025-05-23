package strategies;

public class BestFit implements AllocationStrategy {
    @Override
    public int findBlock(int[] memory, int size) {
        int bestStart = -1;
        int bestSize = Integer.MAX_VALUE;
        int free = 0, start = -1;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (free == 0) start = i;
                free++;
            } else {
                if (free >= size && free < bestSize) {
                    bestSize = free;
                    bestStart = start;
                }
                free = 0;
            }
        }

        // Final check if the last block is best
        if (free >= size && free < bestSize) {
            bestStart = start;
        }

        return bestStart;
    }
}
