package io.qameta.allure;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@ExtendWith(AllureJunit5.class)
@Layer("web")
@Owner("data-validator")
@Feature("Data Validation")
@TM4J("TM4J-123")
@Microservice("validation-service")
@Story("Data validation functionality")
@Tag("validation") @Tag("data") @Tag("web")
public class DataValidationTest {

    private WebSteps steps;

    @BeforeEach
    void startDriver() {
        steps = new WebSteps();
    }

    @Test
    @DisplayName("Validate email format")
    @Story("Email validation")
    @JiraIssues({@JiraIssue("JIRA-456")})
    public void shouldValidateEmailFormat() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Email Validation Test");
        steps.shouldSeeIssueWithTitle("Email Validation Test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@domain.co.uk", "admin+tag@company.org"})
    @DisplayName("Validate valid email formats")
    @Story("Valid email validation")
    public void shouldAcceptValidEmailFormats(String email) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Valid Email: " + email);
        steps.shouldSeeIssueWithTitle("Valid Email: " + email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid-email", "@domain.com", "user@", "user.domain.com"})
    @DisplayName("Validate invalid email formats")
    @Story("Invalid email validation")
    public void shouldRejectInvalidEmailFormats(String email) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Invalid Email: " + email);
        steps.shouldSeeIssueWithTitle("Invalid Email: " + email);
    }

    @ParameterizedTest
    @CsvSource({"John Doe, 30, john@example.com", "Jane Smith, 25, jane@example.com", "Bob Wilson, 35, bob@example.com"})
    @DisplayName("Validate user profile data")
    @Story("User profile validation")
    public void shouldValidateUserProfileData(String name, int age, String email) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("User Profile: " + name + " (" + age + ") - " + email);
        steps.shouldSeeIssueWithTitle("User Profile: " + name + " (" + age + ") - " + email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123-45-6789", "987-65-4321", "111-22-3333"})
    @DisplayName("Validate SSN format")
    @Story("SSN validation")
    public void shouldValidateSSNFormat(String ssn) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("SSN Validation: " + ssn);
        steps.shouldSeeIssueWithTitle("SSN Validation: " + ssn);
    }

    @ParameterizedTest
    @ValueSource(strings = {"+1-555-123-4567", "+44-20-7946-0958", "+81-3-1234-5678"})
    @DisplayName("Validate phone number format")
    @Story("Phone validation")
    public void shouldValidatePhoneNumberFormat(String phone) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Phone Validation: " + phone);
        steps.shouldSeeIssueWithTitle("Phone Validation: " + phone);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345-6789", "A1B2C3", "12345-6789-0123"})
    @DisplayName("Validate postal code format")
    @Story("Postal code validation")
    public void shouldValidatePostalCodeFormat(String postalCode) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Postal Code: " + postalCode);
        steps.shouldSeeIssueWithTitle("Postal Code: " + postalCode);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-12-31", "2024-01-01", "2025-06-15"})
    @DisplayName("Validate date format")
    @Story("Date validation")
    public void shouldValidateDateFormat(String date) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Date Validation: " + date);
        steps.shouldSeeIssueWithTitle("Date Validation: " + date);
    }

    @ParameterizedTest
    @ValueSource(strings = {"09:30", "14:45", "23:59"})
    @DisplayName("Validate time format")
    @Story("Time validation")
    public void shouldValidateTimeFormat(String time) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Time Validation: " + time);
        steps.shouldSeeIssueWithTitle("Time Validation: " + time);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100.50", "0.99", "999999.99"})
    @DisplayName("Validate currency format")
    @Story("Currency validation")
    public void shouldValidateCurrencyFormat(String amount) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Currency Validation: " + amount);
        steps.shouldSeeIssueWithTitle("Currency Validation: " + amount);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC123", "XYZ789", "DEF456"})
    @DisplayName("Validate product code format")
    @Story("Product code validation")
    public void shouldValidateProductCodeFormat(String productCode) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Product Code: " + productCode);
        steps.shouldSeeIssueWithTitle("Product Code: " + productCode);
    }
}
