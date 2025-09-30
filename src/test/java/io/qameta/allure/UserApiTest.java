package io.qameta.allure;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(AllureJunit5.class)
@Layer("rest")
@Owner("api-specialist")
@Feature("User API")
@TM4J("TM4J-128")
@Microservice("user-service")
@Story("User API functionality")
@Tag("api") @Tag("user") @Tag("rest")
public class UserApiTest {

    private RestSteps steps = new RestSteps();

    @Test
    @DisplayName("Create user via API")
    @Story("User creation")
    @JiraIssues({@JiraIssue("JIRA-461")})
    public void shouldCreateUserViaApi() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // User Data (JSON)
        String userData = "{\n" +
                "  \"id\": 12345,\n" +
                "  \"username\": \"testuser\",\n" +
                "  \"email\": \"testuser@example.com\",\n" +
                "  \"firstName\": \"Test\",\n" +
                "  \"lastName\": \"User\",\n" +
                "  \"age\": 25,\n" +
                "  \"role\": \"user\",\n" +
                "  \"createdAt\": \"" + timestamp + "\",\n" +
                "  \"status\": \"active\"\n" +
                "}";
        
        // API Response (JSON)
        String apiResponse = "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"User created successfully\",\n" +
                "  \"data\": {\n" +
                "    \"id\": 12345,\n" +
                "    \"username\": \"testuser\",\n" +
                "    \"email\": \"testuser@example.com\"\n" +
                "  },\n" +
                "  \"timestamp\": \"" + timestamp + "\",\n" +
                "  \"statusCode\": 201\n" +
                "}";
        
        // API Documentation (HTML)
        String apiDoc = "<!DOCTYPE html>\n" +
                "<html><head><title>User API Documentation</title></head>\n" +
                "<body><h1>User API Documentation</h1>\n" +
                "<h2>POST /api/users</h2>\n" +
                "<p>Creates a new user in the system.</p>\n" +
                "<h3>Request Body:</h3>\n" +
                "<pre>{\n" +
                "  \"username\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"age\": \"number\"\n" +
                "}</pre>\n" +
                "<h3>Response:</h3>\n" +
                "<pre>{\n" +
                "  \"success\": \"boolean\",\n" +
                "  \"message\": \"string\",\n" +
                "  \"data\": \"object\"\n" +
                "}</pre>\n" +
                "<p><em>Generated at: " + timestamp + "</em></p>\n" +
                "</body></html>";
        
        // Request/Response Log (CSV)
        String requestLog = "timestamp,method,endpoint,status,response_time\n" +
                timestamp + ",POST,/api/users,201,150ms\n" +
                timestamp + ",GET,/api/users/12345,200,45ms\n" +
                timestamp + ",PUT,/api/users/12345,200,120ms\n" +
                timestamp + ",DELETE,/api/users/12345,204,80ms";
        
        // API Schema (XML)
        String apiSchema = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<api-schema>\n" +
                "  <endpoint path=\"/api/users\" method=\"POST\">\n" +
                "    <description>Create new user</description>\n" +
                "    <request>\n" +
                "      <field name=\"username\" type=\"string\" required=\"true\"/>\n" +
                "      <field name=\"email\" type=\"string\" required=\"true\"/>\n" +
                "      <field name=\"firstName\" type=\"string\" required=\"true\"/>\n" +
                "      <field name=\"lastName\" type=\"string\" required=\"true\"/>\n" +
                "      <field name=\"age\" type=\"integer\" required=\"true\"/>\n" +
                "    </request>\n" +
                "    <response status=\"201\">\n" +
                "      <field name=\"success\" type=\"boolean\"/>\n" +
                "      <field name=\"message\" type=\"string\"/>\n" +
                "      <field name=\"data\" type=\"object\"/>\n" +
                "    </response>\n" +
                "  </endpoint>\n" +
                "</api-schema>";
        
        // Test Report (Plain Text)
        String testReport = "USER API TEST REPORT\n" +
                "====================\n\n" +
                "Test: Create User via API\n" +
                "Timestamp: " + timestamp + "\n" +
                "Status: ✅ PASSED\n\n" +
                "REQUEST DETAILS:\n" +
                "Method: POST\n" +
                "Endpoint: /api/users\n" +
                "Content-Type: application/json\n" +
                "Body: User data with username, email, firstName, lastName, age\n\n" +
                "RESPONSE DETAILS:\n" +
                "Status Code: 201 Created\n" +
                "Response Time: 150ms\n" +
                "Content-Type: application/json\n" +
                "Body: Success response with user ID and details\n\n" +
                "VALIDATION:\n" +
                "✓ Status code is 201\n" +
                "✓ Response contains user ID\n" +
                "✓ Response time is acceptable (< 200ms)\n" +
                "✓ JSON structure is valid\n\n" +
                "RESULT: Test passed successfully";
        
        // cURL Command
        String curlCommand = "# cURL command for creating user\n" +
                "curl -X POST 'https://api.example.com/users' \\\n" +
                "  -H 'Content-Type: application/json' \\\n" +
                "  -H 'Authorization: Bearer your-token-here' \\\n" +
                "  -d '{\n" +
                "    \"username\": \"testuser\",\n" +
                "    \"email\": \"testuser@example.com\",\n" +
                "    \"firstName\": \"Test\",\n" +
                "    \"lastName\": \"User\",\n" +
                "    \"age\": 25\n" +
                "  }'";
        
        Allure.attachment("User Data (JSON)", userData);
        Allure.attachment("API Response (JSON)", apiResponse);
        Allure.attachment("API Documentation (HTML)", apiDoc);
        Allure.attachment("Request Log (CSV)", requestLog);
        Allure.attachment("API Schema (XML)", apiSchema);
        Allure.attachment("Test Report (Text)", testReport);
        Allure.attachment("cURL Command", curlCommand);
        
        steps.createIssueWithTitle("testuser", "testrepo", "User API Test: Create User");
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "User API Test: Create User");
    }

    @Test
    @DisplayName("Get user by ID via API")
    @Story("User retrieval")
    public void shouldGetUserByIdViaApi() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        // User Profile (JSON)
        String userProfile = "{\n" +
                "  \"id\": 12345,\n" +
                "  \"username\": \"testuser\",\n" +
                "  \"email\": \"testuser@example.com\",\n" +
                "  \"profile\": {\n" +
                "    \"firstName\": \"Test\",\n" +
                "    \"lastName\": \"User\",\n" +
                "    \"age\": 25,\n" +
                "    \"avatar\": \"https://example.com/avatars/testuser.jpg\",\n" +
                "    \"bio\": \"Software developer and tester\"\n" +
                "  },\n" +
                "  \"preferences\": {\n" +
                "    \"theme\": \"dark\",\n" +
                "    \"language\": \"en\",\n" +
                "    \"notifications\": true\n" +
                "  },\n" +
                "  \"lastLogin\": \"" + timestamp + "\"\n" +
                "}";
        
        // API Performance Metrics (CSV)
        String performanceMetrics = "endpoint,method,avg_response_time,min_response_time,max_response_time,success_rate\n" +
                "/api/users/12345,GET,45ms,30ms,120ms,99.9%\n" +
                "/api/users,POST,150ms,100ms,300ms,99.5%\n" +
                "/api/users/12345,PUT,120ms,80ms,250ms,99.7%\n" +
                "/api/users/12345,DELETE,80ms,50ms,200ms,99.8%";
        
        // Error Log (Plain Text)
        String errorLog = "API ERROR LOG\n" +
                "==============\n\n" +
                "Timestamp: " + timestamp + "\n" +
                "Status: No errors detected\n" +
                "Last Error: None\n" +
                "Error Rate: 0.1%\n" +
                "Uptime: 99.9%\n\n" +
                "RECENT REQUESTS:\n" +
                "GET /api/users/12345 - 200 OK (45ms)\n" +
                "POST /api/users - 201 Created (150ms)\n" +
                "PUT /api/users/12345 - 200 OK (120ms)";
        
        Allure.attachment("User Profile (JSON)", userProfile);
        Allure.attachment("Performance Metrics (CSV)", performanceMetrics);
        Allure.attachment("Error Log (Text)", errorLog);
        
        steps.createIssueWithTitle("testuser", "testrepo", "User API Test: Get User by ID");
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "User API Test: Get User by ID");
    }
}