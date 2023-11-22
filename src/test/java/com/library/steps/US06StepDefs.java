package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US06StepDefs {
    String author;
    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        BrowserUtil.waitFor(3);
        bookPage.addBook.click();
        BrowserUtil.waitFor(3);
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String Book_Name) {
        bookPage.bookName.sendKeys(Book_Name);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String Book_ISBN) {
        bookPage.isbn.sendKeys(Book_ISBN);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String Book_Year) {
        bookPage.year.sendKeys(Book_Year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String Book_Author) {
        bookPage.author.sendKeys(Book_Author);
        author = Book_Author;
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String Book_Category) {

        Select select = new Select(bookPage.categoryDropdown);
        select.selectByVisibleText(Book_Category);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitFor(1);
        bookPage.saveChanges.click();
        BrowserUtil.waitFor(2);
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {
        BrowserUtil.waitFor(4);
        String actualMessage = bookPage.toastMessage.getText();
        System.out.println("actualMessage = " + actualMessage);
        System.out.println("expectedMessage = " + expectedMessage);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String actualBookName) {
        String query = "select id,name,author from books\n" +
                "where name = '" + actualBookName + "' and author='" + author + "'\n" +
                "order by id desc";
        DB_Util.runQuery(query);
        String expectedBookName = DB_Util.getCellValue(1, 2);
        System.out.println("actualBookName = " + actualBookName);
        System.out.println("expectedBookName = " + expectedBookName);
        Assert.assertEquals(expectedBookName, actualBookName);
        BrowserUtil.waitFor(2);
        bookPage.logOut();
    }
}
