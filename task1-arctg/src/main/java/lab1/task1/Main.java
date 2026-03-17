package lab1.task1;

public class Main {

    public static void main(String[] args) {
        int terms = 200;

        for (double argument = -3.0; argument <= 3.0; argument += 0.25) {
            double taylorValue = TaylorSeries.arctg(argument, terms);
            double mathValue = Math.atan(argument);
            double error = Math.abs(taylorValue - mathValue);

            System.out.printf(
                    "arctg(%.2f) = %.10f | Math.atan(%.2f) = %.10f | error = %.10f%n",
                    argument, taylorValue, argument, mathValue, error
            );
        }
    }
}