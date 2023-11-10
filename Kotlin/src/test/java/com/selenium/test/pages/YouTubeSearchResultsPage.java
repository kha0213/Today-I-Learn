package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This page is a page object example.
 */
public class YouTubeSearchResultsPage extends BasePage {

    @FindBy(css="#content")
    private WebElement contentElement;

    @FindBys(@FindBy(css = "div[class*='yt-lockup-tile yt-lockup-video']"))
    private List<WebElement> videoElements;


    public YouTubeSearchResultsPage(){
        super(true);
    }

    public YouTubeSearchResultsPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {
            return contentElement.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean hasResults() {
        return !videoElements.isEmpty();
    }
}
