package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class NavigationPage extends BasePage {
    // Common navigation elements
    private final String elementsCard = "//h5[text()='Elements']";
    private final String buttonsLink = "//span[text()='Buttons']";
    private final String textBoxLink = "//span[text()='Text Box']";

    public NavigationPage(Page page) {
        super(page);
    }

    public void navigateToElements() {
        click(elementsCard);
    }

    public void navigateToButtons() {
        navigateToElements();
        waitAndClick(buttonsLink);
    }

    public void navigateToTextBox() {
        navigateToElements();
        waitAndClick(textBoxLink);
    }

}