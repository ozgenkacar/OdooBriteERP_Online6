package com.Odoo.pages;

import com.Odoo.utilities.BrowserUtils;
import com.Odoo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//everything that is in common among Odoo.pages
//can go here
//for example top menu elements don't belong to specific page
//top menu appears on every single page
//so we can keep them here

public class BasePage {
    @FindBy(css = "[data-menu=\"68\"]")
    public WebElement contacts;

    @FindBy(css = "a[class='oe_menu_leaf']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;
    @FindBy(css = "[class=\"o_loading\"]")
    public WebElement loaderMask;
    @FindBy(css = "[accesskey=\"c\"]")
    public WebElement create;



    public BasePage() {
        //this method requires to provide webdriver object for @FindBy
        //and page class
        //this means this page class
        PageFactory.initElements(Driver.get(), this);
    }
    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle
        BrowserUtils.wait(2);
        BrowserUtils.waitForStaleElement(contacts);

        return contacts.getText();
    }

    public boolean waitUntilLoaderMaskDisappear() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class=\"o_web_client oe_wait\"]")));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found!");
            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }



}