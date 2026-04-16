@echo off
javac -cp "lib\gson-2.13.2.jar" -sourcepath src -d out src\com\toko\*.java
if %ERRORLEVEL% GEQ 1 exit /b %ERRORLEVEL%
java -cp "out;lib\gson-2.13.2.jar" com.toko.Main
