package org.example;

import org.example.antlr.ExprBaseVisitor;
import org.example.antlr.ExprParser;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;



public class CustomVisitor extends ExprBaseVisitor<Void> {
    private final MethodVisitor mv;

    public CustomVisitor(MethodVisitor mv) {
        this.mv = mv;
    }

    @Override
    public Void visitAddSub(ExprParser.AddSubContext ctx) {
        visit(ctx.getChild(0));
        visit(ctx.getChild(2));
        String op = ctx.getChild(1).getText(); // оператор
        if (op.equals("+")) {
            mv.visitInsn(IADD);
        } else  {
            mv.visitInsn(ISUB);
        }
        return null;
    }

    @Override
    public Void visitMulDiv(ExprParser.MulDivContext ctx) {
        visit(ctx.getChild(0));
        visit(ctx.getChild(2));
        String op = ctx.getChild(1).getText();
        if (op.equals("*")) {
            mv.visitInsn(IMUL);
        } else {
            mv.visitInsn(IDIV);
        }
        return null;
    }

    @Override
    public Void visitInt(ExprParser.IntContext ctx) {
        int value = Integer.parseInt(ctx.getText());
        mv.visitLdcInsn(value);
        return null;
    }

    @Override
    public Void visitParens(ExprParser.ParensContext ctx) {
        visit(ctx.expr());
        return null;
    }

}

