@echo off
setlocal enabledelayedexpansion

set "ROOT=%~dp0"
cd /d "%ROOT%"

if not exist "bin\com\example\Launcher.class" (
    echo [RUN] Compiled binaries not found. Triggering build...
    call "%ROOT%build.bat"
    if %ERRORLEVEL% NEQ 0 (
        echo [ERROR] Build failed!
        pause
        exit /b %ERRORLEVEL%
    )
)

set "JAVA_CMD=java"
if defined JAVA_HOME (
    if exist "%JAVA_HOME%\bin\java.exe" set "JAVA_CMD=%JAVA_HOME%\bin\java.exe"
)

where %JAVA_CMD% >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java runtime not found!
    pause
    exit /b 1
)

set "SDK_PATH=%ROOT%lib\javafx-sdk\lib"
set "BIN_DLL_PATH=%ROOT%lib\javafx-sdk\bin"

echo [RUN] Launching JavaFX application...
"%JAVA_CMD%" -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.net.useSystemProxies=false -Dhttp.nonProxyHosts="localhost|127.0.0.1|10.*" -Djava.library.path="%BIN_DLL_PATH%" --enable-native-access=javafx.graphics --module-path "%SDK_PATH%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base -cp "%ROOT%bin" com.example.Launcher

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Application exited with code %ERRORLEVEL%.
    pause
)
