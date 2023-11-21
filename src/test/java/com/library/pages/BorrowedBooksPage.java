package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BorrowedBooksPage extends BasePage{


    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

    @FindBy(xpath = "//tbody//td[1]")
    public List<WebElement> allBorrowedBooksAction;

    @FindBy(xpath = "//tbody//td[5]")
    public List<WebElement> allBorrowedBooksReturnDate;

    @FindBy(xpath = "//tbody//td[3]")
    public List<WebElement> allBorrowedBooksBorrowedDate;




}
