package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This page is a page object example.
 */
public class YouTubePage extends BasePage {

    private static final String PAGE_URL = "http://youtube.com";

    @FindBy(css = "#masthead-search button")
    private WebElement searchButtonElement;

    @FindBy(css = "#masthead-search-term")
    private WebElement searchStringElement;

    public YouTubePage() {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return searchStringElement.isDisplayed();
    }

    /**
     * inserts search text in search string
     * @param text text for input
     */
    public void insertSearchString(String text) {
        searchStringElement.sendKeys(text);
    }

    /**
     * perform search
     * @return results page instance
     */
    public YouTubeSearchResultsPage doSearch(){
        searchButtonElement.click();
        return new YouTubeSearchResultsPage();
    }

    /**
     * clears search string
     */
    private void clearSearchString(){
        searchStringElement.clear();
    }

    /**
     * getting search string text
     * @return text from search string
     */
    public String getSearchStringText(){
        return searchStringElement.getAttribute("value");
    }
}
