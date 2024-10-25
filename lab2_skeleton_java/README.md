## Laboration 2

### Environment & Tools
Lenovo IdeaPad Windows 10 64x. IntelliJ IDEA 2022.3.3 Java 17.0.6 Git 2.37.3

### Purpose
The purpose of this laboration is to implement a calculator program in Java that can handle expressions and equations.
these equations should be able to c ontain parantheses, exponents, as well as the basic operations like multiplication, division, addition and subtraction.

Additionally, the purpose is to conduct testing, creating test cases that can be applied to the program, observing how a desired result can be reached.
There is also the case of the expressions' subfolder, which should be able to be read from the Json file and calculated as the form of a test.

* Create a variety tests for different equations, ensuring that the calculator follows PEMDAS as well as yielding the correct result for all different types of calculations.
* Ensure that the calculations are handled correctly by producing step - by step results.
* Ensure that expressions within the json file can be read, parsed and calculated correctly.

### Procedures
Starting out the program needed to accept inputs as well implement the most basic calculator form.
#### Input
The first step was simply creating the "input" class, which uses scanner to receive the argument for the equation to be calculated.
utilizing scanner, the program can receive the input and pass it to the calculator class, through the main class "Lab2".
The Lab2 class simply passes this result from the scanner to the calculator class, and finally produce the result of the calculation.

#### Calculator
The Calculator class is the main component of this program, responsible for handling the calculation.

the calculateInput method receives the provided equation and starts the calculation process.
first removing all spaces in the equation, then solving each of the present parenthesis equations inside the equation if they exists.
the equation is then modified so the original equation of 9 * (5*9) would instead be 9 * 45, this is passed down to the Calculate method.
The Calculate method receives the formatted equation, and performs the calculation by extracting tokens to separate it to individual expressions.
These individual expressions are then handled by process to ensure they follow the order of PEMDAS and calculate each operation provided for the expression.
Yielding the result of the calculation.
The Process method receives a list of tokens, which represents the expression and their related operation, iterating over the expressions and ensuring it follows PEMDAS for the calculation.
each of the expressions and their operation is passed down to their corresponding operation like addition, multiplication, exponent etc.

Operations such as addition, division, multiply, subtract, exponent, and parenthesis all handle their operation.
but in case of parenthesis, it passes the equation inside parenthesis to the other related operation classes.
The extractTokens method will receive the formatted equation and extract the individual tokens in this case their operands, which is later handled by process.
During the creation of the calculator, the tests were created in Lab2Test, these tests were built to see if the calculator could replicate the equation and yield the correct result.
when all test yielded the right result the final thing to implement was loading the "Lab2_expressions" as a submodule through git, and ensure all expressions could be calculated.

### Discussion
The calculator works as intended and can yield the right results based on inputs, pre-specified arguments or through the tests.
Each of the submitted tests yield the correct and desired output.

### Purpose Fulfillment
The goal "Create tests for different equations" has been met.
To meet these goals Tests were created with a variety of different both simple and complex equations, ensuring that the calculator follows PEMDAS, as well yielding the correct result.
this meets the goal of different equations, and the goal is completed.

The goal "Ensure that the calculations are handled correctly by producing step - by step results." has been met.
The calculations are handled correctly, and the terminal yields outputs step by step, ensuring that the calculations are handled in the correct order, yielding the right result
This meets the goal, clearly showing the user that it follows the correct order and displays step by step how to calculate the expressions within.

The goal "Ensure that expressions within the json file can be read and calculated correctly."
The expressions are read and can be calculated correctly, each of them yielding their desired specified result.
this meets the goal, ensuring each specified expression is  calculated correctly and the tests are all passed.

### Alternative Approaches
This calculator works as intended, and can receive inputs both as arguments through the JAR file as well input in case no arguments exist.
The first method used was not by extracting tokens, instead it simply seperated the string, but in this case a lot of issues occurred with testing, thus the token method was instead used.
However, one could also separate the strings, but that caused more issues, especially with the exponents.

In this case since each token is separated and specified to be calculated in order of PEMDAS ensuring that the final result is correct.

### Personal Reflections
I have not used "tokens" as much as this before, so that was new.
The original idea consisted of separating the strings, but that caused a lot of issues, and resulted in the wrong results, or improper parsing.
Using tokens as the main pointer, accessing the individual expressions and their related operations, ensured that the calculator could calculate the expressions in the correct order, and yield the right result.

Whilst I am not too well versed with testing, it is a useful tool to ensure that the program works as intended, and it also can make debugging easier.