// BrokenLinksPage.java
package pages;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

public class BrokenLinksPage extends BasePage {
    // Selectors for images
    private final String validImage = "//p[text()='Valid image']/following-sibling::img";
    private final String brokenImage = "//p[text()='Broken image']/following-sibling::img";

    // Selectors for links
    private final String validLink = "//a[text()='Click Here for Valid Link']";
    private final String brokenLink = "//a[text()='Click Here for Broken Link']";

    public BrokenLinksPage(Page page) {
        super(page);
        page.navigate("https://demoqa.com/broken");
    }

    /**
     * Gets the HTTP response status for a given image/link
     * @param selector The image selector
     * @return HTTP status code (200 for valid, 404 for broken, etc.)
     * For get attribute: If your HTML is: <img src="https://example.com/image.jpg">, will return "https://example.com/image.jpg"
     */
    public int getImageStatus(String selector) {
        String srcAttribute = page.getAttribute(selector, "src");
        if (srcAttribute == null || srcAttribute.isEmpty()) {
            return 404;
        }
        // Make sure the URL is absolute
        String imageUrl = srcAttribute.startsWith("http") ?
                srcAttribute : "https://demoqa.com" + srcAttribute;

        APIResponse response = page.request().get(imageUrl);
        return response.status();
    }

    public int getLinkStatus(String selector) {
        String href = page.getAttribute(selector, "href");
        if (href == null || href.isEmpty()) {
            return 404;
        }

        String linkUrl = href.startsWith("http") ?
                href : "https://demoqa.com" + href;

        APIResponse response = page.request().get(linkUrl);
        return response.status();
    }

    /**
     * Clicks a link and returns the response status of the new page
     * @param selector The link selector
     * @return HTTP status code of the page after clicking
     * waitForResponse (predicate, response).
     * Predicate: A condition that must be met by the response.
     * Action: An action to perform while waiting for the response.
     * First performs action in 2nd param, click(selector) is executed.
     * waitForResponse listens for responses. For each response, it checks res -> res.request().isNavigationRequest().
     * When a response that satisfies the predicate is found, it is returned.
     */
    public int clickLinkAndGetStatus(String selector) {
        Response response = page.waitForResponse(
                res -> res.request().isNavigationRequest(),
                () -> click(selector)
        );
        return response.status();
    }

    // Getter methods for selectors to use in tests
    public String getValidImageSelector() {
        return validImage;
    }

    public String getBrokenImageSelector() {
        return brokenImage;
    }

    public String getValidLinkSelector() {
        return validLink;
    }

    public String getBrokenLinkSelector() {
        return brokenLink;
    }
}