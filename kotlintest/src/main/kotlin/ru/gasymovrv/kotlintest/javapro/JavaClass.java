package ru.gasymovrv.kotlintest.javapro;

public class JavaClass {
    public String method() {
        KotlinClass kotlinClass = new KotlinClass("sdf");
        kotlinClass.getStr();
        return "Java method!";
    }
}

