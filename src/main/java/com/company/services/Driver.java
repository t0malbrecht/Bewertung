package com.company.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class Driver {

    private static List<String> list;
    private WebDriver driver;
    private JavascriptExecutor js;
    private int index;
    private String XPath;

        public void setUp(){
            System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
            driver = new ChromeDriver();
            js = (JavascriptExecutor) driver;
        }


    public void getPage() throws InterruptedException{
        driver.get(list.get(index));
        Thread.sleep(getWaitTime());
    }
    public void getPageFast() throws InterruptedException{
        driver.get(list.get(index));
    }

        public String getfittingXPath() throws IndexOutOfBoundsException{
            List<String> XPathOptions = Arrays.asList("ABC2","//img[@alt= '5 Stars']",
                    "//img[@title= '5 Sterne']",
                    "//img[@alt= '5']");
            int size = 0;
            int temp = 0;
            while (size == 0 && temp < XPathOptions.size() ) {
                temp++;
                size = driver.findElements(By.xpath(XPathOptions.get(temp))).size();
                }
            return XPathOptions.get(temp);
        }
        public void findElement() throws InterruptedException, IndexOutOfBoundsException {
            XPath = getfittingXPath();
            WebElement Element1 = driver.findElement(By.xpath(XPath));
            js.executeScript("arguments[0].scrollIntoView();", Element1);
            Thread.sleep(getWaitTime());
        }

    public void findElementFast() throws InterruptedException, IndexOutOfBoundsException {
        XPath = getfittingXPath();
        WebElement Element1 = driver.findElement(By.xpath(XPath));
        js.executeScript("arguments[0].scrollIntoView();", Element1);
    }

        public void clickElement()throws InterruptedException{
            driver.findElement(By.xpath(XPath)).click();
            Thread.sleep(getWaitTime());
        }

        public String getCurrentURL() throws IndexOutOfBoundsException{
            return (String) list.get(index);
        }

        public int getSizeOfList(){
            return list.size();
        }

        public long getWaitTime(){
            double temp = (2000 + Math.random()*10000);
            long waitTime = (long) temp;
            return waitTime;
        }

        public void closeDriver(){
            driver.close();
        }

        public void countIndex(){
            index++;
        }
        public void setList(List list){
            this.list = list;
        }
}
