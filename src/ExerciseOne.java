import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExerciseOne {
    static double calculateCommision(double value) throws ValueIsLessOrEqualThanNullException {
        double commision = 0;
        if (value <= 0) throw new ValueIsLessOrEqualThanNullException();
        commision = value * 0.02 + 3;
        BigDecimal com = new BigDecimal(Double.toString(commision));
        com = com.setScale(2, RoundingMode.HALF_UP);
        commision = com.doubleValue();
        if (commision <= 5) return 5;
        else if (commision >= 20) return 20;
        else return commision;
    }

    static double fromStringWithAmountToDouble(String s) throws IllegalArgumentException {
        double value;
        s = s.replace(" грн", "");
        value = Double.valueOf(s);
        return value;
    }
}

class ValueIsLessOrEqualThanNullException extends Exception {

}

class MainOne {
    public static void main(String[] args) {
        String amount1 = "1.23 грн";
        String amount2 = "235.38 грн";
        String amount3 = "1680.45 грн";



        double com1;
        double com2;
        double com3;
        try {
            double value;
            value = ExerciseOne.fromStringWithAmountToDouble(amount1);
            com1 = ExerciseOne.calculateCommision(value);
            System.out.println(com1);

            value = ExerciseOne.fromStringWithAmountToDouble(amount2);
            com2 = ExerciseOne.calculateCommision(value);
            System.out.println(com2);

            value = ExerciseOne.fromStringWithAmountToDouble(amount3);
            com3 = ExerciseOne.calculateCommision(value);
            System.out.println(com3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}