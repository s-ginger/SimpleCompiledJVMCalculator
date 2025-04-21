package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.antlr.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import java.io.FileOutputStream;
import static org.objectweb.asm.Opcodes.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(V1_8, ACC_PUBLIC, "Main", null, "java/lang/Object", null);

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        String input = "1 + 3";
        ExprLexer lexer = new ExprLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ExprParser.ExprContext tree = parser.expr();

        CustomVisitor visitor = new CustomVisitor(mv);
        visitor.visit(tree);

        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitInsn(SWAP); // результат должен быть вторым
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        // Завершение
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        cw.visitEnd();

        // Сохраняем .class файл
        FileOutputStream fos = new FileOutputStream("bin/Main.class");
        fos.write(cw.toByteArray());
        fos.close();

    }
}

