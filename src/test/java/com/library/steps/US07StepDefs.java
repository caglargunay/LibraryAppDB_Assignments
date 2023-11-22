package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class US07StepDefs {
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();

    String searchedBook = US4StepDefs.searchedBook;
    String actualBorrowingBookName = "";
    String actualBorrowingDateOfNonReturnedBook = "";

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        BrowserUtil.waitFor(2);
        bookPage.borrowBookClick.click();
    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String Borrow) {
        bookPage.navigateModule(Borrow);
        BrowserUtil.waitFor(2);

        int i = 0;
        for (WebElement each : borrowedBooksPage.allBorrowedBooksReturnDate) {

            if (!each.getText().equals("null")) {
                i += 1;
            } else {
                actualBorrowingBookName = borrowedBooksPage.allBorrowedBooksName.get(i).getText();
                actualBorrowingDateOfNonReturnedBook = borrowedBooksPage.allBorrowedBooksBorrowedDate.get(i).getText();
                i += 1;
            }
        }
//        System.out.println("actualBorrowingBookName = " + actualBorrowingBookName);
//        System.out.println("expectedBorrowngDateOfNonReturnedBook = " + actualBorrowingDateOfNonReturnedBook);
//        System.out.println("searchedBook = " + searchedBook);
        Assert.assertEquals(searchedBook, actualBorrowingBookName);
    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        String actualFullName = bookPage.accountHolderName.getText();
        String query ="select full_name,b.name,bb.borrowed_date from users u\n" +
                "                                                  inner join book_borrow bb on u.id = bb.user_id\n" +
                "                                                  inner join books b on bb.book_id = b.id\n" +
                "where full_name='Test Student 49' and name='Richard Burke' and is_returned =0\n" +
                "order by 3 desc";
        DB_Util.runQuery(query);
        List<Map<String, String>> allRowAsListOfMap = DB_Util.getAllRowAsListOfMap();

        System.out.println("allRowAsListOfMap.get(1) = " + allRowAsListOfMap.get(0));
        String expectedBorrowingBookName = allRowAsListOfMap.get(0).get("name");
        String expectedBorrowngDateOfNonReturnedBook = allRowAsListOfMap.get(0).get("borrowed_date");
        String expectedFullName = allRowAsListOfMap.get(0).get("full_name");

//        System.out.println("actualFullName = " + actualFullName);
//        System.out.println("expectedFullName = " + expectedFullName);
//        System.out.println("actualBorrowingBookName = " + actualBorrowingBookName);
//        System.out.println("expectedBorrowingBookName = " + expectedBorrowingBookName);
//        System.out.println("actualBorrowingDateOfNonReturnedBook = " + actualBorrowingDateOfNonReturnedBook);
//        System.out.println("expectedBorrowngDateOfNonReturnedBook = " + expectedBorrowngDateOfNonReturnedBook);

        Assert.assertEquals(expectedFullName,actualFullName);
        Assert.assertEquals(expectedBorrowingBookName,actualBorrowingBookName);
        Assert.assertEquals(expectedBorrowngDateOfNonReturnedBook,actualBorrowingDateOfNonReturnedBook);


    }
}
