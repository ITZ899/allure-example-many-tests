package io.qameta.allure;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@ExtendWith(AllureJunit5.class)
@Layer("rest")
@Owner("database-admin")
@Feature("Database Operations")
@TM4J("TM4J-129")
@Microservice("database-service")
@Story("Database functionality")
@Tag("database") @Tag("sql") @Tag("rest")
public class DatabaseTest {

    private RestSteps steps = new RestSteps();

    @Test
    @DisplayName("Test database connection")
    @Story("Database connection")
    @JiraIssues({@JiraIssue("JIRA-462")})
    public void shouldTestDatabaseConnection() {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Connection Test");
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Connection Test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"SELECT", "INSERT", "UPDATE", "DELETE", "CREATE"})
    @DisplayName("Test different SQL operations")
    @Story("SQL operations")
    public void shouldTestDifferentSqlOperations(String operation) {
        steps.createIssueWithTitle("testuser", "testrepo", "SQL Operation: " + operation);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "SQL Operation: " + operation);
    }

    @ParameterizedTest
    @CsvSource({"Users, 1000", "Products, 500", "Orders, 2500", "Categories, 50"})
    @DisplayName("Test different table operations")
    @Story("Table operations")
    public void shouldTestDifferentTableOperations(String table, int records) {
        steps.createIssueWithTitle("testuser", "testrepo", "Table: " + table + " (" + records + " records)");
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Table: " + table + " (" + records + " records)");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Primary Key", "Foreign Key", "Unique", "Index", "Constraint"})
    @DisplayName("Test different database constraints")
    @Story("Database constraints")
    public void shouldTestDifferentDatabaseConstraints(String constraint) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Constraint: " + constraint);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Constraint: " + constraint);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Transaction", "Commit", "Rollback", "Savepoint", "Isolation"})
    @DisplayName("Test different transaction features")
    @Story("Transaction management")
    public void shouldTestDifferentTransactionFeatures(String feature) {
        steps.createIssueWithTitle("testuser", "testrepo", "Transaction Feature: " + feature);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Transaction Feature: " + feature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backup", "Restore", "Replication", "Clustering", "Sharding"})
    @DisplayName("Test different database maintenance operations")
    @Story("Database maintenance")
    public void shouldTestDifferentDatabaseMaintenanceOperations(String operation) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Maintenance: " + operation);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Maintenance: " + operation);
    }

    @ParameterizedTest
    @ValueSource(strings = {"MySQL", "PostgreSQL", "Oracle", "SQL Server", "MongoDB"})
    @DisplayName("Test different database types")
    @Story("Database types")
    public void shouldTestDifferentDatabaseTypes(String dbType) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Type: " + dbType);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Type: " + dbType);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Connection Pool", "Query Cache", "Result Cache", "Buffer Pool", "Log Buffer"})
    @DisplayName("Test different database performance features")
    @Story("Database performance")
    public void shouldTestDifferentDatabasePerformanceFeatures(String feature) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Performance: " + feature);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Performance: " + feature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"User Management", "Role Management", "Permission Management", "Access Control", "Audit Log"})
    @DisplayName("Test different database security features")
    @Story("Database security")
    public void shouldTestDifferentDatabaseSecurityFeatures(String feature) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Security: " + feature);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Security: " + feature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Stored Procedure", "Function", "Trigger", "View", "Materialized View"})
    @DisplayName("Test different database objects")
    @Story("Database objects")
    public void shouldTestDifferentDatabaseObjects(String object) {
        steps.createIssueWithTitle("testuser", "testrepo", "Database Object: " + object);
        steps.shouldSeeIssueWithTitle("testuser", "testrepo", "Database Object: " + object);
    }
}
