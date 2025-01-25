package ru.gasymovrv.kotlintest.coursera.oop

public abstract class C1 {

    public abstract fun f1(s: String)
    protected abstract fun f1Protected()

    public final fun f2() {}
    internal final fun f3() {} //visible in module (gradle, maven, etc.)
    protected final fun f4() {} //visible only in subclasses (not in package)
    private final fun f5() {}
}

open class C2 {

    public final fun f1() {}
    public open fun f2() {}
//  abstract fun f3()
}

class C3 : C1() {

    public final fun f6() {}
    public override fun f1(s: String) {}
    protected override fun f1Protected() {}
    //override fun f3() {}
}
