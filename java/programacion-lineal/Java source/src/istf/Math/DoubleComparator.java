package istf.math;

public class DoubleComparator {

    private double epsilon;

    public DoubleComparator(int range) {
        epsilon = 0.1;

        for (int i = 0; i < range; i++) {
            epsilon = epsilon / 10;
        }
    }

    public DoubleComparator() {
        epsilon = 0.00001;
    }

    public boolean areEquals(Double number1, Double number2) {
        double difference = Math.abs(number1 - number2);

        return difference <= epsilon;
    }

}
