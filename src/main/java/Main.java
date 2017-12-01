import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String h = "first second third";
        String[] header = h.split("\\s+");
        Reader in = new FileReader("test.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        List<String[]> ls = new ArrayList<String[]>();
        String[] temp ;

        for (CSVRecord record : records) {

            String first = record.get("first");
            Iterator<String> it = record.iterator();
            temp = new  String[record.size()];
            int i = 0 ;
            while (it.hasNext()) {
                String ele = it.next();
                if (ele.equals(record.get("second"))){
                    temp[i++] = "test";
                    continue;
            }
                temp[i++] = ele;
            }
            ls.add(temp);

        }
        System.out.println(ls);
        CSVWriter writer = new CSVWriter(new FileWriter("test.csv"));
        writer.writeNext(header);
        writer.writeAll(ls);
        writer.close();
    }
}
