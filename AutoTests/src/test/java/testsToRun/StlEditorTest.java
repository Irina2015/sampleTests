package testsToRun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testsMethods.*;
import testsData.*;

public class StlEditorTest {

	private WebDriver driver = null;

	@BeforeClass
	public void setup() {
		driver = CommonFunctions.loadWebBrowser(CommonData.browser);
	}

	@Test
	public void test1() throws Exception {

		// Login
		User.logIn(CommonData.user2.getEmail(), CommonData.user2.getPassword());

		// File Uploading launching
		FunctionsWithFiles.fileUpload(CommonData.pathToFileUpload, CommonData.fileName);

		// Waiting for the file to be fully uploaded and the button "Go to My Files" appearing
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='go']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-model_name='" + CommonData.fileName + "']")));
	}

	@Test
	// Check the rotation feature
	public void test2() {

		// Identification of the uploaded file ID
		String modelId = FunctionsWithFiles.findModelId(CommonData.fileName);

		// Open STL Editor. Waiting for the file to be fully downloaded and pretest
		// Check that after ticking off the model (pictures from 1 to 6) its parameters are correct
		// Verify that after selecting another rotation angle the model has been saved with new parameters
		// To verify rotation angle changes screenshots of the saved models will be captured
		for (int i = 1; i <= 6; i++) {
			StlEditor.openStlEditor(modelId);
			StlEditor.TickOffModel(i);
			// In case of clicking on the first model no changes have been made
			// So STL Editor shouldn't perform any saving activity
			// In this case the appearing alert window needs to be closed and the second loop will be launched
			if (i == 1) {
				FunctionsWithFiles.clickSave();
				CommonFunctions.acceptAlert();
				i++;
				StlEditor.TickOffModel(i);
			}
			StlEditor.checkModelIsTickedOff(i);
			StlEditor.checkRotationValue(i);
			FunctionsWithFiles.clickSave();
			String newFileName = FunctionsWithFiles.findNewFileName(i);
			System.out.println("New file name = " + newFileName);
			String newModelId = FunctionsWithFiles.findModelId(newFileName);
			System.out.println("New model ID = " + newModelId);
			StlEditor.openStlEditor(newModelId);
			CommonFunctions.takeScreenshot(i);
			StlEditor.closeStlEditor();
		}
		// Check the resize feature

		int i = 7;
		// Check the Scale change
		StlEditor.checkResizeStart(modelId, i);
		StlEditor.checkScaleChange(i);
		i++;
		System.out.println("i = " + i);

		// Check the X change
		StlEditor.checkResizeStart(modelId, i);
		StlEditor.checkXchange(i);
		i++;
		
		// Check the Y change
		StlEditor.checkResizeStart(modelId, i);
		StlEditor.checkYchange(i);
		i++;
		
		// Check the Z change
		StlEditor.checkResizeStart(modelId, i);
		StlEditor.checkZchange(i);
		
		// Deleting all created files to clear the workspace for the next test
		for (int j = i; j > 0; j--) {
			FunctionsWithFiles.deleteFileByXPath("/html/body/div[12]/div/div[2]/form/table[2]/tbody/tr[1]/td[12]/img");
		}

	}

	@AfterClass
	public void ClosingBrowser() {
		CommonFunctions.closeWebBrowser();
	}

}
