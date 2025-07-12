package strategies;

import java.util.*;


//lists or buffers
public class QuickFit implements AllocationStrategy {

    @Override
    public AllocationResult findBlock(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int successfulAllocations = 0;
        int totalMemoryUsed = 0;
        TreeMap<Integer, List<Integer>> freeBlocks = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            freeBlocks.putIfAbsent(blockSize[i], new ArrayList<>());
            freeBlocks.get(blockSize[i]).add(i);
        }
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int process = processSize[i];
            int bucket = roundToBucket(process);

            Integer suitableKey = freeBlocks.ceilingKey(bucket);
            if (suitableKey != null) {
                List<Integer> blocks = freeBlocks.get(suitableKey);
                if (!blocks.isEmpty()) {
                    int blockIndex = blocks.removeFirst();
                    allocation[i] = blockIndex;
                    totalMemoryUsed += process;
                    blockSize[blockIndex] -= process;
                    successfulAllocations++;
                    if (blockSize[blockIndex] > 0) {
                        int newBucket = roundToBucket(blockSize[blockIndex]);
                        freeBlocks.putIfAbsent(newBucket, new ArrayList<>());
                        freeBlocks.get(newBucket).add(blockIndex);
                    }
                    if (blocks.isEmpty()) {
                        freeBlocks.remove(suitableKey);
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        double timeTakenMillis = (endTime - startTime) / 1_000_000.0;

        System.out.println("\n--- Quick Fit Performance Summary ---");
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
        return new AllocationResult("QuickFit", timeTakenMillis, successfulAllocations, totalMemoryUsed);
    }
    private int roundToBucket(int size) {
        return ((size + 49) / 100) * 100; // rounds to 100, 200, etc.
    }
}

//        @Override
//        public AllocationResult findBlock(int[] blockSize, int m, int[] processSize, int n) {
//            int[] allocation = new int[n];
//            Arrays.fill(allocation, -1);
//            int successfulAllocations = 0;
//            int totalMemoryUsed = 0;
//            Map<Integer, List<Integer>> freeBlocks = new TreeMap<>();
//            for (int i = 0; i < m; i++) {
//                freeBlocks.putIfAbsent(blockSize[i], new ArrayList<>());
//                freeBlocks.get(blockSize[i]).add(i);
//            }
//            long startTime = System.nanoTime();
//            for (int i =0; i < n; i++){
//                int process = processSize[i];
//                if (freeBlocks.containsKey(process)) {
//                    List<Integer> blocks = freeBlocks.get(process);
//                    if (!blocks.isEmpty()) {
//                        allocation[i] = blocks.remove(0);
//                        successfulAllocations++;
//                        totalMemoryUsed += process;
//                        if (blocks.isEmpty()) {
//                            freeBlocks.remove(process);
//                        }
//                    }
//                } else {
//                    for (int size : freeBlocks.keySet()) {
//                        if (size >= process) {
//                            List<Integer> blocks = freeBlocks.get(size);
//                            if (!blocks.isEmpty()) {
//                                allocation[i] = blocks.removeFirst();
//                                successfulAllocations++;
//                                totalMemoryUsed += process;
//                                if (blocks.isEmpty()) {
//                                    freeBlocks.remove(size);
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
