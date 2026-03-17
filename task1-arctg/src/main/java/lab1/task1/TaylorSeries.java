package lab1.task1;

public class TaylorSeries {
    public static double arctgSeries(double argument, int terms){
        double sum = 0.0;
        double power = argument;

        for(int i = 0; i < terms; i++){
            double term = power / (2 * i + 1);
            sum = (i % 2 == 0) ? sum + term : sum - term;
            power *= Math.pow(argument, 2);
        }

        return sum;
    }

    public static double arctg(double argument, int terms){
        if(Double.isNaN(argument) || Double.isInfinite(argument)){
            throw new IllegalArgumentException("Argument must be a finite number!");
        }

        if(terms <= 0){
            throw new IllegalArgumentException("Number of terms must be positive!");
        }

        if(argument > 1.0){
            return Math.PI / 2 - arctgSeries(1.0 / argument, terms);
        } else if(argument < -1.0){
            return -Math.PI / 2 - arctgSeries(1.0 / argument, terms);
        } else {
            return arctgSeries(argument, terms);
        }
    }
}
