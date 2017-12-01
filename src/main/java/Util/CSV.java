package Util;

import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CSV {

    public static Iterable<CSVRecord> read(String filename) throws IOException {
        Reader in = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        return records;
    }

    public static void write(String[] header, List<String[]> ls,String filename) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename));
        writer.writeNext(header);
        writer.writeAll(ls);
        writer.close();
    }
}
