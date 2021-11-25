@echo on

set ROOT_PROJET=E:\WorkSpaces\WS\GitHub\BricolageV2\server
rem set OPTS=--info
gradle build %OPTS% -Dgradle.user.home=%ROOT_PROJET%\.gradle_user_home --build-cache