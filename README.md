# Selenium & Appium Test Automation Project

This is a Java-based test automation project using Selenium WebDriver for web testing and Appium for Android mobile testing.

## Project Structure

```
test/
├── pom.xml                          # Maven build file
├── src/
│   ├── main/
│   │   └── java/src/
│   │       ├── Web.java             # Web test helper methods
│   │       ├── Android.java         # Android test helper methods
│   │       ├── SelectorLoader.java  # Loads selectors from XML
│   │       ├── Helper.java          # Shared helper methods (e.g., Login)
│   │       └── selector.xml         # XML file with web & mobile selectors
│   └── test/
│       └── java/
│           ├── WebTest.java         # Web tests (CURA Healthcare Service)
│           └── AndroidTest.java     # Android tests (Erafone app)
```

## Features

- **Web Testing**: Uses Selenium WebDriver and ChromeDriver to test [CURA Healthcare Service](https://katalon-demo-cura.herokuapp.com/)
- **Android Testing**: Uses Appium with UiAutomator2 to test Erafone Android app
- **Selector Management**: Uses XML to store test selectors (keeps test code clean)
- **Test Framework**: JUnit 5 for test structure and AssertJ for assertions

## Prerequisites

To run this project, you need to have installed:

1. **Java 11 or higher**
2. **Maven 3.6+**
3. **Chrome Browser** (for web tests)
4. **ChromeDriver** matching your Chrome version (or use WebDriverManager)
5. **Appium Server** (for Android tests):
   ```bash
   npm install -g appium
   ```
6. **UiAutomator2 Driver**:
   ```bash
   appium driver install uiautomator2
   ```
7. **Android Studio & Android SDK** (for Android tests)
8. **Physical Android device or emulator** connected via USB debugging

## Dependencies (pom.xml)

- **Selenium Java**: 4.46.0
- **Appium Java Client**: 9.2.3
- **JUnit Jupiter**: 5.10.2
- **AssertJ**: 3.25.3

## How to Run

### Step 1: Clone the project

```bash
cd /path/to/your/workspace
git clone <your-repository-url>
cd test
```

### Step 2: Fix dependency compatibility (Important!)

Currently, the Appium Java Client 9.2.3 is **incompatible** with Selenium 4.46.0. To fix this, you can:
- Downgrade Selenium Java to a version between 4.19.0 - 4.27.0 in pom.xml, or
- Upgrade Appium Java Client to 10.x (which supports newer Selenium versions)

### Step 3: Run tests

#### Run all tests
```bash
mvn clean test
```

#### Run only Web tests
```bash
mvn clean test -Dtest=WebTest
```

#### Run only Android tests (requires Appium server running)
First start the Appium server in one terminal:
```bash
appium
```
Then in another terminal:
```bash
mvn clean test -Dtest=AndroidTest
```

## What the Tests Do

### Web Tests (WebTest.java)
- `testLoginWithInvalidData`: Tests login with wrong credentials
- `testLoginWithValidData`: Tests successful login
- `testMakeAppointmentWithoutInputMandatoryField`: Tests booking without required info
- `testMakeAppointmentWithInputMandatoryField`: Tests successful appointment booking

### Android Tests (AndroidTest.java)
- `loginWithInvalidData`: Tests login with wrong credentials
- `loginWithValidData`: Tests successful login and profile check

## Customization

### Add new selectors
Edit [selector.xml](file:///Users/dfdandys/IdeaProjects/test/src/main/java/src/selector.xml) to add new web or mobile element selectors.

### Modify test data
Edit test files like [WebTest.java](file:///Users/dfdandys/IdeaProjects/test/src/test/java/WebTest.java) or [AndroidTest.java](file:///Users/dfdandys/IdeaProjects/test/src/test/java/AndroidTest.java) to change test inputs.
