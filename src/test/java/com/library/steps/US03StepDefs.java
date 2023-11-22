package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03StepDefs {

    BookPage bookPage = new BookPage();
    List<String> actualBookCategories = new ArrayList<>();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String BooksPage) {
        bookPage.navigateModule(BooksPage);
    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        Select bookCategories = new Select(bookPage.mainCategoryElement);
        List<WebElement> options = bookCategories.getOptions();

        for (WebElement option : options) {
            BrowserUtil.waitForClickablility(bookPage.mainCategoryElement, 3);
            actualBookCategories.add(option.getText());
        }

        actualBookCategories.remove("ALL");
        System.out.println(actualBookCategories);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        String query = "select name from book_categories";
        DB_Util.runQuery(query);
        List<String> expectedBookCategories = DB_Util.getColumnDataAsList("name");
        System.out.println(expectedBookCategories);
        Assert.assertEquals(expectedBookCategories, actualBookCategories);

    }
}
