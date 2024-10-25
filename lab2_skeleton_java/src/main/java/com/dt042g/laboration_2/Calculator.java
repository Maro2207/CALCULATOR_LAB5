package com.dt042g.laboration_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Calculator
 * This class is what handles the calculation as well observing the equation and passes them to the correct method of calculations.
 */
public class Calculator {

    /**
     * calculateInput takes in the input equation, received from the Input class.
     * it replaces all spaces, and looks after parentheses and passes to the parantheses method.
     * after the parenthesis has been solved (or if none exists) the remade equation is passed to Calculate.
     * @param equation the Equation provided from input
     * @return remade equation to be calculated.
     */
    public double calculateInput(String equation) {
        equation = equation.replaceAll(" ", "");
        for (char c : equation.toCharArray()) {
            if (!Character.isDigit(c) && c != '(' && c != ')' && c != '+' && c != '-' && c != '*' && c != '/' && c != '^') {
                throw new IllegalArgumentException("Invalid input. Equation contains non-numeric characters.");
            }
        }

        while (equation.contains("(")) {
            int start = equation.lastIndexOf("(");
            int end = equation.indexOf(")", start);

            if (start != -1 && end != -1) {
                String groupExpression = equation.substring(start + 1, end);
                double groupResult = paranthesis(groupExpression);

                // Replace the entire expression within parentheses with the calculated result
                equation = equation.substring(0, start) + groupResult + equation.substring(end + 1);
            } else {
                break;
            }
        }

        return Calculate(equation);
    }

    /**
     * The Calculate method recieves the equation from CalculateInput
     * In this case the original equation has been remade with their parenthesis already solved, and with spaces excluded.
     * The equation is calculated by using the extractTokens method and passing the individual expressions within the equation to process method.
     * @param equation the formatted equation
     * @return result.
     */
    private double Calculate(String equation) {
        // First, handle multiplication, division, and exponentiation
        List<String> tokens = Calculator.extractTokens(equation);

        while (tokens.contains("^")){
            tokens = process(tokens, "^");
        }
        while (tokens.contains("/") || tokens.contains("*") ){
            tokens = process(tokens, "/*");
        }
        while (tokens.contains("-") || tokens.contains("+")){
            tokens = process(tokens, "-+");
        }


        double result = Double.parseDouble(tokens.get(0));

        return result;

    }

    /**
     * process receives the expressions inside the equation, and applies to specified operation
     * @param tokens the sign for the operation (ex, "^", "*", "/")
     * @param twoTypes the entire string specifying for which numbers the operation should be applied (Ex: 2 + 2)
     * @return the new tokens containing each expression, so instead of the token "*" it will recieve "number * number"
     */
    private List<String> process(List<String> tokens, String twoTypes) {
        List<String> newTokens = new ArrayList<>(tokens);
        double i1 = newTokens.indexOf(twoTypes.substring(0, 1));
        double i2 = newTokens.indexOf(twoTypes.substring(1));
        if (i1 == -1)
            i1 = Double.MAX_VALUE;
        if (i2 == -1)
            i2 = Double.MAX_VALUE;
        double index = Math.min(i1, i2);
        String type = newTokens.get((int)index);
        String aStr = newTokens.get((int)index - 1);
        String bStr = newTokens.get((int)index + 1);

        // Replace tokens with null
        newTokens.set((int)index, null);
        newTokens.set((int)index + 1, null);

        double result = 0;

        if ("^".equals(type)) {
            result = exponent(Double.parseDouble(aStr), Double.parseDouble(bStr));
        } else if ("*".equals(type)) {
            result = multiply(Double.parseDouble(aStr), Double.parseDouble(bStr));
        } else if ("/".equals(type)) {
            result = division(Double.parseDouble(aStr), Double.parseDouble(bStr));
        } else if ("+".equals(type)) {
            result = addition(Double.parseDouble(aStr), Double.parseDouble(bStr));
        } else if ("-".equals(type)) {
            // If aStr is empty or null, replace it with "0"
            String a = (aStr != null && !aStr.isEmpty()) ? aStr : "0";
            result = subtract(Double.parseDouble(a), Double.parseDouble(bStr));
        }

        // Set the previous token to the result
        newTokens.set((int)index - 1, Double.toString(result));

        // Filter out null tokens
        newTokens = newTokens.stream().filter(Objects::nonNull).toList();

        return newTokens;
    }

    /**
     * Addition handles the addition of numbers, by receiving the expressions and integers
     * @param nmb is the first number
     * @param nmb2 is the second number which should be added.
     * @return result of nmb + nmb 2
     */
    private double addition(double nmb, double nmb2) {
        return nmb + nmb2;
    }
    /**
     * division handles the division of numbers, by receiving the expressions and integers
     * @param nmb is the first number
     * @param nmb2 is the second number which should be divided by.
     * @return result of nmb / nmb 2
     */
    private double division(double nmb, double nmb2) {
        if(nmb2 == 0){
            System.err.println("Cannot divide by zero");
            return 0;
        }
        if(nmb == 0){
            System.err.println("Cannot divide by zero");
            return 0;
        }
        return nmb / nmb2;
    }
    /**
     * multiply handles the multiplication of numbers, by receiving the expressions and integers
     * @param nmb is the first number
     * @param nmb2 is the second number which should be multiplied by.
     * @return result of nmb * nmb 2
     */
    private double multiply(double nmb, double nmb2) {
        return nmb * nmb2;
    }

    /**
     * subtract handles the subtraction of numbers, by receiving the expressions and integers
     * @param nmb is the first number
     * @param nmb2 is the second number which should be subtracted by.
     * @return result of nmb - nmb 2
     */
    private double subtract(double nmb, double nmb2) {
        return nmb - nmb2;
    }

    /**
     * exponent handles the exponent of the expression by taking in the base and exponent.
     * @param base number which should be affected by their exponent.
     * @param exp exponent.
     * @return total amount of base^exponent
     */
    private double exponent(double base, double exp) {
        return Math.pow(base, exp);
    }

    /**
     * Parenthesis uses the equation within the paranthesis, passed down by CalculateInput
     * depending on what is inside the paranthesis the method checks if it contains a sign for multiplication, addition etc.
     * and passes them to their corresponding method to be solved.
     * @param paranthesisEquation the expressions within the paranthesis.
     * @return calculated equation, passed back to calculate input.
     */
    private double paranthesis(String paranthesisEquation) {
        paranthesisEquation = paranthesisEquation.replaceAll("\\(", "").replaceAll("\\)", "");

        if (paranthesisEquation.contains("*") || paranthesisEquation.contains("/")) {
            String[] operands;

            if (paranthesisEquation.contains("*")) {
                operands = paranthesisEquation.split("\\*", 2);
                double nmb = Integer.parseInt(operands[0].trim());
                double nmb2 = paranthesis(operands[1].trim()); // Recursively calculate multiplication inside parentheses
                return multiply(nmb, nmb2);
            }

            if (paranthesisEquation.contains("/")) {
                operands = paranthesisEquation.split("/", 2);
                double nmb = paranthesis(operands[0].trim());
                double nmb2 = paranthesis(operands[1].trim());
                return division(nmb, nmb2);
            }
        }

        if (paranthesisEquation.contains("^")) {
            String[] operands = paranthesisEquation.split("\\^", 2);
            double nmb = Integer.parseInt(operands[0].trim());
            double nmb2 = paranthesis(operands[1].trim()); // Recursively calculate the exponent inside parentheses
            return exponent(nmb, nmb2);
        }

        if (paranthesisEquation.contains("+") || paranthesisEquation.contains("-")) {
            String[] operands;

            if (paranthesisEquation.contains("+")) {
                operands = paranthesisEquation.split("\\+", 2);
                double nmb = paranthesis(operands[0].trim());
                double nmb2 = paranthesis(operands[1].trim());
                return addition(nmb, nmb2);
            }

            if (paranthesisEquation.contains("-")) {
                operands = paranthesisEquation.split("-", 2);
                double nmb = paranthesis(operands[0].trim());
                double nmb2 = paranthesis(operands[1].trim());
                return subtract(nmb, nmb2);
            }
        }

        try {
            return Double.parseDouble(paranthesisEquation);
        } catch (NumberFormatException e) {
            System.err.println("Invalid expression inside parentheses: " + paranthesisEquation);
            return 0;
        }
    }

    /**
     * ExtractTokens is what checks if the expression contains a sign so the equation can be seperated to individual expressions within the whole equation
     * @param eq the equation.
     * @return tokens, in this case the signs within the expressions
     */
    public static List<String> extractTokens(String eq){
        String result = "";
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < eq.length(); i++) {
            char c = eq.charAt(i);
            if(c == '^' || c == '+' || c == '-' || c == '*' || c == '/'){
                tokens.add(result);
                result = "";
                tokens.add(c + "");
            }
            else{
            result += c;

            }

        }
        tokens.add(result);
        return tokens;
    }
}