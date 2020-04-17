package examples.collections;

import java.util.HashMap;
import examples.MyClass;

public class Main {
    public static void main(String[] args) {
        MyList myArrList = new MyArrayList();
        myArrList.add("str1");
        myArrList.add("str2");
        myArrList.add("str3");
        System.out.println("-".repeat(7) + " MyArrayList test " + "-".repeat(7));
        for (int i = 0; i < myArrList.size(); i++) {
            System.out.println(myArrList.get(i));
        }

        MyList myLinkList = new MyLinkedList();
        myLinkList.add("str1");
        myLinkList.add("str2");
        myLinkList.add("str3");
        myLinkList.add("str4");
        System.out.println("-".repeat(7) + " MyLinkedList test " + "-".repeat(7));
        for (int i = 0; i < myLinkList.size(); i++) {
            System.out.println(myLinkList.get(i));
        }


        System.out.println("-".repeat(7) + " HashMap test " + "-".repeat(7));
        MyClass myClass = new MyClass();

        HashMap<MyClass, String> map = new HashMap<>();
        map.put(myClass, "first");
        System.out.println(map.get(myClass));
    }
}
