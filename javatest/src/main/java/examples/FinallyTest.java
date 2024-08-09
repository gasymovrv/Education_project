package examples;
/**
* Возвращаем finally и получаем корневое исключение
 */
public class FinallyTest extends Test {
    private static Test thisOne = new FinallyTest();

    @Override
    public void contentGo() {
        System.out.println(getMessage());
    }

    private String getMessage(){
        String message = null;
        try {
            try {
                throw new RuntimeException("Корневое исключение!");
            } catch (RuntimeException e) {
                try {
                    throw new RuntimeException(e);
                } catch (RuntimeException e1) {
                    try {
                        throw new RuntimeException(e1);
                    } catch (RuntimeException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        } catch (RuntimeException e){
            Throwable t = e;
            //берем корневое исключение
            while (t.getCause() != null){
                t = t.getCause();
            }
            //и сообщение из него
            message = t.getMessage();
            System.out.println("Print from catch: " + message);
            return message;
        } finally {
            System.out.println("Print from finally: " + message);
            message += " from finally";
            return message;
        }
    }

    public static void main(String[] args) {
        thisOne.go();
    }
}
