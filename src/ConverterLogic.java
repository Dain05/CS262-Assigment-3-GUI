// Assignment #3 - GUI Applications
// Course: CS262 - Introduction to Object-Oriented Programming II
// Lecturer: Doron Williams

// Group Members:
// Dain Thorpe - ID: 2301011605
// Joan Johnson-Brown - ID: 2401010520
// Shanique Wisdom - ID: 2401010649
// Pasha Pinnock - ID: 2401011323
// Dante Graham - ID: 2401010192

public class ConverterLogic {

    public static double convertToJMD(double amount, String currency) {
        switch (currency) {
            case "USD":
                return amount * 129.02;
            case "CAD":
                return amount * 97.50;
            case "EUR":
                return amount * 164.33;
            default:
                return 0;
        }
    }
}