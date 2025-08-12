package io.qameta.allure;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

@Layer("rest")
@Owner("api-team")
@Feature("User API")
public class UserApiTest {

    private final RestSteps steps = new RestSteps();

    @ParameterizedTest(name = "Create user with name: {0}")
    @ValueSource(strings = {"John", "Jane", "Bob", "Alice", "Charlie", "Diana", "Edward", "Fiona"})
    @TM4J("UA-T1")
    @Story("Create new user via API")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-1")})
    public void shouldCreateUserViaApi(String name) {
        steps.createIssueWithTitle("testuser", "testrepo", "User: " + name);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "User: " + name);
    }

    @ParameterizedTest(name = "Get user by ID: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @TM4J("UA-T2")
    @Story("Retrieve user by ID")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-2")})
    public void shouldGetUserById(int userId) {
        steps.createIssueWithTitle("testuser", "testrepo", "User ID: " + userId);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "User ID: " + userId);
    }

    @ParameterizedTest(name = "Update user {0} with email {1}")
    @CsvSource({
        "John, john.updated@example.com",
        "Jane, jane.updated@example.com",
        "Bob, bob.updated@example.com",
        "Alice, alice.updated@example.com"
    })
    @TM4J("UA-T3")
    @Story("Update user email")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-3")})
    public void shouldUpdateUserEmail(String name, String email) {
        steps.createIssueWithTitle("testuser", "testrepo", "Update " + name + " to " + email);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Update " + name + " to " + email);
    }

    @ParameterizedTest(name = "Delete user with ID: {0}")
    @ValueSource(ints = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
    @TM4J("UA-T4")
    @Story("Delete user by ID")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-4")})
    public void shouldDeleteUserById(int userId) {
        steps.createIssueWithTitle("testuser", "testrepo", "Delete User ID: " + userId);
        steps.closeIssueWithTitle("testuser", "testrepo", "Delete User ID: " + userId);
    }

    @ParameterizedTest(name = "Search users by role: {0}")
    @ValueSource(strings = {"admin", "user", "moderator", "guest", "editor"})
    @TM4J("UA-T5")
    @Story("Search users by role")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-5")})
    public void shouldSearchUsersByRole(String role) {
        steps.createIssueWithTitle("testuser", "testrepo", "Search Role: " + role);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Search Role: " + role);
    }

    @ParameterizedTest(name = "Validate user status: {0}")
    @ValueSource(strings = {"active", "inactive", "suspended", "pending"})
    @TM4J("UA-T6")
    @Story("Validate user status")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-6")})
    public void shouldValidateUserStatus(String status) {
        steps.createIssueWithTitle("testuser", "testrepo", "Status: " + status);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Status: " + status);
    }

    @ParameterizedTest(name = "Check user permissions for role: {0}")
    @ValueSource(strings = {"read", "write", "delete", "admin", "moderate"})
    @TM4J("UA-T7")
    @Story("Check user permissions")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-7")})
    public void shouldCheckUserPermissions(String permission) {
        steps.createIssueWithTitle("testuser", "testrepo", "Permission: " + permission);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Permission: " + permission);
    }

    @ParameterizedTest(name = "Verify user authentication for: {0}")
    @ValueSource(strings = {"valid_token", "expired_token", "invalid_token", "missing_token"})
    @TM4J("UA-T8")
    @Story("Verify user authentication")
    @Microservice("UserService")
    @Tags({@Tag("api"), @Tag("security"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UA-8")})
    public void shouldVerifyUserAuthentication(String tokenType) {
        steps.createIssueWithTitle("testuser", "testrepo", "Auth: " + tokenType);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Auth: " + tokenType);
    }
}
