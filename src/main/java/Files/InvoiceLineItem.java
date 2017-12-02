package Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;
import Util.Amount;
import Util.CSV;

public class InvoiceLineItem {

	public static void run() throws IOException {
		String filename = "InvoiceLineItem.csv";
		String h = "TENANT_FK	ACCOUNT	BILLING_END_DATE	AMOUNT	AMOUNT_IND	INVOICE_HEADER_FK	CHARGE_TYPE_FK	CHARGE_DESCRIPTION	CHARGE_FREQUENCY	ASSET_TYPE	ASSET_IDENTIFIER	DISPUTE_AMOUNT	DISPUTE_AMOUNT_IND	SERVICE_TYPE_FK	SERVICE_PROVIDER_FK	SERVICE_ORDER_NUMBER	QUOTE_NUMBER	UNIT_PRICE	UNIT_PRICE_IND	QUANTITY	UNIT_OF_MEASURE	TAX_RATE	TAX_STATEPROVINCE_REGION	TAX_NAME	TAX_TYPE	TAX_COUNTRY_FK	PARENT_ACCOUNT	CREATION_DATE	AMOUNT_DATE	DISPUTE_AMOUNT_DATE	FROM_DATE	TO_DATE	UNIT_PRICE_DATE	USOC	OTHER_DESCRIPTION	ADJUSTED_INVOICE_NUMBER	ADJUSTED_INVOICE_DATE	DISCOUNT_PERCENT	IS_DISCOUNTED	PO_NUMBER	CHARGE_START_DATE	CHARGE_END_DATE	ALLOCATION_STRING	EVENT_IDENTIFIER	VENDOR_LINE_NUMBER	ADDITIONAL_JSON";
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
				} else if (ele.equals(record.get("ACCOUNT"))) {
					temp[i++] = ExpenseData.ACCOUNT_NO;
					continue;
				} else if (ele.equals(record.get("INVOICE_HEADER_FK"))) {
					temp[i++] = ExpenseData.INVOICE_HEADER_FK;
					continue;
				} else if (ele.equals(record.get("FROM_DATE"))) {
					temp[i++] = ExpenseData.SERVICE_FROM;
					continue;
				} else if (ele.equals(record.get("TO_DATE"))) {
					temp[i++] = ExpenseData.SERVICE_TO;
					continue;
				} else if (ele.equals(record.get("ASSET_IDENTIFIER"))) {
					if (record.getRecordNumber() == 1L) {
						temp[i++] = ExpenseData.DEVICE_ASSET;
						continue;
					} else
						temp[i++] = ExpenseData.PLAN_ASSET;
					continue;
				} else if (ele.equals(record.get("ASSET_TYPE"))) {
					if (record.getRecordNumber() == 1L) {
						temp[i++] = ExpenseData.ASSET_TYPE_DEVICE;
						continue;
					} else
						temp[i++] = ExpenseData.ASSET_TYPE_PLAN;
					continue;
				} else if (ele.equals(record.get("AMOUNT")) || ele.equals(record.get("UNIT_PRICE"))) {
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
