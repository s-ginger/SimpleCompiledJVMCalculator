package org.example;

import org.example.antlr.ExprBaseListener;
import org.example.antlr.ExprParser;

public class CustomListener extends ExprBaseListener {
    @Override
    public void enterExpr(ExprParser.ExprContext ctx) {
        System.out.println("Expression: " + ctx.getText());
    }
}
