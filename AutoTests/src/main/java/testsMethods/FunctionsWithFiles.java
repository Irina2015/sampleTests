package testsMethods;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testsData.CommonData;

public class FunctionsWithFiles{

	public static WebDriver driver = CommonFunctions.driver;

	// auxiliary method for the file uploading
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public static void OpenPackage(String packageName){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//span[contains(text(), '" + packageName + "')]")).click();
	}
	

	// File uploading
	public static void fileUpload(String pathToFileUpload, String fileName) throws Exception {
		driver.get(CommonData.uploadPageUrl);
		driver.findElement(By.id("clickable")).click();
		// Runtime.getRuntime().exec("D:\\work\\stl\\Uploading.exe");
		setClipboardData(pathToFileUpload + fileName);
		// native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		System.out.println("Start uploading");
	}

	// Method for finding a file ID by its name
	public static String findModelId(String fileName) {
		WebElement element = driver.findElement(By.xpath("//input[@data-model_name='" + fileName + "']"));
		String modelId = element.getAttribute("data-model_id");
		System.out.println(modelId);
		return modelId;
	}

	// Method for finding the name of the modified file after it has been changed in STL Editor
	public static String findNewFileName(int XPathVariable) {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int Variable = XPathVariable + 1;
		WebElement element = driver.findElement(By.xpath("/html/body/div[12]/div/div[2]/form/table[2]/tbody/tr[" + Variable + "]/td[4]/span/span"));
		String str = element.getAttribute("data-file-id");
		System.out.println(str);
		String newFileName = CommonData.fileName.substring(0, CommonData.fileName.length() - 4) + "_" + str + ".stl";
		return newFileName;
	}

	public static void deleteFileByXPath(String XPath) {
		driver.findElement(By.xpath(XPath)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/div[16]/div[2]/input[1]")).click();
	}

	public static void clickSave() {
		driver.findElement(By.id("postbtn")).click();
	}

}
