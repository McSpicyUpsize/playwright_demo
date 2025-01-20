package pages;

import com.microsoft.playwright.Page;

public class TextBoxPage extends BasePage {
    // Selectors
    private final String fullNameInput = "#userName";
    private final String emailInput = "#userEmail";
    private final String currentAddressInput = "#currentAddress";
    private final String permanentAddressInput = "#permanentAddress";
    private final String submitButton = "#submit";

    // Output selectors
    private final String nameOutput = "#output #name";
    private final String emailOutput = "#output #email";
    private final String currentAddressOutput = "#output #currentAddress";
    private final String permanentAddressOutput = "#output #permanentAddress";

    public TextBoxPage(Page page) {
        super(page);
        page.navigate("https://demoqa.com/text-box");
    }

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        fill(fullNameInput, fullName);
        fill(emailInput, email);
        fill(currentAddressInput, currentAddress);
        fill(permanentAddressInput, permanentAddress);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getNameOutput() {
        return getText(nameOutput).replace("Name:", "").trim();
    }

    public String getEmailOutput() {
        return getText(emailOutput).replace("Email:", "").trim();
    }

    public String getCurrentAddressOutput() {
        return getText(currentAddressOutput).replace("Current Address :", "").trim();
    }

    public String getPermanentAddressOutput() {
        return getText(permanentAddressOutput).replace("Permananet Address :", "").trim();
    }
}