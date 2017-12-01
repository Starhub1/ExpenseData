package Files;

import Util.CSV;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BillJob {
    public static void run () throws IOException {
        String h = "first second third";
        String[] header = h.split("\\s+");

        //Reader
        Iterable<CSVRecord> records =  CSV.read("test.csv");

        //Updater
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
        //Writer
        CSV.write(header,ls,"test.csv");

    }
}
