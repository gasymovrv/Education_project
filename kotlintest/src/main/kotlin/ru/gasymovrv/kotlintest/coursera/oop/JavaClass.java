package ru.gasymovrv.kotlintest.coursera.oop;


import java.util.List;
import org.jetbrains.annotations.Nullable;
import ru.gasymovrv.kotlintest.coursera.oop.generics.GenericsKt;

import static ru.gasymovrv.kotlintest.coursera.oop.ObjectsKt.extFun;

public class JavaClass {

    public static String getJavaString() {
        return null;
    }

    @Nullable
    public static String getNullableJavaString() {
        return null;
    }

    public static void main(String[] args) {
        new C3().f3$kotlintest();
        KSingleton.INSTANCE.print();
        KSingleton.INSTANCE.print();
        KSingleton.INSTANCE.print();

        // Does not compile, because print() doesn't have @JvmStatic.
        // It's generated as method of KSingleton.INSTANCE object
        //KSingleton.print();
        KSingleton.bar();

        extFun(SimpleCompanion.Companion);
        //SimpleCompanion.bar(); //does not compile, because bar() doesn't have @JvmStatic
        SimpleCompanion.foo();
        SimpleCompanion.Companion.bar();
        SimpleCompanion.Companion.foo();
        System.out.println(SimpleCompanion.Companion.getClass());

        //Contact prop = Obj1.INSTANCE.getProp(); //does not work because of @JvmField
        Contact prop = Obj1.prop;
        System.out.println(Obj1.getProp2()); //it gets the field from Obj1.INSTANCE.prop2

        Class1 class1 = new Class1();
        //Contact prop1 = class1.getProp(); //does not work because of @JvmField
        Contact prop1 = class1.prop;

        int a = ConstantsKt.a;
        int b = ConstantsKt.b;
        String empty = ConstantsKt.empty;
        Contact contact = ConstantsKt.prop;
        int c = ConstantsKt.getC();

        System.out.println(GenericsKt.averageOfDouble(List.of(0.0, 1.4, 3.5)));
    }
}
