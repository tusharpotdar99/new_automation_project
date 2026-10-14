# E-Commerce Website Automation Testing Using Selenium

## 📌 Project Overview

This project is an automation testing framework developed to test the functionality of an e-commerce website using **Selenium WebDriver, Java, TestNG, and Maven**.

The project follows the **Page Object Model (POM)** design pattern to maintain reusable, readable, and maintainable automation code. It focuses on validating important e-commerce workflows and improving test efficiency through automated functional testing.

The framework is designed around an OpenCart e-commerce application.

## 🎯 Project Objectives

- Automate important e-commerce application workflows.
- Validate the functionality of key features.
- Identify and report application defects.
- Improve test reusability and maintainability.
- Organize test execution using TestNG.
- Generate test execution reports.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution and test organization |
| Maven | Dependency management and build automation |
| IntelliJ IDEA | Development environment |
| Apache POI | Excel-based test data handling |
| ExtentReports | Test execution reporting |
| Git | Version control |
| GitHub | Source code hosting |

## 🧪 Test Scenarios

The following test scenarios are covered or planned as part of the framework:

- **User Registration:** Validate the registration workflow using test data.
- **User Login:** Verify login functionality using valid credentials.
- **My Account:** Validate account page navigation and displayed information.
- **Product Selection:** Verify product listings and navigate to product details.
- **Add to Wishlist:** Validate the wishlist functionality.
- **Navigation:** Verify navigation between pages and website sections.
- **Test Data Management:** Generate and manage registration test data using Excel.
- **Regression Testing:** Re-execute relevant test cases after code changes.

*Note: The scenarios listed here should be adjusted to match the test cases currently implemented in the repository.*

## 🏗️ Framework Design

The project uses the Page Object Model (POM) design pattern with reusable page and component classes.

Key framework components include:

- **BasePage:** Common functionality shared by page classes.
- **BaseTest:** Common test setup and teardown.
- **Page Classes:** Represent application pages such as Home, Login, and My Account.
- **Reusable Components:** Handle common website sections such as the header, navigation menu, product grid, footer, and account side panel.
- **ElementActions:** Provides reusable element interactions and explicit wait handling.
- **Test Classes:** Contain test scenarios and validation logic.
- **Test Data Generator:** Supports generating and managing registration test data.
- **ExtentReports:** Supports readable test execution reports.

## 📂 Project Structure

```text
new_automation_project/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │       └── components/
│   │       └── utils/
│   │
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   ├── base/
│       │   ├── testdata/
│       │   └── listeners/
│       │
│       └── resources/
│
├── testData/
├── pom.xml
├── .gitignore
└── README.md
```

*The structure above is illustrative. Update the folder names to match the actual project structure.*

## ⚙️ Prerequisites

Before running the automation tests, ensure the following are installed:

- Java JDK
- Apache Maven
- IntelliJ IDEA or another Java IDE
- Google Chrome browser
- Git

The project also requires access to the configured OpenCart test application.

## 🚀 How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/tusharpotdar99/new_automation_project.git
```

### 2. Navigate to the project directory

```bash
cd new_automation_project
```

### 3. Verify Java and Maven

```bash
java -version
mvn -version
```

### 4. Import the project

Open the project in IntelliJ IDEA and allow Maven to download the required dependencies.

### 5. Execute the automation tests

Run the complete TestNG suite configured for Maven:

```bash
mvn clean test
```

Alternatively, execute the required test classes directly from IntelliJ IDEA.

**Note:** Ensure the OpenCart application is running and the test configuration is correct before executing the tests.

## 📊 Reporting

The framework is designed to support test execution reporting through ExtentReports and TestNG.

Reports can help identify:

- Passed test cases
- Failed test cases
- Execution errors
- Test execution details

Report locations depend on the configured reporting implementation.

## 💡 Key Features

- Page Object Model architecture
- Reusable page and component classes
- Selenium WebDriver browser automation
- TestNG-based test execution
- Maven dependency and build management
- Excel-based test data handling
- Explicit wait and element interaction utilities
- Test execution reporting
- Version control using Git and GitHub

## 🔮 Future Enhancements

- Integrate automated test execution with Jenkins or GitHub Actions.
- Add cross-browser testing.
- Expand API testing coverage.
- Implement parallel execution with TestNG.
- Improve failure screenshots and logging.
- Integrate automated test reporting into the CI/CD pipeline.

## 👨‍💻 Author

**Tushar Potdar**

GitHub: [tusharpotdar99](https://github.com/tusharpotdar99)

## 📄 License

This project is intended for learning, practice, and demonstration purposes. Add a specific open-source license if you decide to distribute the project under one.
