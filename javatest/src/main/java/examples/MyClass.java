package examples;

public class MyClass {
    private Long id;
    private String info;

    public enum OrderType {
        PRO,
        PO,
        RO,
        SO,
        CU
    }

    public MyClass(Long id) {
        this.id = id;
    }

    public MyClass(String info) {
        this.info = info;
    }

    public MyClass(Long id, String info) {
        this.id = id;
        this.info = info;
    }

    public MyClass() {
    }

    public String getInfo() {
        //System.out.println(info);
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }

    public static void printSomething(String s) {
        System.out.println("printSomething=" + s);
    }
}
