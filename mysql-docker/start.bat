@echo off

ECHO Run docker container mysql_dev...
docker run --name mysql_dev --rm -p3306:3306 -d mysql_dev

ECHO.
ECHO Waiting for server up and running...
:loop
timeout /t 1 >nul
docker inspect -f '{{.State.Health.Status}}' mysql_dev | find "healthy" >nul
if errorlevel 1 goto :loop