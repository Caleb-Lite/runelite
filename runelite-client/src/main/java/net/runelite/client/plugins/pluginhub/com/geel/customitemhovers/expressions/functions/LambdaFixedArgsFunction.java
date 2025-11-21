package net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.functions;

import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.ExecutionContext;
import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.models.ArgumentType;
import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.models.Token;
import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.models.TokenType;

public class LambdaFixedArgsFunction extends FixedArgsFunction {
    private java.util.function.BiFunction<Token[], ExecutionContext, Token> executionFunc;

    public LambdaFixedArgsFunction(String name, TokenType returnType, ArgumentType[] arguments,
                                   java.util.function.BiFunction<Token[], ExecutionContext, Token> executionFunc) {
        super(name, returnType, arguments);
        this.executionFunc = executionFunc;
    }

    public LambdaFixedArgsFunction(String name, TokenType returnType, ArgumentType[] arguments, boolean isConstExpr,
                                   java.util.function.BiFunction<Token[], ExecutionContext, Token> executionFunc) {
        super(name, returnType, arguments, isConstExpr);
        this.executionFunc = executionFunc;
    }

    protected Token innerExecute(Token[] arguments, ExecutionContext context) {
        return executionFunc.apply(arguments, context);
    }
}
