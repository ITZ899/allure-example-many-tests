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
@Owner("notification-team")
@Feature("Notification System")
public class NotificationSystemTest {

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("NS-T1")
    @Microservice("Notifications")
    @Story("Enable email notifications")
    @Tags({@Tag("web"), @Tag("smoke"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-1")})
    @DisplayName("Enable email notifications for user")
    public void shouldEnableEmailNotifications() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Enable Email Notifications");
        steps.shouldSeeIssueWithTitle("Enable Email Notifications");
    }

    @ParameterizedTest(name = "Enable notification type: {0}")
    @ValueSource(strings = {"SMS", "Push", "WhatsApp", "Telegram", "Slack", "Discord", "Teams", "Email"})
    @TM4J("NS-T2")
    @Microservice("Notifications")
    @Story("Enable different notification types")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-2")})
    public void shouldEnableNotificationType(String notificationType) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Enable " + notificationType + " Notifications");
        steps.shouldSeeIssueWithTitle("Enable " + notificationType + " Notifications");
    }

    @ParameterizedTest(name = "Test order notification: {0}")
    @ValueSource(strings = {"Order Confirmed", "Order Shipped", "Order Delivered", "Order Cancelled", "Payment Received"})
    @TM4J("NS-T3")
    @Microservice("Notifications")
    @Story("Test order notifications")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-3")})
    public void shouldReceiveOrderNotification(String notificationType) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Order Notification: " + notificationType);
        steps.shouldSeeIssueWithTitle("Order Notification: " + notificationType);
    }

    @ParameterizedTest(name = "Test product notification: {0}")
    @ValueSource(strings = {"Price Drop", "Back in Stock", "New Arrival", "Sale Started", "Limited Time Offer"})
    @TM4J("NS-T4")
    @Microservice("Notifications")
    @Story("Test product notifications")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-4")})
    public void shouldReceiveProductNotification(String notificationType) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Product Notification: " + notificationType);
        steps.shouldSeeIssueWithTitle("Product Notification: " + notificationType);
    }

    @Test
    @TM4J("NS-T5")
    @Microservice("Notifications")
    @Story("Disable all notifications")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-5")})
    @DisplayName("Disable all notification types")
    public void shouldDisableAllNotifications() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Disable All Notifications");
        steps.closeIssueWithTitle("Disable All Notifications");
    }

    @ParameterizedTest(name = "Test notification frequency: {0}")
    @ValueSource(strings = {"Immediate", "Daily", "Weekly", "Monthly", "Never"})
    @TM4J("NS-T6")
    @Microservice("Notifications")
    @Story("Test notification frequency")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-6")})
    public void shouldSetNotificationFrequency(String frequency) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Notification Frequency: " + frequency);
        steps.shouldSeeIssueWithTitle("Notification Frequency: " + frequency);
    }

    @ParameterizedTest(name = "Test notification channel: {0}")
    @CsvSource({
        "Email, john@example.com",
        "SMS, +1234567890",
        "WhatsApp, +1234567890",
        "Telegram, @username"
    })
    @TM4J("NS-T7")
    @Microservice("Notifications")
    @Story("Configure notification channels")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-7")})
    public void shouldConfigureNotificationChannel(String channel, String contact) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Configure " + channel + " Channel: " + contact);
        steps.shouldSeeIssueWithTitle("Configure " + channel + " Channel: " + contact);
    }

    @Test
    @TM4J("NS-T8")
    @Microservice("Notifications")
    @Story("Test notification delivery")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-8")})
    @DisplayName("Test notification delivery to all channels")
    public void shouldTestNotificationDelivery() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Test Notification Delivery");
        steps.shouldSeeIssueWithTitle("Test Notification Delivery");
    }

    @ParameterizedTest(name = "Test notification template: {0}")
    @ValueSource(strings = {"Welcome", "Order Update", "Price Alert", "Stock Alert", "Promotion", "Newsletter"})
    @TM4J("NS-T9")
    @Microservice("Notifications")
    @Story("Test notification templates")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-9")})
    public void shouldTestNotificationTemplate(String template) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Notification Template: " + template);
        steps.shouldSeeIssueWithTitle("Notification Template: " + template);
    }

    @Test
    @TM4J("NS-T10")
    @Microservice("Notifications")
    @Story("Manage notification preferences")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("NS-10")})
    @DisplayName("Manage user notification preferences")
    public void shouldManageNotificationPreferences() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Manage Notification Preferences");
        steps.shouldSeeIssueWithTitle("Manage Notification Preferences");
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }
}
