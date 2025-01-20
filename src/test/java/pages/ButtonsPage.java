package pages;

import com.microsoft.playwright.Page;

public class ButtonsPage extends BasePage {
    private final String doubleClickBtn = "#doubleClickBtn";
    private final String rightClickBtn = "#rightClickBtn";
    private final String dynamicClickBtn = "//button[text()='Click Me']";

    private final String doubleClickMessage = "#doubleClickMessage";
    private final String rightClickMessage = "#rightClickMessage";
    private final String dynamicClickMessage = "#dynamicClickMessage";

    public ButtonsPage(Page page) {
        super(page);
        page.navigate("https://demoqa.com/buttons");
    }

    public void performDoubleClick() {
        page.dblclick(doubleClickBtn);
    }

    public void performRightClick() {
        page.click(rightClickBtn, new Page.ClickOptions().setButton(com.microsoft.playwright.options.MouseButton.RIGHT));
    }

    public void performSingleClick() {
        click(dynamicClickBtn);
    }

    public String getDoubleClickMessage() {
        return getText(doubleClickMessage);
    }

    public String getRightClickMessage() {
        return getText(rightClickMessage);
    }

    public String getDynamicClickMessage() {
        return getText(dynamicClickMessage);
    }
}
