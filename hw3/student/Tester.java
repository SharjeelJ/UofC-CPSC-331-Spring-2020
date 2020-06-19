package hw3.student;

import java.text.ParseException;

public class Tester
{
    public static void main(String args[])
    {
        try
        {
            ExpressionTree expressionTree = new ExpressionTree();
            String inputString;

            inputString = "1 + 2";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

            inputString = "1 + 2 - 3";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

            inputString = "2 * ( 3 + 6 / 2 ) / 4";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

            inputString = "-1 * ( 2 + 2 * ( 7 + 2 * ( 11 * 2 - 4 ) ) ) / ( 1 + ( 2 - 3 ) * 2 )";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

            inputString = "2 * 3 / ( 2 - 1 ) + 5 * ( 4 - 1 )";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

            inputString = "9 - 5 * 2";
            expressionTree.parse(inputString);
            expressionTree.simplify(1);
            System.out.println(inputString + " = " + expressionTree.evaluate());

        } catch (ParseException e)
        {
            System.out.println("Error Occurred!");
            e.printStackTrace();
        }
    }
}
