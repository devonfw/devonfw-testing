@echo off
set browser=firefox
set tag=login
set env=DEV

echo Configuring browser
set /p newBrowser=Do you want to use "%browser%" browser then press enter or provide new browser:
IF NOT [%newBrowser%]==[] (
set browser=%newBrowser%
)
echo Configuring environment
set /p newEnvironment=Do you want to use "%env%" environment then press enter or provide new environment:
IF NOT [%newEnvironment%]==[] (
set env=%newEnvironment%
)
echo Configuring tags
set /p newTag=Do you want to use "%tag%" tag then press enter or provide new tag:
IF NOT [%newTag%]==[] (
set tag=%newTag%
)
java -cp ${zipfilename}.jar;${zipfilename}-tests.jar -Dbrowser=%browser% -Denv=%env% org.junit.runner.JUnitCore "add here testcases"
xcopy "target\allure-results" "allure-results\" /E /Y
allure generate allure-results --clean -o allure-report
