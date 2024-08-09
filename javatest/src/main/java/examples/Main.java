package examples;

import static examples.utils.NumberedExamplesUtil.printNumberOfExample;

import examples.collections.ListPerformanceTest;
import examples.multiparents.MyInterface1;
import examples.multiparents.MyInterfaceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main{
    public static void main(String[] args) {
        new DoubleTest().go();
        new FinallyTest().go();
        new RandomTest().go();
        new RegexTest().go();
        new Java8Test().go();
        new ListPerformanceTest().go();
        new BCryptTest().go();

        System.out.println(
                "---------------------------------------------------- Разное - BEGIN ---------------------------------------------------"
        );

        //Ручной toString в цикле
        printNumberOfExample(1, "Ручной toString в цикле");
        List<MyClass> myClasses = new ArrayList<>();
        myClasses.add(new MyClass(1L,"info1"));
        myClasses.add(new MyClass(2L,"info2"));
        myClasses.add(new MyClass());
        myClasses.add(new MyClass(3L,"info3"));
        myClasses.add(new MyClass("info4"));
        myClasses.add(new MyClass());
        ArrayList<String> strings = new ArrayList<>();
        myClasses.forEach((mc)-> strings.add(String.format("{id=%s; info=%s}",mc.getId(), mc.getInfo())));
        System.out.println("strings="+strings);


        //Вывод сета енамов
        printNumberOfExample(2, "Вывод сета енамов");
        Set<MyClass.OrderType> myClasses2 = new HashSet<>();
        myClasses2.add(MyClass.OrderType.CU);
        myClasses2.add(MyClass.OrderType.PO);
        myClasses2.add(MyClass.OrderType.RO);
        myClasses2.add(MyClass.OrderType.PRO);
        myClasses2.add(MyClass.OrderType.SO);
        System.out.println("enums="+myClasses2);


        //substring последних 2х цифр и удаление нуля
        printNumberOfExample(3, "substring последних 2х цифр и удаление нуля");
        String spz = "1234507";
        String str = "";
        str = spz.substring(spz.length()-2, spz.length());
        if(str.startsWith("0")){
            str = str.substring(1, str.length());
        }
        System.out.println("2 последние цифры из "+spz+" без нуля = "+str);


        //Мапы и null
        printNumberOfExample(4, "Мапы и null");
        Map<String, MyClass> map = new HashMap<>();
        map.put("str1", new MyClass("info1"));
        map.put("str2", null);
        map.put(null, new MyClass("info2"));
        map.put(null, new MyClass("info3"));
        System.out.println("map.get(\"str1\") = "+map.get("str1"));
        System.out.println("map.get(\"str2\") = "+map.get("str2"));
        System.out.println("map.get(\"str3\") = "+map.get("str3"));
        System.out.println("map.get(null) = "+map.get(null));


        //Боксинг long в Long при сохранении в Object
        printNumberOfExample(5, "Боксинг long в Long при сохранении в Object");
        long l1 = 425L;
        Object o1 = l1;
        System.out.println("Object o1 = 425L, o1.getClass()="+o1.getClass());


        //присваивание на лету
        printNumberOfExample(6, "присваивание на лету if((mc=mc2)!=null)");
        MyClass mc = null;
        MyClass mc2=new MyClass("mc2");
        if((mc=mc2)!=null){
            System.out.println(mc);
        }


        //Наследование
        printNumberOfExample(7, "Наследование");
        MyClass mcc = new MyClassChild(1L, "child");
        System.out.println(mcc);
        mcc.printSomething("что-то");


        //Множественно наследование (абстрактный класс и 2 интерфейса с одинаковым реализованным методом)
        printNumberOfExample(8, "Множественно наследование (абстрактный класс и 2 интерфейса с одинаковым реализованным методом)");
        MyInterface1 myInterfaceImpl = new MyInterfaceImpl();
        myInterfaceImpl.print();


        System.out.println(
                "---------------------------------------------------- Разное - END ---------------------------------------------------"
        );
    }


}
