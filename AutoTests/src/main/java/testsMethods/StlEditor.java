package testsMethods;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StlEditor {

	public static WebDriver driver = CommonFunctions.driver;

	// Method to verify that the rotation angle parameters has been changed in the corresponding field after ticking off
	// the model and the tick off mark has been replaced
	// Method also verifies that if the first model is ticked off the saving is forbidden
	public static void checkRotationValue(int modelRotationOptionNumber) {
		switch (modelRotationOptionNumber) {
		case 1:
			Assert.assertEquals(getFiValue(), "0");
			Assert.assertEquals(getThitaValue(), "0");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		case 2:
			Assert.assertEquals(getFiValue(), "90");
			Assert.assertEquals(getThitaValue(), "0");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		case 3:
			Assert.assertEquals(getFiValue(), "0");
			Assert.assertEquals(getThitaValue(), "90");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		case 4:
			Assert.assertEquals(getFiValue(), "-90");
			Assert.assertEquals(getThitaValue(), "0");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		case 5:
			Assert.assertEquals(getFiValue(), "0");
			Assert.assertEquals(getThitaValue(), "-90");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		case 6:
			Assert.assertEquals(getFiValue(), "0");
			Assert.assertEquals(getThitaValue(), "-180");
			Assert.assertEquals(getPsiValue(), "0");
			break;
		}
	}

	// The following 3 methods are for obtaining the current rotation parameters
	public static String getFiValue() {
		return driver.findElement(By.xpath("//input[@id='rotate_0']")).getAttribute("value");
	}

	public static String getThitaValue() {
		return driver.findElement(By.xpath("//input[@id='rotate_1']")).getAttribute("value");
	}

	public static String getPsiValue() {
		return driver.findElement(By.xpath("//input[@id='rotate_2']")).getAttribute("value");
	}

	// Method to tick off the model
	public static void TickOffModel(int modelToTickOffNumber) {
		switch (modelToTickOffNumber) {
		case 1:
			driver.findElement(By.xpath("//div[@id='0_0_0']")).click();
			break;
		case 2:
			driver.findElement(By.xpath("//div[@id='90_0_0']")).click();
			break;
		case 3:
			driver.findElement(By.xpath("//div[@id='0_90_0']")).click();
			break;
		case 4:
			driver.findElement(By.xpath("//div[@id='-90_0_0']")).click();
			break;
		case 5:
			driver.findElement(By.xpath("//div[@id='0_-90_0']")).click();
			break;
		case 6:
			driver.findElement(By.xpath("//div[@id='0_-180_0']")).click();
			break;
		}
	}

	// This method verifies that the correct image is ticked off
	public static void checkModelIsTickedOff(int modelToCheckNumber) {
		switch (modelToCheckNumber) {
		case 1:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_0_0']")).getAttribute("class"), "rot_sel");
			break;
		case 2:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_90_0']")).getAttribute("class"), "rot_sel");
			break;
		case 3:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_0_90']")).getAttribute("class"), "rot_sel");
			break;
		case 4:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_-90_0']")).getAttribute("class"), "rot_sel");
			break;
		case 5:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_0_-90']")).getAttribute("class"), "rot_sel");
			break;
		case 6:
			Assert.assertEquals(StlEditor.driver.findElement(By.xpath("//span[@id='rot_0_-180']")).getAttribute("class"), "rot_sel");
			break;
		}
	}

	// The following 4 methods are for obtaining the values of the fields: scale, x, y, z
	public static Double getScale() {
		return Double.parseDouble(findScale().getAttribute("value"));
	}

	public static Double getX() {
		return Double.parseDouble(findX().getAttribute("value"));
	}

	public static Double getY() {
		return Double.parseDouble(findY().getAttribute("value"));
	}

	public static Double getZ() {
		return Double.parseDouble(findZ().getAttribute("value"));
	}

	// Method to tick off the Resize check-box
	public static void tickOffResize() {
		driver.findElement(By.id("do_resize")).click();
	}

	public static WebElement findScale() {
		WebElement element = driver.findElement(By.id("size_prc"));
		return element;
	}

	public static WebElement findX() {
		WebElement element = driver.findElement(By.id("size_x"));
		return element;
	}

	public static WebElement findY() {
		WebElement element = driver.findElement(By.id("size_y"));
		return element;
	}

	public static WebElement findZ() {
		WebElement element = driver.findElement(By.id("size_z"));
		return element;
	}

	// This method verifies that it is impossible to resize the model if the resize check-box is disabled
	public static void verifyResizeCheckbox() {
		Assert.assertFalse(driver.findElement(By.id("do_resize")).isSelected());
		Assert.assertEquals(findScale().getAttribute("readonly"), "true");
		Assert.assertEquals(findX().getAttribute("readonly"), "true");
		Assert.assertEquals(findY().getAttribute("readonly"), "true");
		Assert.assertEquals(findZ().getAttribute("readonly"), "true");
	}

	public static void checkResize(double newX, double newY, double newZ) {
		Assert.assertEquals(getX(), newX);
		Assert.assertEquals(getY(), newY);
		Assert.assertEquals(getZ(), newZ);
	}

	// This method verifies the correctness of the scale change
	public static double randomNumberGeneration() {
		// New random scale generation
		Random r = new Random();
		double newRandomNumber = r.nextInt(20000) / 100.0;
		System.out.println("\nNew random is " + newRandomNumber);
		return newRandomNumber;
	}

	public static double calculateScale(double oldSize, double newSize) {
		double newScale = newSize * 100 / oldSize;
		return newScale;
	}

	public static double calculateScaleRoundedOff(double oldSize, double newSize) {
		double newScale = newSize * 100 / oldSize;
		newScale = Math.rint(100.0 * newScale) / 100.0;
		return newScale;
	}

	public static Double calculateSize(double oldSize, double newScale) {
		double newSize = oldSize * newScale / 100;
		newSize = Math.rint(100.0 * newSize) / 100.0;
		return newSize;
	}

	public static void waitUntilValueChanges() {
		if (getX() == 0.0 || getY() == 0.0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void checkResizeStart(String modelId, int i) {
		StlEditor.openStlEditor(modelId);
		if (i > 6) {
			StlEditor.verifyResizeCheckbox();
		}
		StlEditor.tickOffResize();
	}

	public static void checkScaleChange(int i) {
		double newData = randomNumberGeneration();
		double newScale = newData;
		double newX = calculateSize(getX(), newScale);
		double newY = calculateSize(getY(), newScale);
		double newZ = calculateSize(getZ(), newScale);
		System.out.println("Check the values before saving: \noldScale = 100%; newScale = " + newScale);
		System.out.println("\nold_X = " + getX() + "; new_X = " + newX);
		System.out.println("\nold_Y = " + getY() + "; new_Y = " + newY);
		System.out.println("\nold_Z = " + getZ() + "; new_Z = " + newZ);
		findScale().clear();
		findScale().sendKeys("" + newScale);
		waitUntilValueChanges();
		checkSaveChanges(newX, newY, newZ, i);
	}

	public static void checkXchange(int i) {
		double oldX = getX();
		double newData = randomNumberGeneration();
		double newX = newData;
		double newScale = calculateScale(getX(), newX);
		double newY = calculateSize(getY(), newScale);
		double newZ = calculateSize(getZ(), newScale);
		findX().clear();
		findX().sendKeys("" + newX);
		waitUntilValueChanges();
		Assert.assertEquals(getScale(), calculateScaleRoundedOff(oldX, newX));
		checkSaveChanges(newX, newY, newZ, i);
	}

	public static void checkYchange(int i) {
		double oldY = getY();
		double newData = randomNumberGeneration();
		double newY = newData;
		double newScale = calculateScale(getY(), newY);
		double newX = calculateSize(getX(), newScale);
		double newZ = calculateSize(getZ(), newScale);
		findY().clear();
		findY().sendKeys("" + newY);
		waitUntilValueChanges();
		Assert.assertEquals(getScale(), calculateScaleRoundedOff(oldY, newY));
		checkSaveChanges(newX, newY, newZ, i);
	}

	public static void checkZchange(int i) {
		double oldZ = getZ();
		double newData = randomNumberGeneration();
		double newZ = newData;
		double newScale = calculateScale(getZ(), newZ);
		double newX = calculateSize(getX(), newScale);
		double newY = calculateSize(getY(), newScale);
		findZ().clear();
		findZ().sendKeys("" + newZ);
		waitUntilValueChanges();
		Assert.assertEquals(getScale(), calculateScaleRoundedOff(oldZ, newZ));
		checkSaveChanges(newX, newY, newZ, i);
	}

	public static void checkSaveChanges(double newX, double newY, double newZ, int i) {
		// Check that the size has been automatically changed
		checkResize(newX, newY, newZ);
		FunctionsWithFiles.clickSave();
		String newFileName = FunctionsWithFiles.findNewFileName(i);
		System.out.println(newFileName);
		String newModelId = FunctionsWithFiles.findModelId(newFileName);
		System.out.println(newModelId);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[12]/div/div[2]/form/table[2]/tbody/tr[" + (i + 1) + "]/td[1]/a/img")));
		StlEditor.openStlEditor(newModelId);
		// Check that changes have been saved, scale should be set to = 100
		Assert.assertEquals(getScale(), 100.0);
		checkResize(newX, newY, newZ);
		System.out.println("new_X = " + getX()+"; new_Y = " + getY() + "; new_Z = " + getZ());
		i++;
		closeStlEditor();
	}

	public static void openStlEditor(String modelId) {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//input[@data-file-id='" + modelId + "']")).click();
		System.out.println("clicked");
		driver.findElement(By.xpath("//img[@alt='STL Editor']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[style='width: 300px; height: 250px;']")));
		checkModelIsTickedOff(1);
		checkRotationValue(1);
	}

	public static void closeStlEditor() {
		driver.findElement(By.id("stl-editor-close")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
