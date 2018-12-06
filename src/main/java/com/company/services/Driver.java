package com.company.services;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Arrays;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;


public class Driver {

    private static List<String> list;
    public static WebDriver driver;
    private JavascriptExecutor js;
    private int index;
    private String XPath;

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.get("http://www.get-myip.com/");
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
