package converter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedSet;
import java.util.Map;
import java.text.DecimalFormat;

public class CsvWriter {

    private static final char SEPARATOR = ',';

    public static void write(SortedSet<Map.Entry<String,Integer>> set, int words_number) {
        String csvFile = "output.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            writer.append("Word").append(SEPARATOR).append("Count").append(SEPARATOR).append("Probability").append("\n");
            for (Map.Entry<String,Integer> entry : set) {
                DecimalFormat df = new DecimalFormat("#.##");
                writer.append(entry.getKey())
                        .append(SEPARATOR)
                        .append(String.valueOf(entry.getValue()))
                        .append(SEPARATOR)
                        .append(String.valueOf(df.format(entry.getValue() * 1.0 / words_number)))
                        .append("\n");
            }
        }
        catch (IOException e) {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try { writer.close(); }
                catch (IOException e) { e.printStackTrace(System.err); }
            }
        }
    }
}
