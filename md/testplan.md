For this project you will be testing your methods that are designed to return a value.

You will do this in three steps:

Step 1: Test Plan
For this part of the project, you will in your groups generate a thorough test plan using the UML Class Diagram Test Plan template. Because this test plan will serve as the basis for your test scripts in the next step, your test plan will need to be thorough. 

Step 2: Test Script
A test script is a set of steps used to execute a test plan on a piece of software. Your test script will be automated, meaning that it will be run by executing a Java program that takes no input from the user. This is probably best done by creating a method that executes your test script:

public static void runTests()
Your tester class will test any methods that return a value that can be tested using all the test cases from your test plan. Because you will have a limited time actually to perform the testing, it will help you to make the output very easy to read. A two column format is often used:

Test name
Expected Result
Actual Result
Test                     Expected            Actual

----                     --------            ------

Multi #1         True                True

Multi #2         True                False

With a two column format it is easy to see whether the test worked the way it should have.

How you create the actual tests are up to you, but here is a sample file testing a method that multiples 2 numbers. However, I suggest you test both a success and failure test case for each of your methods. In the Tester class I call the method and check if the return value is what I expect using an IF statement.

myMath.java

Tester.java

Step 3: Test

Test your code using your test scripts. You need to design a way for me to modify your test case data and test your code.
