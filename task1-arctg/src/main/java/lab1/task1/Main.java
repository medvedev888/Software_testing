package lab1.task1;

public class Main {
    public static void main(String[] args) {
        int terms = 80;

        for (double x = -10; x <= 10; x += 0.5) {
            double approx = TaylorSeries.arctg(x, terms);
            double exact = Math.atan(x);
            double err = Math.abs(approx - exact);

            System.out.printf(
                    "x=%6.2f | arctg≈% .12f | atan=% .12f | |err|=%.3e%n",
                    x, approx, exact, err
            );
        }
    }
}