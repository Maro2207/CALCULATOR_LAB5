package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for Lab2 and the calculator.
 * This class tests the calculator with different expressions and compares the result with the expected value.
 * @author Martin Roos Eriksson
 */
public class Lab2Test {

    /**
     * Test the calculator with simple addition.
     * The result should be 8.
     */
    @Test
    public void testSimpleAddition() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("5 + 3");
        assertEquals(8, result);
        System.out.println("Test Simple Addition: " + result);
    }

    /**
     * Test the calculator with simple subtraction.
     * The result should be 2.
     */
    @Test
    public void testSimpleMultiplication() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("4 * 6");
        assertEquals(24, result);
        System.out.println("Test Simple Multiplication: " + result);
    }

    /**
     * Test the calculator with simple division.
     * The result should be 2.
     */
    @Test
    public void testComplexExpression() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("(3 + 2) * ((4*6) / (2*1))");
        assertEquals(60, result);
        System.out.println("Test Complex Expression: " + result);
    }

    /**
     * test division by zero.
     * The result should be 0, as well give an error message.
     */
    @Test
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("5 / 0");
        calculator.calculateInput("5 / 0");
        assertEquals(0, result);
        assertNotEquals(5, result);
        System.out.println("Test Division By Zero: " + result);
    }

    /**
     * Test the calculator with simple division.
     * The result should be 2.
     */
    @Test
    public void testSimpleDivision() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("10 / 5");
        assertEquals(2, result);
        System.out.println("Test Simple Division: " + result);
    }

    /**
     * Test the calculator with simple subtraction.
     * The result should be 2.
     */
    @Test
    public void testSimpleSubtraction() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("5 - 3");
        assertEquals(2, result);
        System.out.println("Test Simple Subtraction: " + result);
    }

    /**
     * Test the calculator with division by a negative number.
     * The result should be -2.
     */
    @Test
    public void testDivisionByNegativeNumber() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("-10 / 5");
        assertEquals(-2, result);
        assertNotEquals(2, result);
        System.out.println("Test Division By Negative Number: " + result);
    }

    /**
     * Test the calculator with an exponentiation.
     * The result should be 8.
     */
    @Test
    public void testExponentiation() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("2 ^ 3");
        assertEquals(8, result);
        assertNotEquals(9, result);
        System.out.println("Test Exponentiation: " + result);
    }

    /**
     * Test the calculator with an expression containing negative numbers.
     * The result should be -10.
     */
    @Test
    public void testExpressionWithNegativeNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.calculateInput("-5 * 2");
        assertEquals(-10, result);
        assertNotEquals(10, result);
        System.out.println("Test Expression With Negative Numbers: " + result);
    }

    @Test
    public void testInvalidExpressionInsideParentheses() {
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateInput("(24.0 + 2) * ((4*6) / (2*1))");
        });
    }


    @Test
    public void testInputWithNonNumericCharacters() {
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateInput("5 + a");
        });
    }

    /**
     * Test all expressions within the expressions.json file.
     * The result should be the same as the expected value provided.
     */
    @Test
    public void testJsonExpressions() {
        Calculator calculator = new Calculator();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("lab2_expressions/expressions.json"));
            JSONObject jsonObject = (JSONObject) obj;


            for (Map.Entry<String, Long> entry : ((Map<String, Long>) jsonObject).entrySet()) {
                String equation = entry.getKey();
                long expectedValue = entry.getValue();

                long result = (long) calculator.calculateInput(equation);

                assertEquals(expectedValue, result, "Failed for equation: " + equation);
                System.out.println("Test Json Expressions: " + equation + " = " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
