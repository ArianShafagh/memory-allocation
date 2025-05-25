
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ResultWriter {

    public static void write(String filePath, Map<String, Double[]> results) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("strategy,avg_time_ms,success_count,memory_usage_percent\n");
            for (String strategy : results.keySet()) {
                Double[] data = (Double[]) results.get(strategy);
                writer.write(strategy + "," + data[0] + "," + data[1].intValue() +"," + data[2] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
