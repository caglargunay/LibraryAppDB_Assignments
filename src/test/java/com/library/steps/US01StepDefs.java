package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;

public class US01StepDefs {
    List<String> actualColumnNames;

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
       String query ="SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME = 'users'";
       DB_Util.runQuery(query);
       actualColumnNames=DB_Util.getColumnDataAsList(1);
    //   System.out.println(actualColumnNames);
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnNames) {
        Assert.assertEquals(expectedColumnNames, actualColumnNames);
    }
}
