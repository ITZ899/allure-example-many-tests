package io.qameta.allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Layer("web")
@Owner("qa-team")
@Feature("User Management")
public class UserManagementTest {

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("UM-T1")
    @Microservice("Authentication")
    @Story("User registration")
    @Tags({@Tag("web"), @Tag("smoke"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-1")})
    @DisplayName("Register new user with valid credentials")
    public void shouldRegisterNewUser() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("User Registration Test");
        steps.shouldSeeIssueWithTitle("User Registration Test");
    }

    @Test
    @TM4J("UM-T2")
    @Microservice("Authentication")
    @Story("User login")
    @Tags({@Tag("web"), @Tag("smoke")})
    @JiraIssues({@JiraIssue("UM-2")})
    @DisplayName("Login with valid credentials")
    public void shouldLoginWithValidCredentials() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Login Test");
        steps.shouldSeeIssueWithTitle("Login Test");
    }

    @Test
    @TM4J("UM-T3")
    @Microservice("Authentication")
    @Story("User logout")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-3")})
    @DisplayName("Logout user successfully")
    public void shouldLogoutUser() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Logout Test");
        steps.closeIssueWithTitle("Logout Test");
    }

    @ParameterizedTest(name = "Login with invalid username: {0}")
    @ValueSource(strings = {"", "invalid_user", "user@123", "test_user_very_long_name_that_exceeds_limit"})
    @TM4J("UM-T4")
    @Microservice("Authentication")
    @Story("Invalid login attempts")
    @Tags({@Tag("web"), @Tag("negative"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-4")})
    public void shouldShowErrorForInvalidUsername(String username) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Invalid Username: " + username);
        steps.shouldSeeIssueWithTitle("Invalid Username: " + username);
    }

    @ParameterizedTest(name = "Login with invalid password: {0}")
    @ValueSource(strings = {"", "123", "weak", "password_with_special_chars!@#"})
    @TM4J("UM-T5")
    @Microservice("Authentication")
    @Story("Invalid login attempts")
    @Tags({@Tag("web"), @Tag("negative"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-5")})
    public void shouldShowErrorForInvalidPassword(String password) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Invalid Password: " + password);
        steps.shouldSeeIssueWithTitle("Invalid Password: " + password);
    }

    @Test
    @TM4J("UM-T6")
    @Microservice("Profile")
    @Story("Update user profile")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-6")})
    @DisplayName("Update user profile information")
    public void shouldUpdateUserProfile() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Profile Update Test");
        steps.shouldSeeIssueWithTitle("Profile Update Test");
    }

    @Test
    @TM4J("UM-T7")
    @Microservice("Profile")
    @Story("Change password")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-7")})
    @DisplayName("Change user password")
    public void shouldChangeUserPassword() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Password Change Test");
        steps.shouldSeeIssueWithTitle("Password Change Test");
    }

    @Test
    @TM4J("UM-T8")
    @Microservice("Profile")
    @Story("Delete user account")
    @Tags({@Tag("web"), @Tag("regression")})
    @JiraIssues({@JiraIssue("UM-8")})
    @DisplayName("Delete user account")
    public void shouldDeleteUserAccount() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Account Deletion Test");
        steps.closeIssueWithTitle("Account Deletion Test");
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }
}
