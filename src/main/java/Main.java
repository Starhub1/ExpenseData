import java.io.IOException;

import Data.ExpenseData;
import Files.BillJob;
import Files.InvoiceAccount;
import Files.InvoiceAsset;
import Files.InvoiceHeader;
import Files.InvoiceLineItem;
import Files.InvoiceSummary;
import Files.UsageDetails;

public class Main {

	public static void main(String[] args) throws IOException {
		ExpenseData.init();
		BillJob.run();
		InvoiceAccount.run();
		InvoiceAsset.run();
		InvoiceHeader.run();
		InvoiceLineItem.run();
		InvoiceSummary.run();
		UsageDetails.run();
		System.out.println("Susccessfully Modified the data");
	}
}
