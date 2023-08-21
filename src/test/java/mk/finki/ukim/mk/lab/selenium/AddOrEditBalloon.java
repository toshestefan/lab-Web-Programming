package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage addBalloon(WebDriver driver, String name, String description, String manufacturer) {
        get(driver, "/balloons/add-form");
        AddOrEditBalloon addOrEditballoon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditballoon.name.sendKeys(name);
        addOrEditballoon.description.sendKeys(description);
        addOrEditballoon.manufacturer.click();
        addOrEditballoon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditballoon.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public static BalloonsPage editBalloon(WebDriver driver, WebElement editButton, String name, String description, String manufacturer) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditballoon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditballoon.name.sendKeys(name);
        addOrEditballoon.description.sendKeys(description);
        addOrEditballoon.manufacturer.click();
        addOrEditballoon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditballoon.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }


}

