# 🌐 PRODIGY_ST_04: Cross-Browser Testing with BrowserStack & Selenium Grid

[![Java 17](https://img.shields.io/badge/Java-17-blue?logo=openjdk)](https://www.oracle.com/java/)
[![Selenium WebDriver](https://img.shields.io/badge/Selenium-4.27.0-green?logo=selenium)](https://www.selenium.dev/)
[![BrowserStack](https://img.shields.io/badge/Grid-BrowserStack%20Cloud-orange?logo=browserstack)](https://www.browserstack.com/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)](https://testng.org/)
[![Internship Track](https://img.shields.io/badge/Prodigy%20InfoTech-Software%20Testing-blue)](https://prodigyinfotech.dev/)

This repository contains the completed **Task-04** for the **Software Testing Internship** at **Prodigy InfoTech**.

---

## 📌 Task Overview

- **Task ID:** Task-04
- **Title:** Cross-Browser Testing with BrowserStack
- **Target Application:** [SauceDemo (Swag Labs)](https://www.saucedemo.com/)
- **Core Technology:** **Java 17, Selenium WebDriver, BrowserStack Cloud Grid, TestNG, Maven**
- **Objective:** Run automated test suites across multiple browsers (Chrome, Firefox, Safari, Edge) and mobile devices using BrowserStack Selenium Grid, ensuring functional compatibility, visual stability, and documenting inconsistencies across platforms.

---

## 🏗️ Architecture & Framework

```
PRODIGY_ST_04/
├── pom.xml                                   # Maven dependencies & surefire plugin
├── src/
│   ├── main/java/com/prodigy/task4/pages/
│   │   └── SauceDemoLoginPage.java           # Page Object Model (POM) encapsulation
│   └── test/
│       ├── java/com/prodigy/task4/
│       │   ├── base/
│       │   │   └── BaseCrossBrowserTest.java # Multi-browser & BrowserStack cloud driver factory
│       │   └── tests/
│       │       └── CrossBrowserLoginTest.java# Test scenarios executed across platforms
│       └── resources/
│           ├── testng-cross-browser.xml      # Parallel local runner (Chrome + Edge)
│           ├── testng-browserstack.xml       # Cloud suite (Chrome, Firefox, Safari, Edge, iPhone)
│           └── browserstack.yml              # BrowserStack official configuration
├── run_task4.bat                             # 1-Click execution script for Windows
├── CROSS_BROWSER_REPORT.md                   # Full cross-browser compatibility analysis report
├── INSTRUCTIONS_TASK4.md                     # Step-by-step Hindi + English Intern guide
└── README.md                                 # Project documentation
```

---

## 💻 Supported Browsers & Devices Matrix

| Platform / Device | Operating System | Engine | Test Runner |
| :--- | :--- | :--- | :--- |
| **Google Chrome** | Windows 11 | Blink | Local & BrowserStack Grid |
| **Microsoft Edge** | Windows 11 | Blink | Local & BrowserStack Grid |
| **Mozilla Firefox** | Windows 11 | Gecko | BrowserStack Cloud Grid |
| **Apple Safari** | macOS Sonoma | WebKit | BrowserStack Cloud Grid |
| **Mobile Safari** | iOS 17 (Apple iPhone 15) | WebKit Mobile | BrowserStack Cloud Grid |

---

## 🚀 Execution Guide

### Option 1: Local Cross-Browser Execution (1-Click)
Double-click **`run_task4.bat`** on Windows, or run in terminal:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng-cross-browser.xml
```

### Option 2: BrowserStack Cloud Selenium Grid
Set your BrowserStack credentials and execute the cloud grid suite:
```cmd
set BROWSERSTACK_USERNAME=<YOUR_USERNAME>
set BROWSERSTACK_ACCESS_KEY=<YOUR_ACCESS_KEY>
mvn clean test -DsuiteXmlFile=src/test/resources/testng-browserstack.xml
```

---

## 📑 Cross-Browser Compatibility Report

For a detailed analysis of rendering differences, CSS slide animations, font anti-aliasing, and mobile viewport responsiveness, refer to:
👉 **[CROSS_BROWSER_REPORT.md](CROSS_BROWSER_REPORT.md)**

---

## 📊 Test Execution Summary

```text
===============================================
Cross Browser Local Execution Suite
Total tests run: 8, Passes: 8, Failures: 0, Skips: 0
===============================================
[INFO] BUILD SUCCESS
```

---

## 📄 License
This project is open-source under the MIT License. Created as part of the Prodigy InfoTech Software Testing Internship.
