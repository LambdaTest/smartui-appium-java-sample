import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.github.lambdatest.SmartUIAppSnapshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SmartuiAppIos {
    public static  String userName = System.getenv("LT_USERNAME") == null ? "YOUR_LT_USERNAME" // Add username here
            : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_LT_ACCESS_KEY" // Add accessKey here
            : System.getenv("LT_ACCESS_KEY");
    public static  String projectToken = System.getenv("PROJECT_TOKEN") == null ? "YOUR_PROJECT_TOKEN" // Add project token here
            : System.getenv("PROJECT_TOKEN");
    public static String deviceName = System.getenv("DEVICE_NAME") == null ? ".*" // This will pick a random device for you
      : System.getenv("DEVICE_NAME");
    public static String app = System.getenv("APP") == null ? "lt://proverbial-ios" // Sample app
      : System.getenv("APP");


    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("deviceName", deviceName);
        ltOptions.put("app", app);  // Enter your app url
        ltOptions.put("isRealMobile", true);
        ltOptions.put("platformName", "iOS");
        ltOptions.put("build", "Java - iOS");
        ltOptions.put("name", "Sample RD Test Java-iOS");
        caps.setCapability("lt:options", ltOptions);

        AppiumDriver driver = new AppiumDriver(
                new URL("https://"+userName+":"+accessKey+"@mobile-hub.lambdatest.com/wd/hub"), caps);

        SmartUIAppSnapshot smartUIAppSnapshot = new SmartUIAppSnapshot();
        Map<String, String> screenshotConfig = new HashMap<>();
        screenshotConfig.put("deviceName","iPhone 16");
        screenshotConfig.put("platform","iOS 17");
        try{
            smartUIAppSnapshot.start();
            driver.findElement(AppiumBy.id("color")).click();
            smartUIAppSnapshot.smartuiAppSnapshot(driver, "screenshot1", screenshotConfig);
            Thread.sleep(3000);
            driver.navigate().back();
            Thread.sleep(1000);
            driver.findElement(AppiumBy.id("Text")).click();
            smartUIAppSnapshot.smartuiAppSnapshot(driver, "screenshot2", screenshotConfig);
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            smartUIAppSnapshot.stop();
            driver.quit();
        }
    }

}