package testsData;

import java.text.SimpleDateFormat;
import java.util.Date;

import testsMethods.User;

public class CommonData {

	static Date myDate = new Date(System.currentTimeMillis());
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
	static String dateString = (String)sdf.format(myDate);
	
	public static String browser = "Mozilla"; // You can choose between "Chrome", "Mozilla" and "IE"
	
	public static final String uploadPageUrl = "https://cloud1.3dprinteros.com/upload/";
	public static final String signInPageUrl = "https://cloud1.3dprinteros.com";
	public static final String afterSignInPageUrl = "https://cloud1.3dprinteros.com/myfiles#signIn";
	
	public static User user1 = new User("user1autotesting@gmail.com", "password1");
	public static User user2 = new User("user2autotesting@gmail.com", "password2");
	
	public static final String pathToFileUpload = "D:\\work\\stl\\";
	public static final String pathToSaveScreenshots = "D:\\work\\screens\\"+dateString+"screenshot";
	public static final String pathToIEDriver = "D:\\MyPrograms\\selenium-2.33.0\\drive\\IEDriverServer.exe";
	public static final String pathToChromeDriver = "D:\\MyPrograms\\selenium-2.33.0\\drive\\chromedriver.exe";
	public static String fileName = "cuore.stl";	
	
}

