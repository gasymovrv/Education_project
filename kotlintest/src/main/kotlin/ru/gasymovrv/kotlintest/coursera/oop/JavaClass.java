package ru.gasymovrv.kotlintest.coursera.oop;

import static ru.gasymovrv.kotlintest.coursera.oop.ClassModifiersKt.extFun;

public class JavaClass {

  public static void main(String[] args) {
    new C3().f3$kotlintest();
    KSingleton.INSTANCE.print();
    KSingleton.INSTANCE.print();
    KSingleton.INSTANCE.print();
    KSingleton.bar();

    extFun(SimpleCompanion.Companion);
    SimpleCompanion.foo();
    SimpleCompanion.Companion.bar();
    SimpleCompanion.Companion.foo();

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
  }
}
