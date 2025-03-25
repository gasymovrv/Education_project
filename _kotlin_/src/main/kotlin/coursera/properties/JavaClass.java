package coursera.properties;

public class JavaClass {

    private final String name;
    private boolean isActive;

    public JavaClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "JavaClass{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
