package com.company.services;

import com.company.gui.inProgress.InProgressPresenter;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.util.List;

public class SetRating implements Runnable {
    private static int index;
    private InProgressPresenter presenter;


    public void run() {
        presenter = InProgressPresenter.getInstance();
        CSVReader reader = new CSVReader();
        Driver driver = new Driver();
        driver.setUp();
        try {
            driver.setList(reader.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        presenter.setSizeOfArray(driver.getSizeOfList());
        index = driver.getSizeOfList();
        int status = 0;

        while (index > 0) {
            try {
                driver.getPage();
                driver.findElement();
                driver.clickElement();

            } catch (IndexOutOfBoundsException | ElementNotVisibleException e) {
                status = 1;

            } catch (WebDriverException | InterruptedException e) {
                status = 2;

            } finally{
            }

            try {
                driver.getPageFast();
                driver.findElementFast();
                presenter.updateOutputFailure(driver.getCurrentURL() + ": Failed to verify Referrer");

            } catch (IndexOutOfBoundsException | ElementNotVisibleException | InterruptedException e) {
                if(status==0){
                    presenter.updateOutputSuccess(driver.getCurrentURL() + ": Rating erfolgreich gesetzt");
                }else {
                    presenter.updateOutputFailure(driver.getCurrentURL() + ": Keine Rating-Option gefunden");
                }
            } catch (WebDriverException e) {
                presenter.updateOutputFailure(driver.getCurrentURL() + ": Webseite nicht erreichbar");
            } finally{
                index--;
                driver.countIndex();
            }
        }
        driver.closeDriver();}

}
