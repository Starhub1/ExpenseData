package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.Amount;
import Util.CSV;

public class InvoiceSummary {

	public static void run() throws IOException {
		String filename = "InvoiceSummary.csv";
		String h = "TENANT_FK	CHARGE_PATH	AMOUNT	AMOUNT_IND	INVOICE_HEADER_FK	CHARGE_TYPE_FK	CREATION_DATE	AMOUNT_DATE	CREATED_BY";
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
				} else if (ele.equals(record.get("INVOICE_HEADER_FK"))) {
					temp[i++] = ExpenseData.INVOICE_HEADER_FK;
					continue;
				} else if (ele.equals(record.get("AMOUNT"))) {
					String[] amount = Amount.get();
					switch ((int) record.getRecordNumber()) {
					case 1:
						temp[i++] = amount[0];
						break;
					case 2:
						temp[i++] = amount[1];
						break;
					case 3:
						temp[i++] = amount[2];
						break;
					case 4:
						temp[i++] = amount[3];
						break;
					case 5:
						temp[i++] = amount[4];
						break;
					}
				} else
					temp[i++] = ele;
			}
			ls.add(temp);

		}
		// Writer
		CSV.write(header, ls, filename);

	}

}
