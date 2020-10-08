package com.payse.tcase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.payse.extra.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseUrl = readconfig.getAppUrl();
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) 
	{

		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getfirefoxpath());
			FirefoxOptions opt = new FirefoxOptions();
			opt.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver();
		} 
		else if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getchromepath());
			ChromeOptions opt = new ChromeOptions();
			opt.setAcceptInsecureCerts(false);
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/SereenShots/" + name + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}

	public void pdfCapture(WebDriver driver, String name) throws DocumentException, MalformedURLException, IOException {
		byte[] input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Document doc = new Document();
		String output=System.getProperty("user.dir") + "/CustomPdfReport/" + name + ".pdf";
		FileOutputStream fos = new FileOutputStream(output);
		PdfWriter writer = PdfWriter.getInstance(doc, fos);
		writer.open();
		doc.open();
		Image img = Image.getInstance(input);
		img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		doc.add(img);
		doc.add(new Paragraph(" "));
		doc.close();
		writer.close();
		System.out.println("PDF Generated");
	}

	public void ExcelImplement_Pass() throws IOException
	{

		@SuppressWarnings("resource")
		XSSFWorkbook workbook=new XSSFWorkbook();	
		XSSFSheet sheet=workbook.createSheet("TestData");

		Map<String, Object[]> data=new TreeMap<String, Object[]>();

		data.put("1", new Object[] {"Passed"});

		Set<String> keyset=data.keySet();

		int rownum=1;

		for(String key:keyset)
		{
			Row row=sheet.createRow(rownum++);					
			Object[] objArr=data.get(key);					
			int cellnum=0;

			for(Object obj:objArr)
			{
				Cell cell=row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}					
		}

		FileOutputStream fOut=new FileOutputStream(".//src//test//resources//TestData//TransitionTestReport.xlsx");
		workbook.write(fOut);				
		fOut.close();			
		System.out.println("Test Relult Captured in Excel.");

	}
	
	public void ExcelImplement_Fail() throws IOException
	{

		@SuppressWarnings("resource")
		XSSFWorkbook workbook=new XSSFWorkbook();	
		XSSFSheet sheet=workbook.createSheet("TestData");

		Map<String, Object[]> data=new TreeMap<String, Object[]>();

		data.put("1", new Object[] {"Failed"});

		Set<String> keyset=data.keySet();

		int rownum=1;

		for(String key:keyset)
		{
			Row row=sheet.createRow(rownum++);					
			Object[] objArr=data.get(key);					
			int cellnum=1;

			for(Object obj:objArr)
			{
				Cell cell=row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}					
		}

		FileOutputStream fOut=new FileOutputStream(".//src//test//resources//TestData//TransitionTestReport.xlsx");
		workbook.write(fOut);				
		fOut.close();			
		System.out.println("Test Relult Captured in Excel.");
	}

}
