// ButtonsTest.java
package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.ButtonsPage;
import pages.NavigationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonsTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;
    ButtonsPage buttonsPage;
    NavigationPage navigationPage;

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
        buttonsPage = new ButtonsPage(page);
        navigationPage = new NavigationPage(page);
    }

    @Test
    void testDoubleClickButton() {
        buttonsPage.performDoubleClick();
        assertEquals("You have done a double click", buttonsPage.getDoubleClickMessage());
    }

    @Test
    void testRightClickButton() {
        buttonsPage.performRightClick();
        assertEquals("You have done a right click", buttonsPage.getRightClickMessage());
    }

    @Test
    void testSingleClickButton() {
        buttonsPage.performSingleClick();
        assertEquals("You have done a dynamic click", buttonsPage.getDynamicClickMessage());
    }

    @Test
    void testNavigationBetweenPages() {
        navigationPage.navigateToTextBox();
        page.waitForURL("**/text-box");

        navigationPage.navigateToButtons();
        page.waitForURL("**/buttons");

        buttonsPage.performDoubleClick();
        assertEquals("You have done a double click", buttonsPage.getDoubleClickMessage());
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