package examples.exceptions;

public class TryWithResourcesTest {

    public static void main(String[] args) {
        //try {
        tryWithResourcesTwoResources();
        //tryWithResourcesSingleResource();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    Throwable[] suppressed = e.getSuppressed();
        //    System.out.println("suppressed = " + Arrays.toString(suppressed));
        //}
    }

    public static void tryWithResourcesSingleResource() {
        try (MyAutoCLosable resourceOne = new MyAutoCLosable("One", true)) {
            resourceOne.doOp(false);
        } catch (Exception e) {
            System.out.println("Caught in t-w-r: " + e);
        }
    }

    public static void tryWithResourcesTwoResources() {
        try (MyAutoCLosable resourceOne = new MyAutoCLosable("One", false);
             MyAutoCLosable resourceTwo = new MyAutoCLosable("Two", true)
        ) {
            resourceOne.doOp(false);
            resourceTwo.doOp(false);
        } catch (Exception e) {
            System.out.println("Caught in t-w-r: " + e);
        }
    }
}
