import java.util.Scanner;
import java.util.Stack;
/**
 * UserInterface
 * @author Jan Schelhaas, Pascal Polchow, Larissa Wagnerberger
 * @version 2018.06.13
 */

public class Postfix {


    void input() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {

                System.out.println("enter infix expression:");
                String line = sc.nextLine();
                if (line.equals("quit")) {
                    quit = true;
                }
                else {
                    try {
                        System.out.println(infixToPostfix(line));
                        System.out.println(s.size());
                        System.out.println(evaluate2(infixToPostfix(line),false));
                        System.out.println(s.size());
                    } catch (UnknownCharacterException ignored) {
                }
            }
        }
    }

    private Stack<Integer> s = new Stack<>();

    /**
     * method that evaluates a postfix expression
     * @param pfx postfix expression
     * @return result
     */
    public int evaluate(String pfx) {

        pfx = pfx.trim().replaceAll("\\s", "");
        char[] expr = pfx.toCharArray();
        for (char anExpr : expr) {
            if (anExpr >= 48 && anExpr <= 57)
                s.push(Character.getNumericValue(anExpr));
            if (anExpr == '*')
                multiply();
            if (anExpr == '-') {
                subtract();
            }
            if (anExpr == '+')
                add();
            if (anExpr == '/')
                divide();
            if (anExpr == '^')
                power();
        }

        return s.peek();
    }

    /**
     * improved version of the evaluate- method, it now works with multi-digit numbers
     * calculates a result out of a postfix expression
     * @param pfx postfix expression
     * @return result
     */
    public int evaluate2(String pfx, boolean hexmode) throws UnknownCharacterException{
        s.removeAllElements();
        String[] expr = pfx.split("\\s");
        for (String st : expr) {

            char[] ch = st.toCharArray();
            int numberLength = 0;

            for (char aCh : ch) {

                if (aCh == '*') {
                    multiply();
                    numberLength = 0;
                } else if (aCh == '-') {
                    subtract();
                    numberLength = 0;
                } else if (aCh == '+') {
                    add();
                    numberLength = 0;
                } else if (aCh == '/') {
                    divide();
                    numberLength = 0;
                } else if (aCh == '^') {
                    power();
                    numberLength = 0;
                } else if (aCh >= 48 && aCh <= 57) {
                    s.push(Character.getNumericValue(aCh));
                    if (numberLength > 0) {
                        int second = (int) s.peek();
                        s.pop();
                        int first = (int) s.peek();
                        s.pop();
                        if (!hexmode)
                            s.push(first * 10 + second);
                        else s.push(first * 16 + second);
                    }
                    numberLength++;
                } else if (aCh >= 65 && aCh <= 70) {
                    String hex = "0x" + aCh;
                    int number = Integer.decode(hex);
                    s.push(number);
                    if (numberLength > 0) {
                        int second = (int) s.peek();
                        s.pop();
                        int first = (int) s.peek();
                        s.pop();
                        s.push(first * 16 + second);
                    }
                    numberLength++;
                } else throw new UnknownCharacterException(aCh);

            }

        }
        int result = s.peek();
        s.pop();
        return result;
    }

    /**
     * takes an infix expression String and generates a postfix expression String out of it
     * @param ifx infix expression
     * @return postfix expression
     */
    String infixToPostfix(String ifx) throws UnknownCharacterException{
        ifx = ifx.trim().replaceAll("\\s", "");
        char[] expr = ifx.toCharArray();
        StringBuilder result = new StringBuilder();
        Stack<Character> p = new Stack<>();
        for (char anExpr : expr) {
            if ((anExpr >= 48 && anExpr <= 57))
                result.append(Character.getNumericValue(anExpr));
            else if (anExpr >= 65 && anExpr <= 70) {
                result.append(anExpr);
            } else if (anExpr == '(')
                p.push(anExpr);
            else if (anExpr == ')') {
                try {
                    while (!p.peek().equals('(')) {
                        result.append(" ").append(p.peek());
                        p.pop();
                    }
                    p.pop();
                } catch (NullPointerException e) {
                    System.out.println("Warning while parsing expression: Empty Stack, too many closing parentheses");
                }
            } else if (anExpr == '*' || anExpr == '-' || anExpr == '+' || anExpr == '/' || anExpr == '^') {
                result.append(" ");
                while (!p.isEmpty() && !(checkPrecedence(p.peek(), anExpr) || (anExpr == '^' && p.peek().equals('^')))) {
                    result.append(p.peek()).append(" ");
                    p.pop();
                }
                p.push(anExpr);
            } else throw new UnknownCharacterException(anExpr);
        }

        while (!p.isEmpty()) {
            result.append(" ").append(p.peek());
            p.pop();
        }


        return result.toString();

    }

    private boolean checkPrecedence(char top, char next) {
        return getPrec(top) < getPrec(next);
    }

    private int getPrec(char operator) {
        switch (operator) {
            case '(':
                return 0;
            case ')':
                return 0;
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    private void power() {
        int op2 = s.peek();
        s.pop();
        int op1 = s.peek();
        s.pop();
        int result = (int) Math.pow(op1, op2);
        s.push(result);
    }

    private void divide() {
        int op2 = s.peek();
        s.pop();
        int op1 = s.peek();
        s.pop();
        int result = Math.round((float)op1/(float)op2);
        s.push(result);
    }

    private void add() {
        int op2 =  s.peek();
        s.pop();
        int op1 = s.peek();
        s.pop();
        int result =  op1 + op2;
        s.push(result);
    }

    private void subtract() {
        if (s.size()>1) {
            int op2 = s.peek();
            s.pop();
            int op1 = s.peek();
            s.pop();
            int result = op1 - op2;
            s.push(result);
        } else {
            int op1 = s.peek();
            int result = op1*(-1);
            s.push(result);
        }
    }

    private void multiply() {
        int op2 = s.peek();
        s.pop();
        int op1 = s.peek();
        s.pop();
        int result = op1 * op2;
        s.push(result);
    }

    private class UnknownCharacterException extends RuntimeException {
        UnknownCharacterException(char c) {
            System.out.println("Error while parsing expression: Illegal Character: "+c);
            //printStackTrace();
        }
        public UnknownCharacterException() {
            System.out.println("Error while parsing expression: Illegal Character");
        }
    }

}

