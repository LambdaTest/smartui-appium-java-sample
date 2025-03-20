import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.github.lambdatest.SmartUIAppSnapshot;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SmartUIAppAndroid {
    public static  String userName = System.getenv("LT_USERNAME") == null ? "YOUR_LT_USERNAME" // Add username here
            : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_LT_ACCESS_KEY" // Add accessKey here
            : System.getenv("LT_ACCESS_KEY");

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("deviceName", ".*");
        ltOptions.put("app", "lt://proverbial-android");  // Enter your app url
        ltOptions.put("isRealMobile", true);
        ltOptions.put("platformName", "Android");
        ltOptions.put("build", "Java - Android");
        ltOptions.put("name", "Sample Test Java-Android");
        ltOptions.put("w3c", true);
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("smartUI.project", "Real-Device-Project-Android");  // Enter your smartUI Project name
        caps.setCapability("lt:options", ltOptions);



        AppiumDriver driver = new AppiumDriver(
                new URL("https://"+userName+":"+accessKey+"@mobile-hub.lambdatest.com/wd/hub"), caps);
        String projectToken = "<<enter-project-token>>";
        String buildName = "<<enter-your-cutomised-build-name>>";

        SmartUIAppSnapshot smartUIAppSnapshot = new SmartUIAppSnapshot();
        Map<String, String> startConfig= new HashMap<>();
        startConfig.put("projectToken", projectToken);
        startConfig.put("buildName", buildName);

        Map<String, String> screenshotConfig = new HashMap<>();
        screenshotConfig.put("deviceName","Google pixel 9");
        screenshotConfig.put("platform","Android 15");
        try{
            smartUIAppSnapshot.start(startConfig);     //We can also call start w/o any param, values set from env vars
            driver.findElement(AppiumBy.id("com.lambdatest.proverbial:id/color")).click();
            smartUIAppSnapshot.smartuiAppSnapshot(driver, "screenshot1", screenshotConfig);
            driver.findElement(AppiumBy.id("com.lambdatest.proverbial:id/Text")).click();
            smartUIAppSnapshot.smartuiAppSnapshot(driver, "screenshot2", screenshotConfig);
            driver.findElement(AppiumBy.id("com.lambdatest.proverbial:id/toast")).click();
            smartUIAppSnapshot.smartuiAppSnapshot(driver, "screenshot3", screenshotConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            smartUIAppSnapshot.stop();
        }
    }
}