package Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ExpenseData {

	public static String JOB_ID;

	public static String TENANT;
	public static String VENDOR;

	public static String CURRENT_CHARGES;
	public static String INVOICE_HEADER_FK;
	public static String ACCOUNT_NO;

	public static String SERVICE_FROM;
	public static String SERVICE_TO;
	public static String INVOICE_DATE;
	// InvoiceAsset
	public static String PLAN_ASSET;
	public static String DEVICE_ASSET;
	public static String ASSET_TYPE_PLAN;
	public static String ASSET_TYPE_DEVICE;

	public static void init() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileReader("Input.properties"));

		JOB_ID = prop.getProperty("JOB_ID");

		TENANT = prop.getProperty("TENANT");
		VENDOR = prop.getProperty("VENDOR");

		SERVICE_FROM = prop.getProperty("SERVICE_FROM");
		SERVICE_TO = prop.getProperty("SERVICE_TO");
		INVOICE_DATE = prop.getProperty("INVOICE_DATE");

		CURRENT_CHARGES = prop.getProperty("CURRENT_CHARGES");
		INVOICE_HEADER_FK = prop.getProperty("INVOICE_HEADER_FK");
		ACCOUNT_NO = prop.getProperty("ACCOUNT_NO");

		PLAN_ASSET = prop.getProperty("PLAN_ASSET");
		DEVICE_ASSET = prop.getProperty("DEVICE_ASSET");
		ASSET_TYPE_PLAN = prop.getProperty("ASSET_TYPE_PLAN");
		ASSET_TYPE_DEVICE = prop.getProperty("ASSET_TYPE_DEVICE");

	}

}
