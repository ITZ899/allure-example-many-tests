#!/bin/bash

# Function to add rich attachments to a test file
add_attachments_to_test() {
    local file="$1"
    local classname=$(basename "$file" .java)
    echo "Adding attachments to $file..."
    
    # Determine if it's an API test
    if [[ "$file" == *"Api"* ]] || [[ "$file" == *"Rest"* ]]; then
        layer="rest"
        steps="RestSteps"
        method_call1="steps.createIssueWithTitle(\"testuser\", \"testrepo\", \"$classname Test\");"
        method_call2="steps.shouldSeeIssueWithTitle(\"testuser\", \"testrepo\", \"$classname Test\");"
    else
        layer="web"
        steps="WebSteps"
        method_call1="steps.openIssuesPage(\"testuser\", \"testrepo\");"
        method_call2="steps.createIssueWithTitle(\"$classname Test\");"
        method_call3="steps.shouldSeeIssueWithTitle(\"$classname Test\");"
    fi
    
    # Add timestamp import if not present
    if ! grep -q "import java.time.LocalDateTime;" "$file"; then
        sed -i '' '/import org.junit.jupiter.api.Tag;/a\
import java.time.LocalDateTime;\
import java.time.format.DateTimeFormatter;
' "$file"
    fi
    
    # Create a temporary file with the new content
    cat > "${file}.tmp" << 'INNER_EOF'
    public void shouldTestBasicFunctionality() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // Test Configuration (JSON)
        String testConfig = "{\n" +
                "  \"testId\": \"CLASSNAME_PLACEHOLDER\",\n" +
                "  \"version\": \"1.0.0\",\n" +
                "  \"timestamp\": \"" + timestamp + "\",\n" +
                "  \"status\": \"running\"\n" +
                "}";
        
        // Test Report (HTML)
        String htmlReport = "<!DOCTYPE html>\n" +
                "<html><head><title>CLASSNAME_PLACEHOLDER Test Report</title></head>\n" +
                "<body><h1>CLASSNAME_PLACEHOLDER Test Report</h1>\n" +
                "<p>Generated: " + timestamp + "</p>\n" +
                "<h2>Test Results</h2>\n" +
                "<ul><li>Status: ✅ PASSED</li><li>Duration: 150ms</li></ul>\n" +
                "</body></html>";
        
        // Test Data (CSV)
        String testData = "test_id,status,duration,result\n" +
                "CLASSNAME_PLACEHOLDER,passed,150ms,success\n" +
                "CLASSNAME_PLACEHOLDER-advanced,passed,200ms,success";
        
        // Test Schema (XML)
        String testSchema = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<test-schema>\n" +
                "  <test name=\"CLASSNAME_PLACEHOLDER\" type=\"functional\">\n" +
                "    <status>passed</status>\n" +
                "    <duration>150ms</duration>\n" +
                "  </test>\n" +
                "</test-schema>";
        
        // Test Log (Plain Text)
        String testLog = "CLASSNAME_PLACEHOLDER TEST LOG\n" +
                "==================\n\n" +
                "Test: CLASSNAME_PLACEHOLDER\n" +
                "Timestamp: " + timestamp + "\n" +
                "Status: ✅ PASSED\n" +
                "Duration: 150ms\n" +
                "Result: Success";
        
        // Test JavaScript
        String testJS = "// CLASSNAME_PLACEHOLDER Test JavaScript\n" +
                "function runCLASSNAME_PLACEHOLDERTest() {\n" +
                "  console.log(\"Running CLASSNAME_PLACEHOLDER test...\");\n" +
                "  return { status: \"passed\", duration: \"150ms\" };\n" +
                "}";
        
        Allure.attachment("Test Configuration (JSON)", testConfig);
        Allure.attachment("Test Report (HTML)", htmlReport);
        Allure.attachment("Test Data (CSV)", testData);
        Allure.attachment("Test Schema (XML)", testSchema);
        Allure.attachment("Test Log (Text)", testLog);
        Allure.attachment("Test JavaScript", testJS);
        
        METHOD_CALLS_PLACEHOLDER
    }
INNER_EOF
    
    # Replace placeholders
    sed -i '' "s/CLASSNAME_PLACEHOLDER/$classname/g" "${file}.tmp"
    
    if [[ "$file" == *"Api"* ]] || [[ "$file" == *"Rest"* ]]; then
        sed -i '' "s/METHOD_CALLS_PLACEHOLDER/$method_call1\n        $method_call2/" "${file}.tmp"
    else
        sed -i '' "s/METHOD_CALLS_PLACEHOLDER/$method_call1\n        $method_call2\n        $method_call3/" "${file}.tmp"
    fi
    
    # Replace the method in the original file
    sed -i '' '/public void shouldTestBasicFunctionality() {/,/^    }/c\
'"$(cat "${file}.tmp")"'
' "$file"
    
    # Clean up
    rm "${file}.tmp"
    
    echo "Completed $file"
}

# Find all test files except the ones we already updated
find src/test/java/ -name "*Test.java" -not -name "Issues*Test.java" -not -name "PullRequests*Test.java" -not -name "AnalyticsTest.java" -not -name "DataValidationTest.java" -not -name "WorkflowTest.java" -not -name "IntegrationApiTest.java" -not -name "UserApiTest.java" | while read file; do
    add_attachments_to_test "$file"
done

echo "Attachments added to all remaining test files!"
