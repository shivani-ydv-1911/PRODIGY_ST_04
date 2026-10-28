# 📋 Task 04 - Complete Guide & Step-by-Step Instructions

> **Yeh file special aapki friend ke liye banayi gayi hai**, taaki unhe Task-04 (Cross-Browser Testing with BrowserStack), test execution, report findings aur submission process aasaani se samajh aa sake! 🎯

---

## 📌 Table of Contents (Index)
1. [Task-04 Overview & Requirements](#1-task-04-overview--requirements)
2. [Prodigy InfoTech Guidelines for Task-04](#2-prodigy-infotech-guidelines-for-task-04)
3. [Architecture & Frameworks Used](#3-architecture--frameworks-used)
4. [How to Run the Tests (Step-by-Step)](#4-how-to-run-the-tests-step-by-step)
   - [Method 1: Local Cross-Browser Execution (1-Click)](#method-1-local-cross-browser-execution-1-click)
   - [Method 2: BrowserStack Cloud Selenium Grid](#method-2-browserstack-cloud-selenium-grid)
5. [Cross-Browser Issues & Inconsistencies Documentation](#5-cross-browser-issues--inconsistencies-documentation)
6. [GitHub Repository Setup & Push Guide (`PRODIGY_ST_04`)](#6-github-repository-setup--push-guide)
7. [LinkedIn Post Template (Mandatory Step)](#7-linkedin-post-template-mandatory-step)
8. [Submission Checklist](#8-submission-checklist)

---

## 1. Task-04 Overview & Requirements

- **Task Name:** Cross-Browser Testing with BrowserStack
- **Target Application:** [SauceDemo (Swag Labs)](https://www.saucedemo.com/)
- **Core Technology:** **Java 17, Selenium WebDriver (RemoteWebDriver), TestNG, BrowserStack Grid, Maven**
- **Mandatory Requirements:**
  1. Automated test script run karna across multiple browsers: **Chrome, Firefox, Safari, and Edge**.
  2. Mobile browser testing (e.g. Safari on iPhone 15).
  3. BrowserStack Selenium Grid integration.
  4. Cross-browser inconsistencies, UI issues, ya rendering differences document karna.

---

## 2. Prodigy InfoTech Guidelines for Task-04

| Rule | Details |
| :--- | :--- |
| **GitHub Repo Name** | Must follow naming convention: `PRODIGY_TrackCode_TaskNumber`<br>👉 Task 4 ke liye: **`PRODIGY_ST_04`** |
| **Visibility** | Repository **Public** honi chahiye. |
| **LinkedIn Post** | Task complete hone ke baad LinkedIn par post daalna **MANDATORY** hai. |
| **Documentation** | Cross-browser compatibility report add karna compulsory hai (`CROSS_BROWSER_REPORT.md`). |

---

## 3. Architecture & Frameworks Used

```
task_04_cross_browser/
├── pom.xml                                   # Maven configuration (Selenium 4.27, TestNG 7.10)
├── src/
│   ├── main/java/com/prodigy/task4/pages/
│   │   └── SauceDemoLoginPage.java           # POM: Page Object Model for SauceDemo
│   └── test/
│       ├── java/com/prodigy/task4/
│       │   ├── base/
│       │   │   └── BaseCrossBrowserTest.java # Driver setup (Local + BrowserStack Grid)
│       │   └── tests/
│       │       └── CrossBrowserLoginTest.java# Test suite executed across all browsers
│       └── resources/
│           ├── testng-cross-browser.xml      # Local multi-browser runner (Chrome + Edge)
│           ├── testng-browserstack.xml       # Cloud runner (Chrome, Firefox, Safari, Edge, iPhone)
│           └── browserstack.yml              # Official BrowserStack YAML config
├── run_task4.bat                             # 1-Click execution script for Windows
├── CROSS_BROWSER_REPORT.md                   # Full browser compatibility & inconsistency report
├── INSTRUCTIONS_TASK4.md                     # Hindi + English guide for your friend
└── README.md                                 # GitHub repository documentation
```

---

## 4. How to Run the Tests (Step-by-Step)

### Method 1: Local Cross-Browser Execution (Sabse Aasan)
Aapki friend bina kisi paid tool ya BrowserStack account ke turant test run kar sakti hain:
1. Project folder me jayein: `task_04_cross_browser`
2. **`run_task4.bat`** file par double click karein.
3. Ya VS Code terminal me yeh command run karein:
   ```bash
   ..\mvn.cmd clean test -DsuiteXmlFile=src/test/resources/testng-cross-browser.xml
   ```
*Yeh Chrome aur Edge dono browsers par parallel testing karega aur detailed TestNG report generate karega.*

---

### Method 2: BrowserStack Cloud Selenium Grid
Agar aapki friend ke paas BrowserStack ka free trial ya university/company account hai:
1. [BrowserStack](https://www.browserstack.com/) par account banayein ya login karein.
2. Dashboard se apna **Username** aur **Access Key** copy karein.
3. Terminal me environment variables set karein:
   ```cmd
   set BROWSERSTACK_USERNAME=your_username_here
   set BROWSERSTACK_ACCESS_KEY=your_access_key_here
   ```
4. Cloud Suite run karein:
   ```bash
   ..\mvn.cmd clean test -DsuiteXmlFile=src/test/resources/testng-browserstack.xml
   ```
*Yeh BrowserStack ke real cloud machines (Windows 11 Chrome/Firefox/Edge, macOS Safari, aur iPhone 15) par automated test execute karega aur video recording dashboard par dikhayega!*

---

## 5. Cross-Browser Issues & Inconsistencies Documentation

Humne detail me **[CROSS_BROWSER_REPORT.md](file:///c:/Projects/personal/her_testing/task_04_cross_browser/CROSS_BROWSER_REPORT.md)** banayi hai jisme:
- **Safari CSS Animation:** Safari me sidebar menu slide animation me halka sa transition delay hota hai, jise explicit wait aur JavaScript executor click se solve kiya gaya hai.
- **Font Rendering in Firefox:** Firefox Gecko engine me bold fonts Chrome ke muqable thode zyada thick render hote hain.
- **Mobile Responsive Design (iPhone 15):** Mobile screens par login inputs aur credentials box automatically vertically stack hote hain jo ek healthy responsive layout hai.

---

## 6. GitHub Repository Setup & Push Guide (`PRODIGY_ST_04`)

Task 4 ke liye new public repository banani hai:

1. [GitHub](https://github.com/) par jayein aur **New Repository** par click karein.
2. Repository Name daalein: **`PRODIGY_ST_04`**
3. Visibility: **Public**
4. `task_04_cross_browser` folder ke andar terminal kholein aur yeh commands chalayein:

```bash
git init
git add .
git commit -m "feat: completed task 4 cross browser testing with browserstack and selenium grid"
git branch -M main
git remote add origin https://github.com/<YOUR_GITHUB_USERNAME>/PRODIGY_ST_04.git
git push -u origin main
```
*(Note: `<YOUR_GITHUB_USERNAME>` ko apne actual GitHub username se replace karein).*

---

## 7. LinkedIn Post Template (Mandatory Step)

Direct yeh template copy-paste kar sakti hain:

```markdown
🚀 Excited to announce that I have successfully completed Task-04 of my Software Testing Internship at Prodigy InfoTech!

📌 Task Title: Cross-Browser Testing with BrowserStack & Selenium Grid
🌐 Target Web App: SauceDemo (https://www.saucedemo.com/)
🛠️ Tech Stack: Java 17, Selenium WebDriver, BrowserStack Selenium Grid, TestNG, Maven, Page Object Model (POM)

Key Highlights of this Project:
✅ Configured and executed automated cross-browser test suites across multiple desktop and mobile platforms:
   - Google Chrome (Windows 11)
   - Mozilla Firefox (Windows 11)
   - Microsoft Edge (Windows 11)
   - Apple Safari (macOS Sonoma)
   - Mobile Safari (Apple iPhone 15 / iOS 17)
✅ Leveraged BrowserStack's RemoteWebDriver Selenium Grid to run cloud-based parallel executions.
✅ Evaluated functional authentication flows, validation error handling, and session state across rendering engines (Blink, Gecko, WebKit).
✅ Documented cross-browser inconsistencies, CSS animation timing differences, and responsive UI behaviors in a comprehensive compatibility report.

📂 GitHub Repository: https://github.com/<YOUR_GITHUB_USERNAME>/PRODIGY_ST_04

A huge thank you to Prodigy InfoTech for this enriching internship experience in automated software testing and cross-browser quality assurance!

#ProdigyInfoTech #SoftwareTesting #BrowserStack #Selenium #CrossBrowserTesting #TestNG #Java #AutomationTesting #QualityAssurance #Internship
```

---

## 8. Submission Checklist

- [x] Repository ka naam exact **`PRODIGY_ST_04`** hai.
- [x] Repository Public hai.
- [x] `README.md` aur `CROSS_BROWSER_REPORT.md` files maujood hain.
- [x] Tests successfully pass ho rahe hain.
- [x] LinkedIn post live hai aur link copy kar liya hai.

---
**Task-04 complete! Dono tasks (Task-03 & Task-04) ready hain! 🚀**
