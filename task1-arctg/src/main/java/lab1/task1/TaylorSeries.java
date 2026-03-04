package lab1.task1;

public class TaylorSeries {

    public static double arctg(double x, int terms) {
        if (terms <= 0) {
            throw new IllegalArgumentException("terms must be positive");
        }

        if (x == 1.0) return Math.PI / 4.0;
        if (x == -1.0) return -Math.PI / 4.0;

        if (x > 1.0) {
            return Math.PI / 2.0 - arctg(1.0 / x, terms);
        }
        if (x < -1.0) {
            return -Math.PI / 2.0 - arctg(1.0 / x, terms);
        }

        if (Math.abs(x) > 0.5) {
            double s = x > 0 ? 1.0 : -1.0;
            double y = (x - s) / (1.0 + s * x);
            return s * (Math.PI / 4.0) + arctg(y, terms);
        }

        double sum = 0.0;
        double c = 0.0;

        double termPow = x;
        double x2 = x * x;

        for (int k = 0; k < terms; k++) {
            double denom = 2.0 * k + 1.0;
            double add = termPow / denom;
            if ((k & 1) == 1) add = -add;

            double y = add - c;
            double t = sum + y;
            c = (t - sum) - y;
            sum = t;

            termPow *= x2;
        }

        return sum;
    }
}