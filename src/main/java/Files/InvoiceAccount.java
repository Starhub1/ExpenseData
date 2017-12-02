package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.CSV;

public class InvoiceAccount {
	public static void run() throws IOException {
		String filename = "InvoiceAccount.csv";
		String h = "CREATION_DATE	ACCOUNT_NAME	ACCOUNT_NUMBER	ACCOUNT_TYPE	BILLING_END_DATE	BILLING_START_DATE	CURRENT_CHARGES	CURRENT_CHARGES_DATE	CURRENT_CHARGES_IND	SERVICE_PROVIDER	Parent_Account_fk	INVOICE_HEADER_FK	TENANT_FK	TENANT_VENDOR_FK	INVOICE_COUNTRY_FK";
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
				} else if (ele.equals(record.get("ACCOUNT_NAME")) || ele.equals(record.get("ACCOUNT_NUMBER"))) {
					temp[i++] = ExpenseData.ACCOUNT_NO;
					continue;
				} else if (ele.equals(record.get("CURRENT_CHARGES"))) {
					temp[i++] = ExpenseData.CURRENT_CHARGES;
					continue;
				} else if (ele.equals(record.get("CURRENT_CHARGES_DATE"))) {
					temp[i++] = ExpenseData.SERVICE_TO;
					continue;
				} else if (ele.equals(record.get("INVOICE_HEADER_FK"))) {
					temp[i++] = ExpenseData.INVOICE_HEADER_FK;
					continue;
				} else if (ele.equals(record.get("TENANT_VENDOR_FK"))) {
					temp[i++] = ExpenseData.VENDOR;
					continue;
				} else
					temp[i++] = ele;
			}
			ls.add(temp);

		}
		// Writer
		CSV.write(header, ls, filename);

	}
}
