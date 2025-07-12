package strategies;

public class AllocationResult {
    public final String strategyName;
    public final double timeMs;
    public final int successCount;
    public final double memoryUsagePercent;

    public AllocationResult(String strategyName, double timeMs, int successCount, double memoryUsagePercent) {
        this.strategyName = strategyName;
        this.timeMs = timeMs;
        this.successCount = successCount;
        this.memoryUsagePercent = memoryUsagePercent;
    }
    public Double[] toCsvRow() {
        return new Double[]{timeMs, (double) successCount, memoryUsagePercent};
    }
}
