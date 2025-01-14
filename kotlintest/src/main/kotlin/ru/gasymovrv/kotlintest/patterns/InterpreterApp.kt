package ru.gasymovrv.kotlintest.patterns

object InterpreterApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val context = Context()
        val expr = context.evaluate("-1+2+3+866-2")
        println(expr.interpret())
    }
}

//-------------------------------------------------Context--------------------------------------------------------------
class Context {

    fun evaluate(s: String): Expression {
        var pos = s.length - 1
        while (pos > 0) {
            if (Character.isDigit(s[pos])) {
                pos--
            } else {
                val left = evaluate(s.substring(0, pos))
                val right: Expression = NumberExpression(s.substring(pos + 1, s.length).toInt())
                val operator = s[pos]
                when (operator) {
                    '-' -> return MinusExpression(left, right)
                    '+' -> return PlusExpression(left, right)
                }
            }
        }
        val result = s.toInt()
        return NumberExpression(result)
    }
}

//-----------------------------------------------Expressions------------------------------------------------------------
//AbstractExpression
interface Expression {

    fun interpret(): Int
}

//TerminalExpression
class NumberExpression(private var number: Int) : Expression {

    override fun interpret(): Int {
        return number
    }
}

//NonterminalExpression
class MinusExpression(private var left: Expression, private var right: Expression) : Expression {

    override fun interpret(): Int {
        return left.interpret() - right.interpret()
    }
}

//NonterminalExpression
class PlusExpression(private var left: Expression, private var right: Expression) : Expression {

    override fun interpret(): Int {
        return left.interpret() + right.interpret()
    }
}

