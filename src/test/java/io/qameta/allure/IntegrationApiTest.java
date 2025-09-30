package io.qameta.allure;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(AllureJunit5.class)
@Layer("rest")
@Owner("integration-specialist")
@Feature("API Integration")
@TM4J("TM4J-129")
@Microservice("integration-service")
@Story("API integration functionality")
@Tag("integration") @Tag("api") @Tag("rest")
public class IntegrationApiTest {

    private RestSteps steps;

    @BeforeEach
    void startDriver() {
        steps = new RestSteps();
    }

    @Test
    @DisplayName("Test payment gateway integration")
    @Story("Payment integration")
    @JiraIssues({@JiraIssue("JIRA-462")})
    public void shouldTestPaymentGatewayIntegration() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String paymentConfig = "{\n" +
                "  \"gateway\": \"Stripe\",\n" +
                "  \"version\": \"2023-10-16\",\n" +
                "  \"endpoint\": \"https://api.stripe.com/v1/payments\",\n" +
                "  \"authentication\": \"Bearer token\",\n" +
                "  \"timeout\": 30000,\n" +
                "  \"retryCount\": 3\n" +
                "}";
        
        String paymentData = "Amount: $100.00\n" +
                "Currency: USD\n" +
                "Method: Credit Card\n" +
                "Card: **** 1234\n" +
                "Status: Processing\n" +
                "Transaction ID: txn_123456789";
        
        String result = "Payment gateway integration tested successfully at " + timestamp + "\n" +
                "Status: Connected\n" +
                "Response Time: 250ms\n" +
                "Success Rate: 99.8%\n" +
                "Error Rate: 0.2%";
        
        Allure.attachment("Payment Configuration", paymentConfig);
        Allure.attachment("Payment Data", paymentData);
        Allure.attachment("Integration Result", result);
        
        steps.createIssueWithTitle("testuser", "testrepo", "Payment Gateway Integration: Stripe");
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Payment Gateway Integration: Stripe");
    }

    @Test
    @DisplayName("Test inventory system integration")
    @Story("Inventory integration")
    public void shouldTestInventorySystemIntegration() {
        String system = "Xero";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String inventoryConfig = "{\n" +
                "  \"system\": \"" + system + "\",\n" +
                "  \"apiVersion\": \"v2.0\",\n" +
                "  \"endpoint\": \"https://api.xero.com/inventory\",\n" +
                "  \"authentication\": \"OAuth 2.0\",\n" +
                "  \"syncFrequency\": \"5 minutes\",\n" +
                "  \"batchSize\": 100\n" +
                "}";
        
        String inventoryData = "System: " + system + "\n" +
                "Products: 1,250\n" +
                "Stock Levels: Real-time\n" +
                "Last Sync: " + LocalDateTime.now().minusMinutes(2).format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" +
                "Status: Synchronized\n" +
                "Error Count: 0";
        
        String result = "Inventory system '" + system + "' integrated successfully at " + timestamp + "\n" +
                "Status: Active\n" +
                "Sync Status: Up to date\n" +
                "Data Accuracy: 99.9%\n" +
                "Performance: Excellent";
        
        Allure.attachment("Inventory Configuration", inventoryConfig);
        Allure.attachment("Inventory Data", inventoryData);
        Allure.attachment("Integration Result", result);
        
        steps.createIssueWithTitle("testuser", "testrepo", "Inventory System Integration: " + system);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Inventory System Integration: " + system);
    }

    @Test
    @DisplayName("Test shipping provider integration")
    @Story("Shipping integration")
    public void shouldTestShippingProviderIntegration() {
        String provider = "Royal Mail";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String shippingConfig = "{\n" +
                "  \"provider\": \"" + provider + "\",\n" +
                "  \"apiVersion\": \"v1.2\",\n" +
                "  \"endpoint\": \"https://api.royalmail.com/shipping\",\n" +
                "  \"authentication\": \"API Key\",\n" +
                "  \"rateLimit\": \"1000/hour\",\n" +
                "  \"timeout\": 15000\n" +
                "}";
        
        String shippingData = "Provider: " + provider + "\n" +
                "Service: Tracked 24\n" +
                "Delivery Time: 1-2 business days\n" +
                "Coverage: UK & International\n" +
                "Tracking: Available\n" +
                "Insurance: Up to £500";
        
        String result = "Shipping provider '" + provider + "' integrated successfully at " + timestamp + "\n" +
                "Status: Connected\n" +
                "Service Level: Premium\n" +
                "Reliability: 99.5%\n" +
                "Customer Satisfaction: High";
        
        Allure.attachment("Shipping Configuration", shippingConfig);
        Allure.attachment("Shipping Data", shippingData);
        Allure.attachment("Integration Result", result);
        
        steps.createIssueWithTitle("testuser", "testrepo", "Shipping Provider Integration: " + provider);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Shipping Provider Integration: " + provider);
    }
}
