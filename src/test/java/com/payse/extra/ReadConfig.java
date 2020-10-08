package com.payse.extra;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {
		File src = new File("./PropertyFile/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getAppUrl() {
		String Url = pro.getProperty("baseUrl");
		return Url;
	}

	public String getchromepath() {
		String cpath = pro.getProperty("chromepath");
		return cpath;
	}

	public String getfirefoxpath() {
		String fpath = pro.getProperty("firefoxpath");
		return fpath;
	}

}
