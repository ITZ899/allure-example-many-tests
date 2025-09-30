#!/bin/bash

# List of files that need attachments
files=(
    "src/test/java/io/qameta/allure/PerformanceTest.java"
    "src/test/java/io/qameta/allure/UserManagementTest.java"
    "src/test/java/io/qameta/allure/ShoppingCartTest.java"
    "src/test/java/io/qameta/allure/ProductManagementTest.java"
    "src/test/java/io/qameta/allure/EventTest.java"
    "src/test/java/io/qameta/allure/LoggingTest.java"
    "src/test/java/io/qameta/allure/OrderManagementTest.java"
    "src/test/java/io/qameta/allure/SecurityTest.java"
    "src/test/java/io/qameta/allure/IntegrationTest.java"
    "src/test/java/io/qameta/allure/MobileTest.java"
    "src/test/java/io/qameta/allure/ReportingTest.java"
    "src/test/java/io/qameta/allure/SearchFunctionalityTest.java"
    "src/test/java/io/qameta/allure/ConfigurationTest.java"
    "src/test/java/io/qameta/allure/ProductApiTest.java"
    "src/test/java/io/qameta/allure/OrderApiTest.java"
    "src/test/java/io/qameta/allure/NotificationApiTest.java"
    "src/test/java/io/qameta/allure/IssuesRestTest.java"
    "src/test/java/io/qameta/allure/IssuesWebTest.java"
    "src/test/java/io/qameta/allure/PullRequestsWebTest.java"
)

for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        echo "Adding attachments to $file..."
        
        # Add timestamp import if not present
        if ! grep -q "import java.time.LocalDateTime;" "$file"; then
            sed -i '' '/import org.junit.jupiter.api.Tag;/a\
import java.time.LocalDateTime;\
import java.time.format.DateTimeFormatter;
' "$file"
        fi
        
        # Add simple attachments to the first test method
        sed -i '' '/public void shouldTestBasicFunctionality() {/,/^    }/c\
    public void shouldTestBasicFunctionality() {\
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));\
        \
        String testConfig = "{\\n" +\
                "  \\"testId\\": \\"'$(basename "$file" .java)'\\",\\n" +\
                "  \\"timestamp\\": \\"" + timestamp + "\\"\\n" +\
                "}";\
        \
        String htmlReport = "<!DOCTYPE html>\\n<html><head><title>Test Report</title></head>\\n<body><h1>Test Report</h1><p>Generated: " + timestamp + "</p></body></html>";\
        \
        String testData = "test_id,status,duration\\n'$(basename "$file" .java)',passed,150ms";\
        \
        String testLog = "TEST LOG\\n========\\nTest: '$(basename "$file" .java)'\\nTimestamp: " + timestamp + "\\nStatus: PASSED";\
        \
        Allure.attachment("Test Configuration (JSON)", testConfig);\
        Allure.attachment("Test Report (HTML)", htmlReport);\
        Allure.attachment("Test Data (CSV)", testData);\
        Allure.attachment("Test Log (Text)", testLog);\
        \
        steps.openIssuesPage("testuser", "testrepo");\
        steps.createIssueWithTitle("'$(basename "$file" .java)' Test");\
        steps.shouldSeeIssueWithTitle("'$(basename "$file" .java)' Test");\
    }' "$file"
        
        echo "Completed $file"
    else
        echo "File $file not found, skipping..."
    fi
done

echo "All simple attachments added!"
