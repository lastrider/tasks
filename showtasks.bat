
call runcrud
if "%ERRORLEVEL%" == "0" goto open
goto fail

:open
start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors