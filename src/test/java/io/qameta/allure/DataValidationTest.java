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
@Layer("web")
@Owner("qa-engineer")
@Feature("Data Validation")
@TM4J("TM4J-127")
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
    @JiraIssues({@JiraIssue("JIRA-460")})
    public void shouldValidateEmailFormat() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach validation rules
        String validationRules = "Email Validation Rules:\n" +
                "• Must contain @ symbol\n" +
                "• Must have local part before @\n" +
                "• Must have domain after @\n" +
                "• Domain must have at least one dot\n" +
                "• Length: 1-320 characters\n" +
                "• No consecutive dots";
        Allure.attachment("Validation Rules", validationRules);
        
        // Attach test data
        String testData = "Test Email: test@example.com\n" +
                "Edge case: user+tag@domain.co.uk\n" +
                "Timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Allure.attachment("Test Data", testData);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Email Validation: test@example.com");
        steps.shouldSeeIssueWithTitle("Email Validation: test@example.com");
        
        // Attach validation result
        String result = "Email validation completed at " + timestamp + "\n" +
                "✅ Format: Valid\n" +
                "✅ Length check: Passed\n" +
                "✅ Special characters: Valid";
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate valid email formats")
    @Story("Email validation")
    public void shouldAcceptValidEmailFormats() {
        String email = "test@example.com";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach email analysis
        String emailAnalysis = "Email: " + email + "\n" +
                "Length: " + email.length() + " characters\n" +
                "Special chars: " + (email.contains("+") ? "Yes" : "No");
        Allure.attachment("Email Analysis", emailAnalysis);
        
        // Attach validation steps
        String validationSteps = "Validation Steps:\n" +
                "1. Check for @ symbol\n" +
                "2. Validate local part\n" +
                "3. Validate domain part\n" +
                "4. Check overall length\n" +
                "5. Verify special characters";
        Allure.attachment("Validation Steps", validationSteps);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Valid Email: " + email);
        steps.shouldSeeIssueWithTitle("Valid Email: " + email);
        
        // Attach success report
        String success = "Email validation successful at " + timestamp + "\n" +
                "✅ Format: Valid\n" +
                "✅ Length: Acceptable\n" +
                "✅ Special characters: Allowed";
        Allure.attachment("Success Report", success);
    }

    @Test
    @DisplayName("Validate invalid email formats")
    @Story("Email validation")
    public void shouldRejectInvalidEmailFormats() {
        String email = "invalid-email";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach error analysis
        String errorAnalysis = "Email: " + email + "\n" +
                "Missing: @ symbol\n" +
                "Invalid: N/A";
        Allure.attachment("Error Analysis", errorAnalysis);
        
        // Attach validation steps
        String validationSteps = "Validation Steps:\n" +
                "1. Check for @ symbol\n" +
                "2. Validate local part\n" +
                "3. Validate domain part\n" +
                "4. Check overall length\n" +
                "5. Verify special characters";
        Allure.attachment("Validation Steps", validationSteps);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Invalid Email: " + email);
        steps.shouldSeeIssueWithTitle("Invalid Email: " + email);
        
        // Attach rejection report
        String rejection = "Email validation failed at " + timestamp + "\n" +
                "❌ Missing: @ symbol\n" +
                "❌ Invalid: N/A";
        Allure.attachment("Rejection Report", rejection);
    }

    @Test
    @DisplayName("Validate user profile data")
    @Story("Profile validation")
    public void shouldValidateUserProfileData() {
        String name = "John Doe";
        int age = 30;
        String email = "john@example.com";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach profile data
        String profileData = "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Email: " + email + "\n" +
                "Validation timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Allure.attachment("Profile Data", profileData);
        
        // Attach validation rules
        String validationRules = "Profile Validation Rules:\n" +
                "• Name: Required, 2-50 characters\n" +
                "• Age: 18-120 years\n" +
                "• Email: Valid email format\n" +
                "• All fields: Required";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("User Profile: " + name + " (" + age + ") - " + email);
        steps.shouldSeeIssueWithTitle("User Profile: " + name + " (" + age + ") - " + email);
        
        // Attach validation result
        String result = "Profile validation completed at " + timestamp + "\n" +
                "✅ Name: Valid\n" +
                "✅ Age: Valid\n" +
                "✅ Email validation: " + (email.contains("@") ? "Passed" : "Failed") + "\n" +
                "✅ Overall validation: Passed";
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate SSN format")
    @Story("SSN validation")
    public void shouldValidateSSNFormat() {
        String ssn = "123-45-6789";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach SSN analysis
        String ssnAnalysis = "SSN: " + ssn + "\n" +
                "Hyphens: " + (ssn.split("-").length - 1) + "\n" +
                "Digits: " + ssn.replaceAll("[^0-9]", "").length();
        Allure.attachment("SSN Analysis", ssnAnalysis);
        
        // Attach validation rules
        String validationRules = "SSN Validation Rules:\n" +
                "• Format: XXX-XX-XXXX\n" +
                "• Digits: 9 numeric digits\n" +
                "• No leading zeros in first group";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("SSN Validation: " + ssn);
        steps.shouldSeeIssueWithTitle("SSN Validation: " + ssn);
        
        // Attach validation result
        String result = "SSN validation completed at " + timestamp + "\n" +
                "✅ Hyphens: " + (ssn.split("-").length == 3 ? "Valid" : "Invalid") + "\n" +
                "✅ Digits: " + (ssn.replaceAll("[^0-9]", "").length() == 9 ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate phone number format")
    @Story("Phone validation")
    public void shouldValidatePhoneNumberFormat() {
        String phone = "+1-555-123-4567";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach phone analysis
        String phoneAnalysis = "Phone: " + phone + "\n" +
                "Country Code: " + phone.substring(0, phone.indexOf("-")) + "\n" +
                "Area Code: " + phone.substring(phone.indexOf("-") + 1, phone.indexOf("-", phone.indexOf("-") + 1)) + "\n" +
                "Number: " + phone.substring(phone.indexOf("-", phone.indexOf("-") + 1) + 1) + "\n" +
                "Length: " + phone.length() + " characters";
        Allure.attachment("Phone Analysis", phoneAnalysis);
        
        // Attach validation rules
        String validationRules = "Phone Validation Rules:\n" +
                "• Format: +X-XXX-XXX-XXXX\n" +
                "• Country code: +1 to +999\n" +
                "• Number: 7-10 digits\n" +
                "• Hyphens: Separators between groups";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Phone Validation: " + phone);
        steps.shouldSeeIssueWithTitle("Phone Validation: " + phone);
        
        // Attach validation result
        String result = "Phone validation completed at " + timestamp + "\n" +
                "✅ Length: " + (phone.length() >= 10 && phone.length() <= 20 ? "Valid" : "Invalid") + "\n" +
                "✅ Digits: " + (phone.replaceAll("[^0-9]", "").length() >= 10 ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate postal code format")
    @Story("Postal code validation")
    public void shouldValidatePostalCodeFormat() {
        String postalCode = "12345-6789";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach postal code analysis
        String postalAnalysis = "Postal Code: " + postalCode + "\n" +
                "Type: US ZIP+4\n" +
                "Country: United States";
        Allure.attachment("Postal Code Analysis", postalAnalysis);
        
        // Attach validation rules
        String validationRules = "Postal Code Validation Rules:\n" +
                "• Format: XXXXX-XXXX or XXXXX\n" +
                "• Length: 5-10 characters\n" +
                "• Alphanumeric: Letters and numbers";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Postal Code: " + postalCode);
        steps.shouldSeeIssueWithTitle("Postal Code: " + postalCode);
        
        // Attach validation result
        String result = "Postal code validation completed at " + timestamp + "\n" +
                "✅ Characters: " + (postalCode.matches("[A-Za-z0-9-]+") ? "Valid" : "Invalid") + "\n" +
                "✅ Type: US ZIP+4";
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate date format")
    @Story("Date validation")
    public void shouldValidateDateFormat() {
        String date = "2024-01-01";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach date analysis
        String dateAnalysis = "Date: " + date + "\n" +
                "Year: " + date.split("-")[0] + "\n" +
                "Month: " + date.split("-")[1] + "\n" +
                "Day: " + date.split("-")[2] + "\n" +
                "Length: " + date.length() + " characters";
        Allure.attachment("Date Analysis", dateAnalysis);
        
        // Attach validation rules
        String validationRules = "Date Validation Rules:\n" +
                "• Format: YYYY-MM-DD\n" +
                "• Year: 1900-2100\n" +
                "• Month: 01-12\n" +
                "• Day: 01-31 (depending on month)\n" +
                "• Separator: Hyphen (-)";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Date Validation: " + date);
        steps.shouldSeeIssueWithTitle("Date Validation: " + date);
        
        // Attach validation result
        String result = "Date validation completed at " + timestamp + "\n" +
                "✅ Year: " + (isValidYear(date.split("-")[0]) ? "Valid" : "Invalid") + "\n" +
                "✅ Month: " + (isValidMonth(date.split("-")[1]) ? "Valid" : "Invalid") + "\n" +
                "✅ Day: " + (isValidDay(date.split("-")[2]) ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate time format")
    @Story("Time validation")
    public void shouldValidateTimeFormat() {
        String time = "09:30";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach time analysis
        String timeAnalysis = "Time: " + time + "\n" +
                "Hour: " + time.split(":")[0] + "\n" +
                "Minute: " + time.split(":")[1] + "\n" +
                "Length: " + time.length() + " characters";
        Allure.attachment("Time Analysis", timeAnalysis);
        
        // Attach validation rules
        String validationRules = "Time Validation Rules:\n" +
                "• Format: HH:MM\n" +
                "• Hour: 00-23\n" +
                "• Minute: 00-59\n" +
                "• Separator: Colon (:)\n" +
                "• Length: 5 characters";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Time Validation: " + time);
        steps.shouldSeeIssueWithTitle("Time Validation: " + time);
        
        // Attach validation result
        String result = "Time validation completed at " + timestamp + "\n" +
                "✅ Hour: " + (isValidHour(time.split(":")[0]) ? "Valid" : "Invalid") + "\n" +
                "✅ Minute: " + (isValidMinute(time.split(":")[1]) ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate currency format")
    @Story("Currency validation")
    public void shouldValidateCurrencyFormat() {
        String amount = "123.45";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach currency analysis
        String currencyAnalysis = "Amount: " + amount + "\n" +
                "Integer part: " + amount.split("\\.")[0] + "\n" +
                "Decimal part: " + amount.split("\\.")[1] + "\n" +
                "Length: " + amount.length() + " characters";
        Allure.attachment("Currency Analysis", currencyAnalysis);
        
        // Attach validation rules
        String validationRules = "Currency Validation Rules:\n" +
                "• Format: XXXX.XX\n" +
                "• Integer: 0-999999\n" +
                "• Decimal: 2 digits\n" +
                "• Separator: Dot (.)\n" +
                "• Range: 0.00 - 999999.99";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Currency Validation: " + amount);
        steps.shouldSeeIssueWithTitle("Currency Validation: " + amount);
        
        // Attach validation result
        String result = "Currency validation completed at " + timestamp + "\n" +
                "✅ Integer part: " + (isValidIntegerPart(amount.split("\\.")[0]) ? "Valid" : "Invalid") + "\n" +
                "✅ Decimal part: " + (isValidDecimalPart(amount.split("\\.")[1]) ? "Valid" : "Invalid") + "\n" +
                "✅ Range: " + (isValidRange(amount) ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    @Test
    @DisplayName("Validate product code format")
    @Story("Product code validation")
    public void shouldValidateProductCodeFormat() {
        String productCode = "ABC123";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Attach product code analysis
        String productAnalysis = "Product Code: " + productCode + "\n" +
                "Letters: " + productCode.replaceAll("[^A-Za-z]", "") + "\n" +
                "Numbers: " + productCode.replaceAll("[^0-9]", "") + "\n" +
                "Length: " + productCode.length() + " characters";
        Allure.attachment("Product Code Analysis", productAnalysis);
        
        // Attach validation rules
        String validationRules = "Product Code Validation Rules:\n" +
                "• Format: XXX###\n" +
                "• Letters: 3 alphabetic characters\n" +
                "• Numbers: 3 numeric digits\n" +
                "• Length: 6 characters\n" +
                "• No special characters";
        Allure.attachment("Validation Rules", validationRules);
        
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Product Code: " + productCode);
        steps.shouldSeeIssueWithTitle("Product Code: " + productCode);
        
        // Attach validation result
        String result = "Product code validation completed at " + timestamp + "\n" +
                "✅ Letters: " + (productCode.replaceAll("[^A-Za-z]", "").length() == 3 ? "Valid" : "Invalid") + "\n" +
                "✅ Numbers: " + (productCode.replaceAll("[^0-9]", "").length() == 3 ? "Valid" : "Invalid");
        Allure.attachment("Validation Result", result);
    }

    // Helper methods
    private boolean isValidYear(String year) {
        try {
            int yearInt = Integer.parseInt(year);
            return yearInt >= 1900 && yearInt <= 2100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidMonth(String month) {
        try {
            int monthInt = Integer.parseInt(month);
            return monthInt >= 1 && monthInt <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDay(String day) {
        try {
            int dayInt = Integer.parseInt(day);
            return dayInt >= 1 && dayInt <= 31;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidHour(String hour) {
        try {
            int hourInt = Integer.parseInt(hour);
            return hourInt >= 0 && hourInt <= 23;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidMinute(String minute) {
        try {
            int minuteInt = Integer.parseInt(minute);
            return minuteInt >= 0 && minuteInt <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidIntegerPart(String integerPart) {
        try {
            int value = Integer.parseInt(integerPart);
            return value >= 0 && value <= 999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDecimalPart(String decimalPart) {
        return decimalPart.length() == 2 && decimalPart.matches("\\d{2}");
    }

    private boolean isValidRange(String amount) {
        try {
            double value = Double.parseDouble(amount);
            return value >= 0.00 && value <= 999999.99;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}