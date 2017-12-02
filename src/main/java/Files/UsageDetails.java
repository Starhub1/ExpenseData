package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.Amount;
import Util.CSV;

public class UsageDetails {

	public static void run() throws IOException {
		String filename = "Usagedetails.csv";
		String h = "CREATED_BY	TENANT_FK	CREATED_ON	INVOICE_HEADER_FK	ASSET_IDENTIFIER	CHARGE_AMOUNT	CHARGE_AMOUNT_IND	DURATION	IS_A_SUMMARY_AMOUNT	IS_TAXABLE	TAX_AMOUNT	USAGE_DESCRIPTION	ASSET_FK	MASTER_SERVICE_TYPE_FK	USAGE_ACCOUNT_FK	ASSET_TYPE	ASSET_TYPE_REF_FK	CHARGE_AMOUNT_DATE	TAX_AMOUNT_DATE	USAGE_DATE	UNIT_OF_MEASURE	BILLING_END_DATE	USAGE_TIME	APPROVAL_STATUS	CALL_MARKING_TYPE";
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
				} else if (ele.equals(record.get("USAGE_ACCOUNT_FK"))) {
					temp[i++] = ExpenseData.ACCOUNT_NO;
					continue;
				} else if (ele.equals(record.get("ASSET_TYPE")) || ele.equals(record.get("ASSET_TYPE_REF_FK"))) {
					temp[i++] = ExpenseData.ASSET_TYPE_PLAN;
					continue;
				} else if (ele.equals(record.get("ASSET_IDENTIFIER")) || ele.equals(record.get("ASSET_FK"))) {
					temp[i++] = ExpenseData.PLAN_ASSET;
					continue;
				} else if (ele.equals(record.get("DURATION"))) {
					Double d = Math.random() * 1000;
					temp[i++] = String.valueOf(d.intValue());
					continue;
				} else if (ele.equals(record.get("USAGE_DATE"))) {
					temp[i++] = ExpenseData.SERVICE_FROM;
					continue;
				} else if (ele.equals(record.get("CHARGE_AMOUNT"))) {
					String[] amount = Amount.get();
					switch ((int) record.getRecordNumber()) {
					case 1:
						temp[i++] = amount[1];
						break;
					case 2:
						temp[i++] = amount[2];
						break;
					case 3:
						temp[i++] = amount[3];
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
