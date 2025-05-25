
import strategies.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int memorySize = 100000;
        int requestCount = 10000;

        Map<String, AllocationStrategy> strategies = Map.of(
                "FirstFit", new FirstFit(),
                "BestFit", new BestFit(),
                "WorstFit", new WorstFit(),
                "NextFit", new NextFit(),
                "QuickFit", new QuickFit()
        );

        Map<String, Double[]> results = new LinkedHashMap<>();

        for (Map.Entry<String, AllocationStrategy> entry : strategies.entrySet()) {
            String name = entry.getKey();
            AllocationStrategy strategy = entry.getValue();

            MemorySimulator sim = new MemorySimulator(strategy, memorySize);
            sim.runSimulation(requestCount);

            results.put(name, new Double[]{
                    sim.getAverageTime(),
                    (double) sim.getSuccessfulRequests(),
                    sim.getMemoryUsage()
            });

            System.out.printf("[%s] Time: %.4fms, Success: %d,Usage: %.2f%%\n",
                    name, sim.getAverageTime(),
                    sim.getSuccessfulRequests(),
                    sim.getMemoryUsage());
        }

        // Save to CSV or JSON
        ResultWriter.write("results.csv", results);
    }
}
