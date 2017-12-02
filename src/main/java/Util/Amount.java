package Util;

import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

import Data.ExpenseData;

public class Amount {
	public static String[] get() throws IOException {
		String filename = "InvoiceLineItem.csv";
		String h = "TENANT_FK	ACCOUNT	BILLING_END_DATE	AMOUNT	AMOUNT_IND	INVOICE_HEADER_FK	CHARGE_TYPE_FK	CHARGE_DESCRIPTION	CHARGE_FREQUENCY	ASSET_TYPE	ASSET_IDENTIFIER	DISPUTE_AMOUNT	DISPUTE_AMOUNT_IND	SERVICE_TYPE_FK	SERVICE_PROVIDER_FK	SERVICE_ORDER_NUMBER	QUOTE_NUMBER	UNIT_PRICE	UNIT_PRICE_IND	QUANTITY	UNIT_OF_MEASURE	TAX_RATE	TAX_STATEPROVINCE_REGION	TAX_NAME	TAX_TYPE	TAX_COUNTRY_FK	PARENT_ACCOUNT	CREATION_DATE	AMOUNT_DATE	DISPUTE_AMOUNT_DATE	FROM_DATE	TO_DATE	UNIT_PRICE_DATE	USOC	OTHER_DESCRIPTION	ADJUSTED_INVOICE_NUMBER	ADJUSTED_INVOICE_DATE	DISCOUNT_PERCENT	IS_DISCOUNTED	PO_NUMBER	CHARGE_START_DATE	CHARGE_END_DATE	ALLOCATION_STRING	EVENT_IDENTIFIER	VENDOR_LINE_NUMBER	ADDITIONAL_JSON";
		String[] header = h.split("\\s+");

		// Reader
		Iterable<CSVRecord> records = CSV.read(filename, header);
		double total = Double.parseDouble(ExpenseData.CURRENT_CHARGES);
		double temp = 0;
		double[] res = new double[5];
		int i = 0;
		for (CSVRecord record : records) {
			temp = temp + Double.parseDouble(record.get("AMOUNT"));
			res[i++] = Double.parseDouble(record.get("AMOUNT"));
		}
		double diff = total - temp;
		diff = diff / 3;
		res[1] = res[1] + diff;
		res[2] = res[2] + diff;
		res[3] = res[3] + diff;
		String[] finalres = new String[5];
		for (int j = 0; j < res.length; j++) {
			finalres[j] = String.valueOf(res[j]);
			// finalres[j] = String.format("%.2f", res[j]);
		}
		return finalres;
	}
}
