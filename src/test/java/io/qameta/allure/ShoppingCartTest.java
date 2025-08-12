package io.qameta.allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

@Layer("web")
@Owner("ecommerce-team")
@Feature("Shopping Cart")
public class ShoppingCartTest {

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("SC-T1")
    @Microservice("Cart")
    @Story("Add item to cart")
    @Tags({@Tag("web"), @Tag("smoke"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-1")})
    @DisplayName("Add product to shopping cart")
    public void shouldAddItemToCart() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Add to Cart Test");
        steps.shouldSeeIssueWithTitle("Add to Cart Test");
    }

    @Test
    @TM4J("SC-T2")
    @Microservice("Cart")
    @Story("Remove item from cart")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-2")})
    @DisplayName("Remove product from shopping cart")
    public void shouldRemoveItemFromCart() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Remove from Cart Test");
        steps.closeIssueWithTitle("Remove from Cart Test");
    }

    @ParameterizedTest(name = "Add multiple items: {0}")
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 10})
    @TM4J("SC-T3")
    @Microservice("Cart")
    @Story("Add multiple items to cart")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-3")})
    public void shouldAddMultipleItemsToCart(int itemCount) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Multiple Items: " + itemCount);
        steps.shouldSeeIssueWithTitle("Multiple Items: " + itemCount);
    }

    @ParameterizedTest(name = "Update quantity for {0} to {1}")
    @CsvSource({
        "Product A, 2",
        "Product B, 3",
        "Product C, 5",
        "Product D, 10"
    })
    @TM4J("SC-T4")
    @Microservice("Cart")
    @Story("Update item quantity")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-4")})
    public void shouldUpdateItemQuantity(String productName, int quantity) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Update Quantity: " + productName + " to " + quantity);
        steps.shouldSeeIssueWithTitle("Update Quantity: " + productName + " to " + quantity);
    }

    @Test
    @TM4J("SC-T5")
    @Microservice("Cart")
    @Story("Clear shopping cart")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-5")})
    @DisplayName("Clear all items from cart")
    public void shouldClearShoppingCart() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Clear Cart Test");
        steps.closeIssueWithTitle("Clear Cart Test");
    }

    @ParameterizedTest(name = "Apply discount code: {0}")
    @ValueSource(strings = {"SAVE10", "WELCOME20", "SUMMER15", "HOLIDAY25", "NEWUSER30"})
    @TM4J("SC-T6")
    @Microservice("Cart")
    @Story("Apply discount codes")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-6")})
    public void shouldApplyDiscountCode(String discountCode) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Discount Code: " + discountCode);
        steps.shouldSeeIssueWithTitle("Discount Code: " + discountCode);
    }

    @Test
    @TM4J("SC-T7")
    @Microservice("Cart")
    @Story("Calculate cart total")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-7")})
    @DisplayName("Calculate total price with tax and shipping")
    public void shouldCalculateCartTotal() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Calculate Total Test");
        steps.shouldSeeIssueWithTitle("Calculate Total Test");
    }

    @ParameterizedTest(name = "Save cart for later: {0}")
    @ValueSource(strings = {"Morning Cart", "Evening Cart", "Weekend Cart", "Holiday Cart"})
    @TM4J("SC-T8")
    @Microservice("Cart")
    @Story("Save cart for later")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-8")})
    public void shouldSaveCartForLater(String cartName) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Save Cart: " + cartName);
        steps.shouldSeeIssueWithTitle("Save Cart: " + cartName);
    }

    @Test
    @TM4J("SC-T9")
    @Microservice("Cart")
    @Story("Restore saved cart")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-9")})
    @DisplayName("Restore previously saved cart")
    public void shouldRestoreSavedCart() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Restore Cart Test");
        steps.shouldSeeIssueWithTitle("Restore Cart Test");
    }

    @ParameterizedTest(name = "Share cart via: {0}")
    @ValueSource(strings = {"Email", "WhatsApp", "Facebook", "Twitter", "SMS"})
    @TM4J("SC-T10")
    @Microservice("Cart")
    @Story("Share shopping cart")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("SC-10")})
    public void shouldShareCartVia(String shareMethod) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Share Cart via: " + shareMethod);
        steps.shouldSeeIssueWithTitle("Share Cart via: " + shareMethod);
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }
}
