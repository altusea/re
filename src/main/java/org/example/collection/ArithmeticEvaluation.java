package org.example.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class ArithmeticEvaluation {

    public interface Expression {
        double eval();
    }

    public enum Parentheses {
        LEFT
    }

    public enum BinaryOperator {
        ADD('+', 1),
        SUB('-', 1),
        MUL('*', 2),
        DIV('/', 2);

        public final char symbol;
        public final int precedence;

        BinaryOperator(char symbol, int precedence) {
            this.symbol = symbol;
            this.precedence = precedence;
        }

        public double eval(double leftValue, double rightValue) {
            return switch (this) {
                case ADD -> leftValue + rightValue;
                case SUB -> leftValue - rightValue;
                case MUL -> leftValue * rightValue;
                case DIV -> leftValue / rightValue;
            };
        }

        public static BinaryOperator forSymbol(char symbol) {
            for (BinaryOperator operator : values()) {
                if (operator.symbol == symbol) {
                    return operator;
                }
            }
            throw new IllegalArgumentException(String.valueOf(symbol));
        }
    }

    public static class Number implements Expression {
        private final double number;

        public Number(double number) {
            this.number = number;
        }

        @Override
        public double eval() {
            return number;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }
    }

    public record BinaryExpression(Expression leftOperand,
                                   BinaryOperator operator,
                                   Expression rightOperand) implements Expression {

        @Override
        public double eval() {
            double leftValue = leftOperand.eval();
            double rightValue = rightOperand.eval();
            return operator.eval(leftValue, rightValue);
        }

        @Override
        public String toString() {
            return "(" + leftOperand + " " + operator.symbol + " " + rightOperand + ")";
        }
    }

    private static void createNewOperand(BinaryOperator operator, Stack<Expression> operands) {
        Expression rightOperand = operands.pop();
        Expression leftOperand = operands.pop();
        operands.push(new BinaryExpression(leftOperand, operator, rightOperand));
    }

    public static Expression parse(@NotNull String input) {
        int curIndex = 0;
        boolean afterOperand = false;
        Stack<Expression> operands = new Stack<>();
        Stack<Object> operators = new Stack<>();
        while (curIndex < input.length()) {
            int startIndex = curIndex;
            char c = input.charAt(curIndex++);

            if (Character.isWhitespace(c))
                continue;

            if (afterOperand) {
                if (c == ')') {
                    Object operator;
                    while (!operators.isEmpty() && ((operator = operators.pop()) != Parentheses.LEFT))
                        createNewOperand((BinaryOperator) operator, operands);
                    continue;
                }
                afterOperand = false;
                BinaryOperator operator = BinaryOperator.forSymbol(c);
                while (!operators.isEmpty() && (operators.peek() != Parentheses.LEFT) && (((BinaryOperator) operators.peek()).precedence >= operator.precedence))
                    createNewOperand((BinaryOperator) operators.pop(), operands);
                operators.push(operator);
                continue;
            }

            if (c == '(') {
                operators.push(Parentheses.LEFT);
                continue;
            }

            afterOperand = true;
            while (curIndex < input.length()) {
                c = input.charAt(curIndex);
                if (((c < '0') || (c > '9')) && (c != '.'))
                    break;
                curIndex++;
            }
            operands.push(new Number(Double.parseDouble(input.substring(startIndex, curIndex))));
        }

        while (!operators.isEmpty()) {
            Object operator = operators.pop();
            if (operator == Parentheses.LEFT)
                throw new IllegalArgumentException();
            createNewOperand((BinaryOperator) operator, operands);
        }

        Expression expression = operands.pop();
        if (!operands.isEmpty())
            throw new IllegalArgumentException();
        return expression;
    }

    public static void main(String[] args) {
        String[] testExpressions = {
                "2+3",
                "2+3/4",
                "2*3-4",
                "2*(3+4)+5/6",
                "2 * (3 + (4 * 5 + (6 * 7) * 8) - 9) * 10",
                "2*-3--4+-.25"};
        for (String testExpression : testExpressions) {
            Expression expression = parse(testExpression);
            System.out.printf("Input: \"%s\", AST: \"%s\", value=%s%n", testExpression, expression, expression.eval());
        }
    }
}
