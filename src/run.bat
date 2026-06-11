@echo off
javac *.java
java -cp ".;..\lib\postgresql-42.7.11.jar" Login
pause