package javapro.interfaces

class MyInterfaceImpl(override val member: String, override var field: Int = 0) : MyInterface {

    override var field2: Int = 1

    override fun method2() {
        val ste = Thread.getAllStackTraces().get(Thread.currentThread())!![2]
        println("\tclassLoaderName = ${ste.classLoaderName}")
        println("\tclassName = ${ste.className}")
        println("\tmethodName = ${ste.methodName}")
        println("\tmoduleName = ${ste.moduleName}")
    }

    override fun method1() {
        Thread.getAllStackTraces().get(Thread.currentThread())!!.forEachIndexed { ind, ste ->
            println("StackTraceElement #$ind")
            println("\tclassLoaderName = ${ste.classLoaderName}")
            println("\tclassName = ${ste.className}")
            println("\tmethodName = ${ste.methodName}")
            println("\tmoduleName = ${ste.moduleName}")
        }
    }

    override fun method3() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
