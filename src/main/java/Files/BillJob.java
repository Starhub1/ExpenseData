package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.CSV;

public class BillJob {
	public static void run() throws IOException {
		String filename = "BI_JOB.csv";
		String h = "TENANT_FK	BILLING_SYSTEM_FK	BILLING_FORMAT_FK	PLATFORM_FK	PHASE	STATUS	BILL_NAME	LOAD_BALANCE	LEGACY_JOB_ID	POST_PROCESSING_STATUS";
		String[] header = h.split("\\s+");

		// Reader
		Iterable<CSVRecord> records = CSV.read(filename, header);

		// Updater
		List<String[]> ls = new ArrayList<String[]>();
		String[] temp;

		for (CSVRecord record : records) {

			Iterator<String> it = record.iterator();
			temp = new String[record.size()];
			int i = 0;
			while (it.hasNext()) {
				String ele = it.next();
				if (ele.equals(record.get("TENANT_FK"))) {
					temp[i++] = ExpenseData.TENANT;
					continue;
				} else if (ele.equals(record.get("BILL_NAME"))) {
					temp[i++] = ExpenseData.JOB_ID;
					continue;
				}

				temp[i++] = ele;
			}
			ls.add(temp);

		}
		// Writer
		CSV.write(header, ls, filename);

	}
}
