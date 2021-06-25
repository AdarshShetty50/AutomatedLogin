@echo off
start iexplore.exe "http://tom/utom/underwriting"
timeout /t 30000
taskkill /f /im iexplore.exe
exit
