@echo off
REM ---------------------------------------------------------------
REM  build-run.bat  —  Compile and run the RPG Character System
REM  Requirements: JDK 17+  (java / javac must be on PATH)
REM  Usage: double-click or run from CMD / PowerShell inside Composite\
REM ---------------------------------------------------------------

echo [1/2] Compiling...

if not exist out mkdir out

javac -d out -sourcepath src -encoding UTF-8 ^
    src\Main.java ^
    src\prototype\Prototype.java ^
    src\model\BaseStats.java ^
    src\model\Character.java ^
    src\component\CharacterComponent.java ^
    src\component\CompositeComponent.java ^
    src\component\Equipment.java ^
    src\component\EquipmentSet.java ^
    src\component\Skill.java ^
    src\builder\CharacterBuilder.java ^
    src\builder\RPGCharacterBuilder.java ^
    src\builder\CharacterDirector.java ^
    src\io\Input.java ^
    src\io\Output.java ^
    src\io\ConsoleScanner.java ^
    src\io\ConsoleInput.java ^
    src\io\ConsoleOutput.java ^
    src\io\GUIInput.java ^
    src\io\GUIOutput.java ^
    src\io\WebInput.java ^
    src\io\WebOutput.java ^
    src\adapter\AdapterConsoleInput.java ^
    src\adapter\AdapterConsoleOutput.java ^
    src\adapter\AdapterGUIInput.java ^
    src\adapter\AdapterGUIOutput.java ^
    src\adapter\AdapterWebInput.java ^
    src\adapter\AdapterWebOutput.java ^
    src\factory\InterfaceFactory.java ^
    src\factory\ConsoleFactory.java ^
    src\factory\GUIFactory.java ^
    src\factory\WebFactory.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation FAILED. Make sure JDK 17+ is installed and on PATH.
    pause
    exit /b 1
)

echo [2/2] Running...
echo.
java -cp out Main

pause
