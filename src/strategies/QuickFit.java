package strategies;

import java.util.*;

public class QuickFit implements AllocationStrategy {
    private final Map<Integer, LinkedList<Integer>> freeLists = new HashMap<>();

    public QuickFit() {
        for (int i = 1; i <= 10; i++) {
            freeLists.put(i, new LinkedList<>());
        }
    }

    @Override
    public int findBlock(int[] memory, int size) {
        // Try to use a quick-fit list first
        LinkedList<Integer> list = freeLists.get(size);
        if (!list.isEmpty()) {
            int index = list.poll();
            return index;
        }

        // Fallback to First-Fit and record results
        int free = 0, start = -1;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (free == 0) start = i;
                free++;
                if (free == size) {
                    // Update quick-fit list
                    freeLists.get(size).add(start);
                    return start;
                }
            } else {
                free = 0;
            }
        }
        return -1;
    }
}
