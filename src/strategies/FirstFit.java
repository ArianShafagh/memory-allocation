package strategies;

public class FirstFit implements AllocationStrategy {
    @Override
    public int findBlock(int[] memory, int size) {
        int count = 0;
        int start = -1;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (count == 0) start = i;
                count++;
                if (count == size) return start;
            } else {
                count = 0;
            }
        }
        return -1;
    }
}
