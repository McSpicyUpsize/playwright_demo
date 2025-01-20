package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void fill(String selector, String text) {
        page.fill(selector, text);
    }

    protected void click(String selector) {
        page.click(selector);
    }

    protected void waitAndClick(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
    }

    protected String getText(String selector) {
        return page.textContent(selector);
    }

    protected Locator find(String selector) {
        return page.locator(selector);
    }

    protected void waitForElement(String selector) {
        page.waitForSelector(selector);
    }
}