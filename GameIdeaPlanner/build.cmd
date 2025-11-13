@echo off
cd /d "%~dp0"
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.12.7-hotspot
gradlew.bat build
