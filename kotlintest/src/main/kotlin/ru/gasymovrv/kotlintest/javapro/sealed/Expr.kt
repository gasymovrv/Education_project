package ru.gasymovrv.kotlintest.javapro.sealed

sealed class Expr
class Const(val num: Double) : Expr()
class Sum(val a1: Expr, val a2: Expr) : Expr()
object NotNumber : Expr()


fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.num
    is Sum -> eval(expr.a1) + eval(expr.a2)
    NotNumber -> Double.NaN
}