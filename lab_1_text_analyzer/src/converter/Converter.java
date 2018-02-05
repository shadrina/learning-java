package converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.lang.StringBuilder;
import java.util.*;

public class Converter {

    public static void main(String[] args) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream("input.txt"));
            TreeMap<String, Integer> words_with_freq = new TreeMap<>();
            StringBuilder builder = new StringBuilder();
            int symb = reader.read();
            while (true) {
                char ch = (char)symb;
                if (Character.isLetterOrDigit(ch)) builder.append(ch);
                else if (builder.length() != 0) {
                    String word = builder.toString();
                    if (words_with_freq.containsKey(word)) {
                        Integer index = words_with_freq.get(word);
                        words_with_freq.remove(word);
                        words_with_freq.put(word, index + 1);
                    }
                    else words_with_freq.put(word, 1);
                    builder.delete(0, builder.length());
                }
                if (symb == -1) break;
                symb = reader.read();
            }
            System.out.println(entriesSortedByValues(words_with_freq));
            CsvWriter.write(entriesSortedByValues(words_with_freq), 6);

        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try { reader.close(); }
                catch (IOException e) { e.printStackTrace(System.err); }
            }
        }
    }


    // stolen from Stackoverflow
    private static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

}
