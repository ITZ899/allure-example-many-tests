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
)

# Function to add attachments to a file
add_attachments() {
    local file="$1"
    local classname=$(basename "$file" .java)
    echo "Adding attachments to $file..."
    
    # Determine if it's an API test
    if [[ "$file" == *"Api"* ]]; then
        method_calls="        steps.createIssueWithTitle(\"testuser\", \"testrepo\", \"$classname Test\");\n        steps.shouldSeeIssueWithTitle(\"testuser\", \"testrepo\", \"$classname Test\");"
    else
        method_calls="        steps.openIssuesPage(\"testuser\", \"testrepo\");\n        steps.createIssueWithTitle(\"$classname Test\");\n        steps.shouldSeeIssueWithTitle(\"$classname Test\");"
    fi
    
    # Add timestamp import if not present
    if ! grep -q "import java.time.LocalDateTime;" "$file"; then
        sed -i '' '/import org.junit.jupiter.api.Tag;/a\
import java.time.LocalDateTime;\
import java.time.format.DateTimeFormatter;
' "$file"
    fi
    
    # Create the new method content
    cat > "${file}.new_method" << INNER_EOF
    public void shouldTestBasicFunctionality() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Test Configuration (JSON)
        String testConfig = "{\\n" +
                "  \\"testId\\": \\"$classname\\",\\n" +
                "  \\"version\\": \\"1.0.0\\",\\n" +
                "  \\"timestamp\\": \\"" + timestamp + "\\",\\n" +
                "  \\"status\\": \\"running\\"\\n" +
                "}";
        
        // Test Report (HTML)
        String htmlReport = "<!DOCTYPE html>\\n" +
                "<html><head><title>$classname Test Report</title></head>\\n" +
                "<body><h1>$classname Test Report</h1>\\n" +
                "<p>Generated: " + timestamp + "</p>\\n" +
                "<h2>Test Results</h2>\\n" +
                "<ul><li>Status: ✅ PASSED</li><li>Duration: 150ms</li></ul>\\n" +
                "</body></html>";
        
        // Test Data (CSV)
        String testData = "test_id,status,duration,result\\n" +
                "$classname,passed,150ms,success\\n" +
                "$classname-advanced,passed,200ms,success";
        
        // Test Schema (XML)
        String testSchema = "<?xml version=\\"1.0\\" encoding=\\"UTF-8\\"?>\\n" +
                "<test-schema>\\n" +
                "  <test name=\\"$classname\\" type=\\"functional\\">\\n" +
                "    <status>passed</status>\\n" +
                "    <duration>150ms</duration>\\n" +
                "  </test>\\n" +
                "</test-schema>";
        
        // Test Log (Plain Text)
        String testLog = "$classname TEST LOG\\n" +
                "==================\\n\\n" +
                "Test: $classname\\n" +
                "Timestamp: " + timestamp + "\\n" +
                "Status: ✅ PASSED\\n" +
                "Duration: 150ms\\n" +
                "Result: Success";
        
        // Test JavaScript
        String testJS = "// $classname Test JavaScript\\n" +
                "function run${classname}Test() {\\n" +
                "  console.log(\\"Running $classname test...\\");\\n" +
                "  return { status: \\"passed\\", duration: \\"150ms\\" };\\n" +
                "}";
        
        Allure.attachment("Test Configuration (JSON)", testConfig);
        Allure.attachment("Test Report (HTML)", htmlReport);
        Allure.attachment("Test Data (CSV)", testData);
        Allure.attachment("Test Schema (XML)", testSchema);
        Allure.attachment("Test Log (Text)", testLog);
        Allure.attachment("Test JavaScript", testJS);
        
$method_calls
    }
INNER_EOF
    
    # Replace the method in the file
    sed -i '' '/public void shouldTestBasicFunctionality() {/,/^    }/c\
'"$(cat "${file}.new_method")"'
' "$file"
    
    # Clean up
    rm "${file}.new_method"
    
    echo "Completed $file"
}

# Process each file
for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        add_attachments "$file"
    else
        echo "File $file not found, skipping..."
    fi
done

echo "All attachments added!"
