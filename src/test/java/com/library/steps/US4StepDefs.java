package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class US4StepDefs {
   public static String searchedBook;

    BookPage bookPage = new BookPage();
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String searchText) {
        bookPage.search.sendKeys(searchText + Keys.ENTER);
       this.searchedBook=searchText;

    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBook(searchedBook).click();

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String query="select * from books\n" +
                "where  description like 'Prac%'";
        DB_Util.runQuery(query);
        String expectedBookName=DB_Util.getCellValue(1,"name");
        String expectedISBN=DB_Util.getCellValue(1,"isbn");
        String expectedYear=DB_Util.getCellValue(1,"year");
        String expectedAuthor=DB_Util.getCellValue(1,"author");
        String expectedDescription=DB_Util.getCellValue(1,"description");

//        System.out.println(expectedBookName);
//        System.out.println(expectedISBN);
//        System.out.println(expectedYear);
//        System.out.println(expectedAuthor);
//        System.out.println(expectedDescription);

        BrowserUtil.waitFor(2);

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");

//        System.out.println(actualBookName);
//        System.out.println(actualISBN);
//        System.out.println(actualYear);
//        System.out.println(actualAuthor);
//        System.out.println(actualDescription);

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedAuthor,actualAuthor);
        Assert.assertEquals(expectedDescription,actualDescription);

    }
}
