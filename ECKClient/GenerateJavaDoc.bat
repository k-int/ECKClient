@echo off
set JAVA_HOME=E:\Tools\Java\jdk1.7.0_11
set MAVEN_HOME=E:\Tools\Maven\3.0.4
set Path=%Path%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin

call mvn javadoc:javadoc

echo Javadoc generated in target\site\apidocs

pause 
