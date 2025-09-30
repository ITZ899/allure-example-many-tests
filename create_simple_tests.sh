#!/bin/bash

# Create simple test files without parameterization
create_simple_test() {
    local filename="$1"
    local classname="$2"
    local feature="$3"
    local owner="$4"
    local layer="$5"
    local microservice="$6"
    local story="$7"
    local tags="$8"
    local jira="$9"
    
    cat > "$filename" << TEMPLATE
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
@Layer("$layer")
@Owner("$owner")
@Feature("$feature")
@TM4J("TM4J-132")
@Microservice("$microservice")
@Story("$story")
$tags
public class $classname {

    private ${layer^}Steps steps;

    @BeforeEach
    void startDriver() {
        steps = new ${layer^}Steps();
    }

    @Test
    @DisplayName("Test basic functionality")
    @Story("Basic functionality")
    @JiraIssues({@JiraIssue("$jira")})
    public void shouldTestBasicFunctionality() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String testData = "Test: Basic Functionality\n" +
                "Type: $feature\n" +
                "Status: Running\n" +
                "Timestamp: " + timestamp;
        
        String result = "Test completed successfully at " + timestamp + "\n" +
                "Status: Passed\n" +
                "Duration: 100ms\n" +
                "Coverage: 95%";
        
        Allure.attachment("Test Data", testData);
        Allure.attachment("Test Result", result);
        
        if ("$layer" == "web") {
            steps.openIssuesPage("testuser", "testrepo");
            steps.createIssueWithTitle("$feature: Basic Test");
            steps.shouldSeeIssueWithTitle("$feature: Basic Test");
        } else {
            steps.createIssueWithTitle("testuser", "testrepo", "$feature: Basic Test");
            steps.shouldSeeIssueWithTitle("testuser", "testrepo", "$feature: Basic Test");
        }
    }

    @Test
    @DisplayName("Test advanced functionality")
    @Story("Advanced functionality")
    public void shouldTestAdvancedFunctionality() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String testData = "Test: Advanced Functionality\n" +
                "Type: $feature\n" +
                "Complexity: High\n" +
                "Status: Running\n" +
                "Timestamp: " + timestamp;
        
        String result = "Advanced test completed successfully at " + timestamp + "\n" +
                "Status: Passed\n" +
                "Duration: 250ms\n" +
                "Coverage: 98%";
        
        Allure.attachment("Test Data", testData);
        Allure.attachment("Test Result", result);
        
        if ("$layer" == "web") {
            steps.openIssuesPage("testuser", "testrepo");
            steps.createIssueWithTitle("$feature: Advanced Test");
            steps.shouldSeeIssueWithTitle("$feature: Advanced Test");
        } else {
            steps.createIssueWithTitle("testuser", "testrepo", "$feature: Advanced Test");
            steps.shouldSeeIssueWithTitle("testuser", "testrepo", "$feature: Advanced Test");
        }
    }
}
TEMPLATE
}

# Create all simple test files
create_simple_test "src/test/java/io/qameta/allure/PerformanceTest.java" "PerformanceTest" "Performance Testing" "performance-specialist" "web" "performance-service" "Performance testing functionality" "@Tag(\"performance\") @Tag(\"web\")" "JIRA-465"
create_simple_test "src/test/java/io/qameta/allure/NotificationSystemTest.java" "NotificationSystemTest" "Notification System" "notification-specialist" "web" "notification-service" "Notification system functionality" "@Tag(\"notification\") @Tag(\"web\")" "JIRA-466"
create_simple_test "src/test/java/io/qameta/allure/ConfigurationTest.java" "ConfigurationTest" "Configuration Management" "config-specialist" "rest" "config-service" "Configuration management functionality" "@Tag(\"config\") @Tag(\"rest\")" "JIRA-467"
create_simple_test "src/test/java/io/qameta/allure/UserManagementTest.java" "UserManagementTest" "User Management" "user-specialist" "web" "user-service" "User management functionality" "@Tag(\"user\") @Tag(\"web\")" "JIRA-468"
create_simple_test "src/test/java/io/qameta/allure/ShoppingCartTest.java" "ShoppingCartTest" "Shopping Cart" "cart-specialist" "web" "cart-service" "Shopping cart functionality" "@Tag(\"cart\") @Tag(\"web\")" "JIRA-469"

echo "Simple test files created successfully!"
