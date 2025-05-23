import strategies.AllocationStrategy;

import java.util.Random;

public class MemorySimulator {
    private final int[] memory;
    private final AllocationStrategy strategy;
    private int successfulRequests = 0;
    private long totalTime = 0;

    public MemorySimulator(AllocationStrategy strategy, int size) {
        this.memory = new int[size];
        this.strategy = strategy;
    }

    public void runSimulation(int requests) {
        Random rand = new Random();

        for (int i = 0; i < requests; i++) {
            int size = rand.nextInt(10) + 1;
            long start = System.nanoTime();
            int index = strategy.findBlock(memory, size);
            long end = System.nanoTime();
            totalTime += (end - start);

            if (index != -1) {
                for (int j = index; j < index + size; j++) memory[j] = 1;
                successfulRequests++;
            }
        }
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public double getAverageTime() {
        return totalTime / 1_000_000.0 / successfulRequests; // ms
    }

    public double getMemoryUsage() {
        int used = 0;
        for (int val : memory) if (val == 1) used++;
        return 100.0 * used / memory.length;
    }
}
