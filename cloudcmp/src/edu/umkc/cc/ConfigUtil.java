package edu.umkc.cc;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class ConfigUtil {	
	private static ConfigUtil instance;
	private static Properties p;
	
	public static final String AMAZON_LINUX_COMPUTE_MICRO = "amazon.linux.compute.micro";
	public static final String GOOGLE_LINUX_COMPUTE_MICRO = "google.linux.compute.micro";
	public static final String MICROSOFT_LINUX_COMPUTE_MICRO = "microsoft.linux.compute.micro";
	public static final String STORAGE_TABLE_KEEP = "storage.table.populate.keep";
	public static final String STORAGE_TABLE_ROW_KEEP = "storage.table.row.keep";
	
	private ConfigUtil(){
		p = new Properties();
		try {
//			System.out.println(getWebInfPath());
			p.load(new FileInputStream(getWebInfPath()+ "pricing.conf"));
//			System.out.println("3");
		}catch (Exception e) { System.out.println("error message: " + e.getMessage());}
	}
	
	public static ConfigUtil getInstance() {
		if(instance == null) instance = new ConfigUtil();
		return instance;
	}
	
	public boolean getkeepTableVal() {
		return Boolean.parseBoolean(this.p.getProperty(STORAGE_TABLE_KEEP));
	}
	
	public boolean getkeepTableRowVal() {
		return Boolean.parseBoolean(this.p.getProperty(STORAGE_TABLE_ROW_KEEP));
	}
	
	public String getUnitPrice(String providerKey) {
		return p.getProperty(providerKey, "0");
	}

	private String getWebInfPath() throws UnsupportedEncodingException{
		URL r = this.getClass().getResource("/");
		String fullpath = URLDecoder.decode(r.getFile(), "UTF-8");
		if(System.getProperty("os.name").startsWith("Windows")) {
			if(fullpath.startsWith("/")) fullpath = fullpath.substring(1);
		}
		if(fullpath.endsWith("classes/")) fullpath = fullpath.replaceFirst("classes/", "");
		return fullpath;
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
	}
}
