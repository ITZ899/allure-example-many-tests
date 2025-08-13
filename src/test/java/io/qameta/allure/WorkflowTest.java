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
@Owner("workflow-engineer")
@Feature("Workflow Management")
@TM4J("TM4J-125")
@Microservice("workflow-service")
@Story("Workflow automation functionality")
@Tag("workflow") @Tag("automation") @Tag("web")
public class WorkflowTest {

    private WebSteps steps;

    @BeforeEach
    void startDriver() {
        steps = new WebSteps();
    }

    @Test
    @DisplayName("Create new workflow")
    @Story("Workflow creation")
    @JiraIssues({@JiraIssue("JIRA-458")})
    public void shouldCreateNewWorkflow() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Creation Test");
        steps.shouldSeeIssueWithTitle("Workflow Creation Test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Approval", "Review", "Processing", "Validation", "Completion"})
    @DisplayName("Test different workflow stages")
    @Story("Workflow stages")
    public void shouldTestDifferentWorkflowStages(String stage) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Stage: " + stage);
        steps.shouldSeeIssueWithTitle("Workflow Stage: " + stage);
    }

    @ParameterizedTest
    @CsvSource({"User Registration, 3", "Order Processing, 5", "Invoice Generation, 4", "Support Ticket, 6"})
    @DisplayName("Test workflows with different step counts")
    @Story("Workflow complexity")
    public void shouldTestWorkflowsWithDifferentStepCounts(String workflow, int stepCount) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow: " + workflow + " (" + stepCount + " steps)");
        steps.shouldSeeIssueWithTitle("Workflow: " + workflow + " (" + stepCount + " steps)");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sequential", "Parallel", "Conditional", "Loop", "Fork-Join"})
    @DisplayName("Test different workflow patterns")
    @Story("Workflow patterns")
    public void shouldTestDifferentWorkflowPatterns(String pattern) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Pattern: " + pattern);
        steps.shouldSeeIssueWithTitle("Workflow Pattern: " + pattern);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Low", "Medium", "High", "Critical", "Emergency"})
    @DisplayName("Test workflows with different priority levels")
    @Story("Workflow priority")
    public void shouldTestWorkflowsWithDifferentPriorityLevels(String priority) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Priority: " + priority);
        steps.shouldSeeIssueWithTitle("Workflow Priority: " + priority);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Manual", "Automatic", "Semi-automatic", "Scheduled", "Event-driven"})
    @DisplayName("Test workflows with different trigger types")
    @Story("Workflow triggers")
    public void shouldTestWorkflowsWithDifferentTriggerTypes(String trigger) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Trigger: " + trigger);
        steps.shouldSeeIssueWithTitle("Workflow Trigger: " + trigger);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Active", "Paused", "Completed", "Cancelled", "Error"})
    @DisplayName("Test workflows with different statuses")
    @Story("Workflow statuses")
    public void shouldTestWorkflowsWithDifferentStatuses(String status) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Workflow Status: " + status);
        steps.shouldSeeIssueWithTitle("Workflow Status: " + status);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Department A", "Department B", "Department C", "Cross-functional", "External"})
    @DisplayName("Test workflows for different departments")
    @Story("Department workflows")
    public void shouldTestWorkflowsForDifferentDepartments(String department) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Department: " + department);
        steps.shouldSeeIssueWithTitle("Department: " + department);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Simple", "Complex", "Very Complex", "Enterprise", "Mission Critical"})
    @DisplayName("Test workflows with different complexity levels")
    @Story("Workflow complexity levels")
    public void shouldTestWorkflowsWithDifferentComplexityLevels(String complexity) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Complexity: " + complexity);
        steps.shouldSeeIssueWithTitle("Complexity: " + complexity);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Immediate", "1 hour", "1 day", "1 week", "1 month"})
    @DisplayName("Test workflows with different SLA requirements")
    @Story("Workflow SLA")
    public void shouldTestWorkflowsWithDifferentSLARequirements(String sla) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("SLA Requirement: " + sla);
        steps.shouldSeeIssueWithTitle("SLA Requirement: " + sla);
    }
}
