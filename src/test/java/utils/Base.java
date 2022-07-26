package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Base {


    @BeforeTest
    public void setup(){
        Driver.getDriver();
        waitImplicit();
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperties("url"));
    }
    @AfterTest
    public void tearDown() {


      //  Driver.closeDriver();


    }

    public static void  waitStatic(int sec) throws InterruptedException {
        Thread.sleep(1000*sec);
    }
    public static void waitImplicit(){Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));}
    public static void waitFluent(WebElement webElement){
        Wait wait = new FluentWait(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(15)) //wait for 15 seconds
                .pollingEvery(Duration.ofMillis(800))//polling > it will check for that elements for > 5
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);

        WebElement element = (WebElement) wait.until((Function<WebDriver,WebElement>) driver -> webElement);

        try {
            element.click();
        }catch (WebDriverException e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }
    /**
     * Wait for proper steps title
     *
     * @param pageTitle
     */
    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }
    /**
     * This method will convert list of WebElements into list of string
     *stream better check if fails!?
     * @param listOfWebElements
     * @return list of strings
     */
    public static List<String> getListOfString(List<WebElement> listOfWebElements) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings= listOfWebElements.stream().map(s -> s.getText().trim()).filter(s -> s.length()>0).collect(Collectors.toList());
//        for (WebElement element : listOfWebElements) {
//            String value = element.getText().trim();
//            //if there is no text
//            //do not add this blank text into list
//            if (value.length() > 0) {
//                listOfStrings.add(value);
//            }
//        }
        return listOfStrings;
    }
    /**
     * This method is switching a specific window.
     **/
    public boolean switchWindow() {
        String parent_win = Driver.getDriver().getWindowHandle();
        Set<String> windows = Driver.getDriver().getWindowHandles();
        boolean flag =true ;
        for (String winID : windows) {

            if (!winID.equals(parent_win)) {
                Driver.getDriver().switchTo().window(winID);
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }
    /**
     * This method is scrolling a specific element.
     **/
    public  void scrollToElementJS(WebElement element){// scroll with js to elemnt
        JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    /**
     * This method is switch Iframe.
     **/

    public void switchFrame(String frameXpath) {
        Driver.getDriver().switchTo().frame(Driver.getDriver().findElement(By.xpath(frameXpath)));
    }


    /**
     * This method will wait for presence of an element.
     **/
    public void waitForPresenceOfElement(By elementBy) {
        int timeout = Integer.parseInt(ConfigurationReader.getProperties("waitTimeOut"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds( timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }


    public static void selectFromDropDown(WebElement element,String text){

        Select select = new Select(element);
        select.selectByVisibleText(text);
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method created to enter text with Explicit wait
     * @param element
     * @param text
     */
    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

    public static void enterText(WebElement element, String text){
        waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBe(element, "value", text));
        System.out.println("Entering text: " + text);
    }

}
