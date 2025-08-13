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
@Owner("analytics-specialist")
@Feature("Analytics Dashboard")
@TM4J("TM4J-126")
@Microservice("analytics-service")
@Story("Data analytics functionality")
@Tag("analytics") @Tag("dashboard") @Tag("web")
public class AnalyticsTest {

    private WebSteps steps;

    @BeforeEach
    void startDriver() {
        steps = new WebSteps();
    }

    @Test
    @DisplayName("Load analytics dashboard")
    @Story("Dashboard loading")
    @JiraIssues({@JiraIssue("JIRA-459")})
    public void shouldLoadAnalyticsDashboard() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Analytics Dashboard Loading");
        steps.shouldSeeIssueWithTitle("Analytics Dashboard Loading");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Real-time", "Historical", "Predictive", "Comparative", "Trend"})
    @DisplayName("Test different analytics views")
    @Story("Analytics views")
    public void shouldTestDifferentAnalyticsViews(String view) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Analytics View: " + view);
        steps.shouldSeeIssueWithTitle("Analytics View: " + view);
    }

    @ParameterizedTest
    @CsvSource({"Revenue, $100K", "Users, 10K", "Orders, 5K", "Conversion, 15%"})
    @DisplayName("Test different metrics display")
    @Story("Metrics display")
    public void shouldTestDifferentMetricsDisplay(String metric, String value) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Metric: " + metric + " = " + value);
        steps.shouldSeeIssueWithTitle("Metric: " + metric + " = " + value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Line Chart", "Bar Chart", "Pie Chart", "Scatter Plot", "Heat Map"})
    @DisplayName("Test different chart types")
    @Story("Chart types")
    public void shouldTestDifferentChartTypes(String chartType) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Chart Type: " + chartType);
        steps.shouldSeeIssueWithTitle("Chart Type: " + chartType);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Last Hour", "Last Day", "Last Week", "Last Month", "Last Year"})
    @DisplayName("Test different time ranges")
    @Story("Time ranges")
    public void shouldTestDifferentTimeRanges(String timeRange) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Time Range: " + timeRange);
        steps.shouldSeeIssueWithTitle("Time Range: " + timeRange);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Country", "City", "Region", "Timezone", "Language"})
    @DisplayName("Test different geographic filters")
    @Story("Geographic filters")
    public void shouldTestDifferentGeographicFilters(String filter) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Geographic Filter: " + filter);
        steps.shouldSeeIssueWithTitle("Geographic Filter: " + filter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Desktop", "Mobile", "Tablet", "Smart TV", "Wearable"})
    @DisplayName("Test different device filters")
    @Story("Device filters")
    public void shouldTestDifferentDeviceFilters(String device) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Device Filter: " + device);
        steps.shouldSeeIssueWithTitle("Device Filter: " + device);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Chrome", "Firefox", "Safari", "Edge", "Opera"})
    @DisplayName("Test different browser filters")
    @Story("Browser filters")
    public void shouldTestDifferentBrowserFilters(String browser) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Browser Filter: " + browser);
        steps.shouldSeeIssueWithTitle("Browser Filter: " + browser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"New Users", "Returning Users", "Active Users", "Inactive Users", "Churned Users"})
    @DisplayName("Test different user segment filters")
    @Story("User segments")
    public void shouldTestDifferentUserSegmentFilters(String segment) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("User Segment: " + segment);
        steps.shouldSeeIssueWithTitle("User Segment: " + segment);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Export PDF", "Export Excel", "Share Link", "Schedule Report", "API Access"})
    @DisplayName("Test different dashboard actions")
    @Story("Dashboard actions")
    public void shouldTestDifferentDashboardActions(String action) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Dashboard Action: " + action);
        steps.shouldSeeIssueWithTitle("Dashboard Action: " + action);
    }
}
