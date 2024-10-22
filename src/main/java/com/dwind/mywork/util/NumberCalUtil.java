package com.dwind.mywork.util;

import java.util.Stack;

public class NumberCalUtil {

    public static double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder numberStr = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numberStr.append(expression.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(numberStr.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek()!= '(') {
                    performOperation(numbers, operators);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    performOperation(numbers, operators);
                }
                operators.push(c);
            }
        }

        while (!operators.empty()) {
            performOperation(numbers, operators);
        }

        return numbers.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static void performOperation(Stack<Double> numbers, Stack<Character> operators) {
        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char op = operators.pop();

        switch (op) {
            case '+':
                numbers.push(num1 + num2);
                break;
            case '-':
                numbers.push(num1 - num2);
                break;
            case '*':
                numbers.push(num1 * num2);
                break;
            case '/':
                numbers.push(num1 / num2);
                break;
        }
    }

    public static void main(String[] args) {
        String expression = "2*(3+4)-5/2";
        double result = evaluate(expression);
        System.out.println("结果为：" + result);
    }
}
