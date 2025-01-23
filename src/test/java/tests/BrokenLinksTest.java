package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.BrokenLinksPage;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenLinksTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;
    BrokenLinksPage brokenLinksPage;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        brokenLinksPage = new BrokenLinksPage(page);
    }

    @Test
    void testValidImage() {
        int statusCode = brokenLinksPage.getImageStatus(brokenLinksPage.getValidImageSelector());
        assertEquals(200, statusCode, "Valid image should return 200 status code");
    }

    @Test
    void testBrokenImage() {
        int statusCode = brokenLinksPage.getImageStatus(brokenLinksPage.getBrokenImageSelector());
        assertNotEquals(200, statusCode, "Broken image should not return 200 status code");
    }

    @Test
    void testValidLink() {
        // First check without clicking
        int statusCode = brokenLinksPage.getLinkStatus(brokenLinksPage.getValidLinkSelector());
        assertEquals(200, statusCode, "Valid link should return 200 status code");

        // Then check by actually clicking
        int clickStatusCode = brokenLinksPage.clickLinkAndGetStatus(brokenLinksPage.getValidLinkSelector());
        assertEquals(301, clickStatusCode, "Valid link should navigate to a valid page");
    }

    @Test
    void testBrokenLink() {
        int statusCode = brokenLinksPage.getLinkStatus(brokenLinksPage.getBrokenLinkSelector());
        assertNotEquals(200, statusCode, "Broken link should not return 200 status code");
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }
}