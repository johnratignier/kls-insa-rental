# KLS INSA Rental

Application to manage rental of sound and light equipments for the INSA Lyon association named KLS (K-Le Son).

Account creation for the association members, client account for the association and private.
Management of the equipements and their availabilities.
Generation of a custom rental sheet and receipt.

## Technical choices

As it was my first personal (big) project, the architecture is ghastly, the code is horrible.
It's really hard to maintain the project. You can easily improve it and refactor some files.
Currently the big part of the code is in French.

As regards the technical choices :
  - Backend : JAVA EE (8) with no framework, just pure code with servlet, DAO, beans and so on.
  - Frontend : HTML/CSS/Javascript with Bootstrap and JQuery. No front framework.
  - Database : MySQL
  
As the project is not a RESTful API in one side with a front application is the other one, we have a fuckin' monolyth.
Consequently, front files are in the same project in the .JSP files too much linked to the servlets.
There is no test yet.
Note we use some lib (jar) to add some features as the pdf sheet generator for receipt.

## Installation

### IDE and environment
You can develop in the IDE you want. I started with Eclips but now I prefer use IntelliJ from Jetbrains.

You can also develop on Windows environment but I advise to delop on Linux or MacOS environment.

### Java JDK and JAVA EE
##### JAVA SE, JAVA EE, JDK, JRE... what does it mean ?
Java JRE (for Runtime Environment) provide a cross-platform environment to run / launch / use JAVA applications.
If you want to run a JAVA application whatever your environment, you need the JRE.
The JRE embed JAVA SE which is the code base of JAVA.

JAVA JDK (for Development Kit) provide you the JRE + some things to allow you to develop JAVA applications.
Without the JDK you cannot develop JAVA Applications.

JAVA EE is just a layout over JAVA SE (embedded in the JRE).
It will add some base code to allow you to develop JAVA application for the WEB.

Therefore you need to install the **JDK (8)** + **JAVA EE (8)**.

##### How to install them
- First, create the folder where you will install JAVA JDK and JAVA EE
```
sudo mkdir /opt/java
```
- Go to oracle website and download **jdk-8u...** for your environment and **java_ee_sdk-8u1.zip**.
Move these two folders in `/opt/java`.
```
sudo mv jdk-8u... /opt/java
sudo mv java_ee_sdk-8u1.zip /opt/java
```
- Extract these two folders in `/opt/java`.
That will create two folders : `jdk1.8.0_...` and `glassfish5`.
```
cd /opt/java
sudo tar xzvf jdk-8u...
sudo unzip java_ee_sdk-8u1.zip
```
- Open the file to add you environment variables
```
sudo vim /etc/environment
```
- Add the PATH and the JAVA_HOME variables in the file you opened
```
PATH=".........:/opt/java/jdk1.8.0_.../bin"
JAVA_HOME="/opt/java/jdk1.8.0_..."
```