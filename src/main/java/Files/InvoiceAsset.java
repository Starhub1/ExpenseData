package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.CSV;

public class InvoiceAsset {
	public static void run() throws IOException {
		String filename = "InvoiceAsset.csv";
		String h = "CREATION_DATE	ASSET_IDENTIFIER	ASSET_NAME	ASSET_STATUS	ASSET_SUB_TYPE	ASSET_TYPE_NAME	SERVICE_PROVIDER	INVOICE_ACCOUNT_FK	BILLING_END_DATE	INVOICE_HEADER_FK	TENANT_FK	VENDOR_FK	TENANT_VENDOR_COUNTRY";
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
				} else if (ele.equals(record.get("INVOICE_ACCOUNT_FK"))) {
					temp[i++] = ExpenseData.ACCOUNT_NO;
					continue;
				} else if (ele.equals(record.get("INVOICE_HEADER_FK"))) {
					temp[i++] = ExpenseData.INVOICE_HEADER_FK;
					continue;
				} else if (ele.equals(record.get("VENDOR_FK"))) {
					temp[i++] = ExpenseData.VENDOR;
					continue;
				} else if (ele.equals(record.get("ASSET_IDENTIFIER")) || ele.equals(record.get("ASSET_NAME"))) {
					if (record.getRecordNumber() == 1L) {
						temp[i++] = ExpenseData.DEVICE_ASSET;
						continue;
					} else
						temp[i++] = ExpenseData.PLAN_ASSET;
					continue;
				} else if (ele.equals(record.get("ASSET_SUB_TYPE")) || ele.equals(record.get("ASSET_TYPE_NAME"))) {
					if (record.getRecordNumber() == 1L) {
						temp[i++] = ExpenseData.ASSET_TYPE_DEVICE;
						continue;
					} else
						temp[i++] = ExpenseData.ASSET_TYPE_PLAN;
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
