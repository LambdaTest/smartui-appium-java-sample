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


    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("deviceName", ".*");
        ltOptions.put("app", "lt://proverbial-ios");  // Enter your app url
        ltOptions.put("isRealMobile", true);
        ltOptions.put("platformName", "iOS");
        ltOptions.put("build", "Java - iOS");
        ltOptions.put("name", "Sample RD Test Java-iOS");
        ltOptions.put("w3c", true);
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("smartUI.project", "Real-Device-Project-iOS");  // Enter your smartUI Project name
        caps.setCapability("lt:options", ltOptions);

        Map<String, String> startConfig= new HashMap<>();
        startConfig.put("projectToken", projectToken);

        AppiumDriver driver = new AppiumDriver(
                new URL("https://"+userName+":"+accessKey+"@mobile-hub.lambdatest.com/wd/hub"), caps);

        SmartUIAppSnapshot smartUIAppSnapshot = new SmartUIAppSnapshot();
        Map<String, String> screenshotConfig = new HashMap<>();
        screenshotConfig.put("deviceName","iPhone 16");
        screenshotConfig.put("platform","iOS 17");
        try{
            smartUIAppSnapshot.start(startConfig); //We can also call start w/o options it'll take projectToken from env var
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
        }
    }

}