package examples;

/**
 * Родитель для всех классов с различными примерами и задачами
 */
public abstract class Test {
    public void go(){
        String name = this.getClass().getSimpleName();
        System.out.printf(
                "\n----------------------------------------------------%s - BEGIN---------------------------------------------------\n", name);
        contentGo();
        System.out.printf(
                "----------------------------------------------------%s - END---------------------------------------------------\n\n", name);
    }

    abstract public void contentGo();
}
