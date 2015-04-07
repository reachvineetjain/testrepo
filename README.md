# CCIGHGO
Welcome to CCHGHGO
--------------------

This readme file's goal is to provide some quick information on the CCIGHGO project. MySQL will be the database for this application.

Topics are:

1). Development prerequisites.
2). Database.
3). Build process.
4). Project Architecture.

Development Prerequisites
-------------------------

1. It is recommended for all developers to configure their development tools to use spaces instead of tabs for code formatting. We migh use unix based application server and sometimes compilation is a problem due to different line ending and spacing characters in Windows and Unix environments.
2. All developers except database developers required to have jdk 1.8 installed in the machine along with maven 3.0.4 in their machines with proper environment variables set to operating system.
3. Developers can either Eclipse or IntelliJ as their preferred IDE. Eclipse Luna was used to create initial setup. Maven integration in eclipse is required.
4. Apache Tomcat 8.0.20 is suggested for local deployment, the application is independent of any server configuration and deployable in any J2EE container or server. Please cofigure in eclipse or intellij.
5. Developers are required to have MySQL CE(mysql-installer-community-5.6.23.0.msi as of this writing) database and MySQL Workbench CE (mysql-workbench-community-6.2.5-winx64.msi as of this writing) installed in their machine.


Database
--------

CCIGHGO system will use MySQL as database engine. Project directory contains dbscript folder which will contain all database related scripts, er-diagrams and functions.

Build Process
-------------

Devlopment build process will be on local machine over tomcat/jetty/jboss.
Remote build will be done by jenkins using maven plugin.

Project Architecture
--------------------

1. This project utilizes service oriented architecture(SOA). UI/UX will interact with Contoller and business logic over RESTFul services using JSON data as communication medium.
2. This project utilizes Apache CXF as restful interface to provide restful services to UI/UX
3. Spring is used and dependency and aspect injections.
4. Hibernate and Springdata jpa will be used in persistence layer for database interaction, connection pooling, dirty checking, caching mechanism.
5. Mix of Angularjs, reactjs and bootstrap will be used for UI/UX development.




