package strategies;


import java.util.Arrays;

//first but index
public class NextFit implements AllocationStrategy {
    @Override
    public AllocationResult findBlock(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int successfulAllocations = 0;
        int totalMemoryUsed = 0;
        int lastAllocatedIndex = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = (lastAllocatedIndex + j) % m;
                if (blockSize[idx] >= processSize[i]) {
                    allocation[i] = idx;
                    blockSize[idx] -= processSize[i];
                    successfulAllocations++;
                    totalMemoryUsed += processSize[i];
                    lastAllocatedIndex = idx;
                    break;
                }
            }
        }
        long endTime = System.nanoTime();
        double timeTakenMillis = (endTime - startTime) / 1_000_000.0;

        System.out.println("\n--- Next Fit Performance Summary ---");
        System.out.println("Total Processes          : " + n);
        System.out.println("Successfully Allocated   : " + successfulAllocations);
        System.out.println("Failed Allocations       : " + (n - successfulAllocations));
        System.out.println("Total Memory Used        : " + totalMemoryUsed);
        System.out.println("Time Taken (ms)          : " + timeTakenMillis);
        System.out.println("Fragmentation per Block  : " + Arrays.toString(blockSize));

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }

    return new AllocationResult("NextFit", timeTakenMillis, successfulAllocations, totalMemoryUsed);
    }

}
