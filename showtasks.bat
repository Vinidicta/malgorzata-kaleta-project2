call runcrud.bat
if %ERRORLEVEL% == "0" goto display
echo.
echo CRUD RUN has errors - breaking working
goto fail

:display
call start "C:\Program Files (x86)\Google\Chrome\Application" http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors
goto end

:end
echo.
echo Work is finished