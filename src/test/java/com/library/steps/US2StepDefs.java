package com.library.steps;

import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2StepDefs {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    String expectedBorrowedBooksNumber;
    String actualBorrowedBooksNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        String baseUrl = ConfigurationReader.getProperty("library_url");
        Driver.getDriver().get(baseUrl);
        loginPage.login(userType);
    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
            BrowserUtil.waitFor(2);
        actualBorrowedBooksNumber = dashBoardPage.borrowedBooksNumber.getText();
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query = "select count(*) from book_borrow where is_returned = 0";
        DB_Util.runQuery(query);
        expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualBorrowedBooksNumber);
        System.out.println(expectedBorrowedBooksNumber);

        Assert.assertEquals(expectedBorrowedBooksNumber,actualBorrowedBooksNumber);
    }
}
