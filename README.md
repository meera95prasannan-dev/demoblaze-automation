Demoblaze E-Commerce Automation Testing

Project Overview

This project automates functional test scenarios for the Demoblaze e-commerce application using Selenium WebDriver, Java, TestNG, Maven, and the Page Object Model (POM) design pattern.

The objective is to validate key user journeys, including registration, login, product browsing, cart operations, and order placement.

Tech Stack

Programming Language: Java

Automation Tool: Selenium WebDriver

Test Framework: TestNG

Build Tool: Maven

Design Pattern: Page Object Model (POM)

IDE: Eclipse

Version Control: Git and GitHub

Project Structure

src
├── main/java/pages
│   ├── CheckoutPage.java
│   ├── HomePage.java
│   ├── LoginPage.java
│   ├── ProductPage.java
│   └── RegistrationPage.java
└── test/java
    ├── tests
    └── utils
        └── BaseTest.java

pom.xml
README.md
.gitignore

Automated Test Scenarios

Home page loading and validation

User registration

Login with valid and invalid credentials

Logout functionality

Product category selection

Product details validation

Add product to cart

Remove product from cart

Place an order and verify confirmation

Prerequisites

Java JDK

Eclipse IDE (or another Java IDE)

Maven

Google Chrome browser

How to Run the Tests

Clone this repository:

git clone https://github.com/meera95prasannan-dev/demoblaze-automation.git

Import the project into Eclipse as a Maven project.

Ensure the required dependencies are configured in pom.xml.

Run the required TestNG test classes from Eclipse, or execute the suite using Maven if the project is configured for it.

Author

Meera Prasannan
