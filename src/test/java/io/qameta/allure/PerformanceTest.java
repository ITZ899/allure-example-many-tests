package io.qameta.allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

@Layer("web")
@Owner("performance-team")
@Feature("Performance")
public class PerformanceTest {

    private final WebSteps steps = new WebSteps();

    @BeforeEach
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @TM4J("PERF-T1")
    @Microservice("Frontend")
    @Story("Page load time")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-1")})
    @DisplayName("Homepage should load within 3 seconds")
    public void shouldLoadHomepageWithinTimeLimit() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Performance Test 1");
        steps.shouldSeeIssueWithTitle("Performance Test 1");
    }

    @ParameterizedTest(name = "Test page load time for: {0}")
    @ValueSource(strings = {"Products", "About", "Contact", "Help", "FAQ", "Terms", "Privacy", "Support"})
    @TM4J("PERF-T2")
    @Microservice("Frontend")
    @Story("Page load performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T2")})
    public void shouldLoadPagesWithinTimeLimit(String pageName) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Page Load: " + pageName);
        steps.shouldSeeIssueWithTitle("Page Load: " + pageName);
    }

    @ParameterizedTest(name = "Test search performance with {0} results")
    @ValueSource(ints = {10, 25, 50, 100, 250, 500, 1000})
    @TM4J("PERF-T3")
    @Microservice("Search")
    @Story("Search performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T3")})
    public void shouldSearchWithinTimeLimit(int resultCount) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Search Performance: " + resultCount + " results");
        steps.shouldSeeIssueWithTitle("Search Performance: " + resultCount + " results");
    }

    @Test
    @TM4J("PERF-T4")
    @Microservice("Database")
    @Story("Database query performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T4")})
    @DisplayName("Database queries should execute within time limit")
    public void shouldExecuteDatabaseQueriesQuickly() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Database Performance Test");
        steps.shouldSeeIssueWithTitle("Database Performance Test");
    }

    @ParameterizedTest(name = "Test image loading performance for: {0}")
    @ValueSource(strings = {"Small", "Medium", "Large", "Thumbnail", "Full Size", "Gallery"})
    @TM4J("PERF-T5")
    @Microservice("Media")
    @Story("Image loading performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T5")})
    public void shouldLoadImagesQuickly(String imageSize) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Image Load: " + imageSize);
        steps.shouldSeeIssueWithTitle("Image Load: " + imageSize);
    }

    @Test
    @TM4J("PERF-T6")
    @Microservice("API")
    @Story("API response time")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T6")})
    @DisplayName("API endpoints should respond within time limit")
    public void shouldRespondWithinTimeLimit() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("API Performance Test");
        steps.shouldSeeIssueWithTitle("API Performance Test");
    }

    @ParameterizedTest(name = "Test memory usage for: {0} operations")
    @ValueSource(ints = {100, 500, 1000, 5000, 10000})
    @TM4J("PERF-T7")
    @Microservice("Memory")
    @Story("Memory usage monitoring")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T7")})
    public void shouldMaintainReasonableMemoryUsage(int operationCount) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Memory Test: " + operationCount + " operations");
        steps.shouldSeeIssueWithTitle("Memory Test: " + operationCount + " operations");
    }

    @Test
    @TM4J("PERF-T8")
    @Microservice("Concurrency")
    @Story("Concurrent user performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T8")})
    @DisplayName("System should handle concurrent users")
    public void shouldHandleConcurrentUsers() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Concurrency Test");
        steps.shouldSeeIssueWithTitle("Concurrency Test");
    }

    @ParameterizedTest(name = "Test cache performance for: {0}")
    @ValueSource(strings = {"Database", "API", "Static Content", "Images", "CSS", "JavaScript"})
    @TM4J("PERF-T9")
    @Microservice("Caching")
    @Story("Cache performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T9")})
    public void shouldCacheContentEfficiently(String cacheType) {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Cache Test: " + cacheType);
        steps.shouldSeeIssueWithTitle("Cache Test: " + cacheType);
    }

    @Test
    @TM4J("PERF-T10")
    @Microservice("Load Balancing")
    @Story("Load balancer performance")
    @Tags({@Tag("web"), @Tag("performance"), @Tag("regression")})
    @JiraIssues({@JiraIssue("PERF-T10")})
    @DisplayName("Load balancer should distribute traffic evenly")
    public void shouldDistributeTrafficEvenly() {
        steps.openIssuesPage("testuser", "testrepo");
        steps.createIssueWithTitle("Load Balancer Test");
        steps.shouldSeeIssueWithTitle("Load Balancer Test");
    }

    @AfterEach
    public void stopDriver() {
        steps.stopDriver();
    }
}
