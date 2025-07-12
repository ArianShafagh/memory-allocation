import strategies.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[] blockSize = {100, 500, 200, 300, 600, 400, 350, 250, 700, 800, 900, 1200};
//        int[] processSize = {212, 417, 112, 426, 300, 500, 600, 700, 800, 900, 1000, 1100};
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Memory Allocation Strategies Comparison!");
        System.out.print("Enter the number of blocks: ");
        int numBlocks = sc.nextInt();
        System.out.print("Enter the number of processes: ");
        int numProcesses = sc.nextInt();
        int[] blockSize =  Generator(numBlocks);
        int[] processSize = Generator(numProcesses);
        int m = blockSize.length;
        int n = processSize.length;

        List<AllocationResult> results = new ArrayList<>();

        results.add(new FirstFit().findBlock(blockSize.clone(), m, processSize, n));
        results.add(new BestFit().findBlock(blockSize.clone(), m, processSize, n));
        results.add(new WorstFit().findBlock(blockSize.clone(), m, processSize, n));
        results.add(new NextFit().findBlock(blockSize.clone(), m, processSize, n));
        results.add(new QuickFit().findBlock(blockSize.clone(), m, processSize, n));

        Map<String, Double[]> resultMap = new LinkedHashMap<>();
        for (AllocationResult result : results) {
            resultMap.put(result.strategyName, result.toCsvRow());
        }

        ResultWriter.write("allocation_results.csv", resultMap);

    }
    public static int[] Generator(int input){
        int[] rand = new int[input];
        for(int i = 0; i < input; i++){
            rand[i] = (int) (Math.random() * 900 + 100);
        }
        return rand;
    }
}
