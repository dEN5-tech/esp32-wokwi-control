@echo off
setlocal enabledelayedexpansion

set "ROOT=%~dp0"
cd /d "%ROOT%"

echo ========================================================
echo   [BUILD] Compiling JavaFX Arduino Servo Client
echo ========================================================
echo.

set "JAVAC_CMD=javac"
if defined JAVA_HOME (
    if exist "%JAVA_HOME%\bin\javac.exe" set "JAVAC_CMD=%JAVA_HOME%\bin\javac.exe"
)

where %JAVAC_CMD% >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compiler javac not found!
    exit /b 1
)

set "SDK_PATH=%ROOT%lib\javafx-sdk\lib"

if not exist "bin" mkdir "bin"
if not exist "bin\com\example" mkdir "bin\com\example"

echo [BUILD] Compiling Java source files...
"%JAVAC_CMD%" -encoding UTF-8 --release 17 --module-path "%SDK_PATH%" --add-modules javafx.controls,javafx.fxml -d "bin" src\com\example\*.java
set "COMPILE_ERR=%ERRORLEVEL%"

if %COMPILE_ERR% NEQ 0 (
    echo [ERROR] Java compilation failed!
    exit /b %COMPILE_ERR%
)

echo [BUILD] Copying FXML and CSS resources to bin...
copy /y "%ROOT%src\com\example\*.fxml" "%ROOT%bin\com\example\" >nul 2>&1
copy /y "%ROOT%src\com\example\*.css" "%ROOT%bin\com\example\" >nul 2>&1

echo [BUILD] Success! Binaries created in bin/.
exit /b 0
