package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.CSV;

public class InvoiceHeader {

	public static void run() throws IOException {
		String filename = "InvoiceHeader.csv";
		String h = "TENANT_VENDOR_FK	INVOICE_COUNTRY_FK	TENANT_FK	TOTAL_AMOUNT_DUE_IND	TOTAL_AMOUNT_DUE	PREV_BALANCE_IND	PREV_BALANCE	REMIT_TO_ADDRESS1	JOB_FK	INVOICE_CURRENCY	ACCOUNT_NUMBER	BILLING_END_DATE	INVOICE_NUMBER	BILL_CLASSIFICATION_CODE	BILL_TO_ADDRESS1	DUE_DATE	SERVICE_TO	SERVICE_FROM	LOAD_DATE	RECEIPT_DATE	INVOICE_DATE	CURRENT_CHARGES	CURRENT_CHARGES_IND	RECURRING_INVOICE_FK	PARENT_ACCOUNT_FK	IS_MANUFACTURED_INVOICE_NUMBER	IS_MANUFACTURED_TRACKING_ACCOUNT	IS_MANUFACTURED_INVOICE_DATE";
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
				} else if (ele.equals(record.get("ACCOUNT_NUMBER"))) {
					temp[i++] = ExpenseData.ACCOUNT_NO;
					continue;
				} else if (ele.equals(record.get("INVOICE_NUMBER"))) {
					temp[i++] = ExpenseData.INVOICE_HEADER_FK;
					continue;
				} else if (ele.equals(record.get("TENANT_VENDOR_FK"))) {
					temp[i++] = ExpenseData.VENDOR;
					continue;
				} else if (ele.equals(record.get("TOTAL_AMOUNT_DUE")) || ele.equals(record.get("CURRENT_CHARGES"))) {
					temp[i++] = ExpenseData.CURRENT_CHARGES;
					continue;
				} else if (ele.equals(record.get("JOB_FK"))) {
					temp[i++] = ExpenseData.JOB_ID;
					continue;
				} else if (ele.equals(record.get("SERVICE_TO"))) {
					temp[i++] = ExpenseData.SERVICE_TO;
					continue;
				} else if (ele.equals(record.get("SERVICE_FROM"))) {
					temp[i++] = ExpenseData.SERVICE_FROM;
					continue;
				} else if (ele.equals(record.get("INVOICE_DATE"))) {
					temp[i++] = ExpenseData.INVOICE_DATE;
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
