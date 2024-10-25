package com.dt042g.laboration_2;
import java.util.Scanner;

/**
 * Input class, recieves the input entered, and by using scanner it recieves the inut by the user.
 * @author Martin Roos Eriksson
 */
public class Input {

    /**
     * getInput passes the input to the provided method by using scanner.
     * @return input by the user, meaning the equation.
     */
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an equation to be calculated.");
        return scanner.nextLine();
    }
}
