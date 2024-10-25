package com.dt042g.laboration_2;

/**
 * Lab2, the starting point of the program.
 * the main function here is to retrieve the input, passing it to the calculator.
 * @author Martin Roos Eriksson
 */
public class Lab2 {


    /**
     * recieves the input and starts the calculator, finally printing out the result.
     * @param args in this case there are no args here since it is provided by passing result from getInput to calculator,
     * but if there is a provided argument it uses that.
     */
    static public void main(String... args) {
        Calculator calculator = new Calculator();
        String equation;

        if(args.length == 0) {
            System.out.println("Laboration 2");
            equation = Input.getInput();
            System.out.println(equation);

        } else {
            equation = args[0];
        }
        double result = calculator.calculateInput(equation);
            System.out.println(equation + " = " + result);
        }

}
