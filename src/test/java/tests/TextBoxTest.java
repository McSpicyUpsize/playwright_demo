package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.TextBoxPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextBoxTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;
    TextBoxPage textBoxPage;

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
        textBoxPage = new TextBoxPage(page);
    }

    @Test
    void testTextBoxForm() {
        String fullName = "John Doe";
        String email = "john.doe@example.com";
        String currentAddress = "123 Main St";
        String permanentAddress = "456 Oak Ave";

        textBoxPage.fillForm(fullName, email, currentAddress, permanentAddress);
        textBoxPage.submitForm();

        assertEquals(fullName, textBoxPage.getNameOutput());
        assertEquals(email, textBoxPage.getEmailOutput());
        assertEquals(currentAddress, textBoxPage.getCurrentAddressOutput());
        assertEquals(permanentAddress, textBoxPage.getPermanentAddressOutput());
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