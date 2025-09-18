package interviews.alfabank;

import java.util.HashMap;
import java.util.Objects;

//1. напишите свою реализацию метода equals
public class Task1 {
    static class Person {
        private String firstName;
        private String surName;
        private String secondName;

        @Override
        public boolean equals(Object o) {
            //напишите свою реализацию
            if(this == o)
                return true;
            if (o instanceof Person p) {
                return Objects.equals(firstName, p.firstName) && Objects.equals(surName, p.surName) && Objects.equals(secondName, p.secondName);
            }

            return false;

            // правильно так:
            //if (this == o) return true;
            //if (o == null || getClass() != o.getClass()) return false; // строгая проверка по классу
            //Person p = (Person) o;
            //return Objects.equals(firstName, p.firstName)
            //        && Objects.equals(surName, p.surName)
            //        && Objects.equals(secondName, p.secondName);
        }

        @Override
        public int hashCode() {
            // это для примера
            return  1;

            // правильно так:
            //return Objects.hash(firstName, surName, secondName);
        }
    }

    static class Student extends Person {
        private Integer age;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o instanceof Student s) {
                return super.equals(s) && Objects.equals(age, s.age);
            }
            return false;

            // правильно так + hashCode():
            //if (this == o) return true;
            //if (o == null || getClass() != o.getClass()) return false; // Student != Person
            //if (!super.equals(o)) return false;
            //Student s = (Student) o;
            //return Objects.equals(age, s.age);
        }
    }

    public static void main(String[] args) {
        var map = new HashMap<Person, Integer>();
        var p = new Person();
        var s = new Student();
        map.put(p, 1);
        System.out.println(map.get(s)); //null

        System.out.println(p.equals(s)); //true
        System.out.println(s.equals(p)); //false
    }
}
