package com.qa.util;

import com.qa.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import  static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class BrowserUtils {



    public void mySendKeysMethod (By locator, String sendText){

        
        WebElement element = DriverFactory.getDriver().findElement(locator);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(sendText);

    }

    public  boolean isDisplayed (By locator){

        return waitUntilVisibilityOf(locator).isDisplayed();

    }

    public  WebElement waitUntilVisibilityOf (By locator){

        WebElement element = DriverFactory.getDriver().findElement(locator);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        return wait.until(ExpectedConditions.visibilityOf(element));

    }


    public  boolean isEnabled (By locator){

        return waitUntilVisibilityOf(locator).isEnabled();

    }

    public  void myClickMethod (By locator){

        WebElement element = DriverFactory.getDriver().findElement(locator);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

    }

    public  String myGetElementTextMethods(By locator){

        WebElement element = DriverFactory.getDriver().findElement(locator);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.visibilityOf(element)).getText();

        return element.getText();
    }

    public  String myGetCurrentUrlMethod(){

        BrowserUtils.waitForPageToLoad(3000);
        String currentUrl =DriverFactory.getDriver().getCurrentUrl();
        return currentUrl;
    }




    /*
     * switches to new window by the exact title
     */
    public  void switchToWindow(String targetTitle) {
        String origin = DriverFactory.getDriver().getWindowHandle();
        for (String handle : DriverFactory.getDriver().getWindowHandles()) {
            DriverFactory.getDriver().switchTo().window(handle);
            if (DriverFactory.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        DriverFactory.getDriver().switchTo().window(origin);
    }

    /**
     * Moves the mouse to given element
     *
     * @param element on which to hover
     */
    public  void hover(WebElement element) {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * return a list of string from a list of elements
     *
     * @param list of webelements
     * @return list of string
     */
    public  List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param locator
     * @return list of strings
     */
    public  List<String> getElementsText(By locator) {

        List<WebElement> elems = DriverFactory.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public  String getSingleElementText(By locator){

        String returnMyText= DriverFactory.getDriver().findElement(locator).getText();
        return returnMyText;
    }

    /**
     * Performs a pause
     *
     * @param seconds
     */
    public  void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public  WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator
     * @param timeout
     * @return
     */
    public  WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public  WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param timeout
     * @return
     */
    public  WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     *
     * @param by
     * @throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public  void verifyElementDisplayed(By by) {
        try {
            assertTrue("Element not visible: " + by, DriverFactory.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            fail("Element not found: " + by);

        }
    }

    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     *
     * @param by
     * @throws AssertionError the element matching the provided locator is displayed
     */
    public  void verifyElementNotDisplayed(By by) {
        try {
            assertFalse("Element should not be visible: " + by, DriverFactory.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }


    /**
     * Verifies whether the element is displayed on page
     *
     * @param element
     * @throws AssertionError if the element is not found or not displayed
     */
    public  void verifyElementDisplayed(WebElement element) {
        try {
            assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            fail("Element not found: " + element);

        }
    }


    /**
     * Waits for element to be not stale
     *
     * @param element
     */
    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public  void clickWithJS(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", element);
    }


    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public  void scrollToElement(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public  void doubleClick(WebElement element) {
        new Actions(DriverFactory.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     *
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public  void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    /**
     * Highlighs an element by changing its background and border color
     *
     * @param element
     */
    public  void highlight(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    /**
     * Checks or unchecks given checkbox
     *
     * @param element
     * @param check
     */
    public  void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    /**
     * attempts to click on provided element until given time runs out
     *
     * @param element
     * @param timeout
     */
    public  void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }


    /**
     * This method creates a random string
     *
     * @param length number of letters in string
     * @return random string
     */

    public  String getRandomString(int length) {
        String possibleLetters = "abcdefgijklmopqrstuvwxyz";
        char[] rndWord = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * possibleLetters.length());
            rndWord[i] = possibleLetters.charAt(rand);
        }
        return new String(rndWord);
    }

    /**
     * executes the given JavaScript command on given web element
     *
     * @param element
     */
    public  void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getDriver();
        jse.executeScript(command, element);

    }

    /**
     * executes the given JavaScript command on given web element
     *
     * @param command
     */
    public  void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getDriver();
        jse.executeScript(command);

    }

    /**
     *   In our isNumeric() method, we're just checking for values that are of type Double,
     * Integer, Float, Long, and large numbers by using any of the parse methods.
     *
     */
    public  boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            /*int i = Integer.parseInt(strNum);
            float f = Float.parseFloat(strNum);
            long l = Long.parseLong(strNum);*/
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
