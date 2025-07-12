package strategies;

public interface AllocationStrategy {
     AllocationResult findBlock(int[] blockSize, int m, int[] processSize, int n);
}
