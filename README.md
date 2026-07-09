# API Test Automation Framework

API test automation framework for the [Restful Booker API](https://restful-booker.herokuapp.com/apidoc/index.html).
Built with **REST Assured + Cucumber (BDD) + JUnit 5 Platform + Gradle**.

## Stack

| Category              | Tool                                     |
| --------------------- | ---------------------------------------- |
| HTTP/assertions       | REST Assured 6.0.0                       |
| BDD specification     | Cucumber 7.20.1 (Gherkin)                |
| Test runner / enginer | JUnit 5 Platform Suite + Cucumber engine |
| Build / dependencies  | Gradle (Java 26 toolchain)               |
| Serialization         | Jackson Databind                         |
| DI between steps      | cucumber-picocontainer                   |
| Reporting             | Allure 2.29.0 (allure-cucumber7-jvm)     |

## Running

```bash
# All tests
./gradlew clean test

# By tag
./gradlew clean test -Dcucumber.filter.tags="@smoke"
./gradlew clean test -Dcucumber.filter.tags="@booking and not @delete"

# Override environment / base URI at runtime
./gradlew clean test -Dbase.uri=https://restful-booker.herokuapp.com
```

## Allure

Allure results are written to `build/allure-results` on every test run.
Each HTTP request/response is attached automatically via the `AllureRestAssured` filter.

```bash
# 1. Run the tests (generates raw results in build/allure-results)
./gradlew clean test

# 2a. Generate a static HTML report into build/reports/allure-report
./gradlew allureReport

# 2b. Or build + open the report in a browser in one step
./gradlew allureServe
```

`allureReport` and `allureServe` are provided by the `io.qameta.allure` Gradle
plugin -- no separate Allure CLI install is required for these tasks. To use the
standalone CLI (`allure serve build/allure-results`) install it separately (e.g.
`brew install allure`)

Tags map into Allure automatically: Cucumber `@smoke`, `@booking`, etc. appear
as report labels, so `-Dcucumber.filter.tags` selections are reflected in the
generated report.
