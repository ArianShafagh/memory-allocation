import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class CPUMonitor {
    public static double getCpuLoad() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getSystemCpuLoad() * 100;
    }
}
