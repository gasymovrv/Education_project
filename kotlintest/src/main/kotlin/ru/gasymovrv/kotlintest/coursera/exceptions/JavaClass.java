package ru.gasymovrv.kotlintest.coursera.exceptions;


import java.io.IOException;

public class JavaClass {

  public static void main(String[] args) {
    try {
      MainKt.bar();
    } catch (IOException e) {
      e.printStackTrace();
    }

    //try {
    //  MainKt.foo();
    //} catch (IOException e) { //Exception 'java.io.IOException' is never thrown in the
    // corresponding try block
    //  e.printStackTrace();
    //}
  }
}
