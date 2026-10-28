@echo off
setlocal
set "SCRIPT_DIR=%~dp0"
echo ====================================================
echo  PRODIGY_ST_04: Cross-Browser Testing with Selenium
echo  Target: SauceDemo (Chrome, Edge, Firefox, Safari)
echo ====================================================
echo.
echo Running local cross-browser test suite (Chrome + Edge)...
call "%SCRIPT_DIR%..\mvn.cmd" -f "%SCRIPT_DIR%pom.xml" clean test -DsuiteXmlFile=src/test/resources/testng-cross-browser.xml
echo.
echo Test Execution Complete!
echo Report available at: %SCRIPT_DIR%target\surefire-reports\index.html
pause
