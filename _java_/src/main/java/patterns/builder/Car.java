package patterns.builder;

public class Car {
    private String maker;
    private Transmission transmission;
    private int maxSpeed;

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [maker=" + maker + ", transmission=" + transmission
                + ", maxSpeed=" + maxSpeed + "]";
    }
}
