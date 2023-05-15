import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String expression = scanner.nextLine();
            String result = calc(expression);
            System.out.println(result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public static String calc(String input) {

        String[] expression = input.split("\\s+");

        if (expression.length != 3) {
            throw new InputMismatchException("Expression does not match pattern: [operand][ ][operation][ ][operand]");
        }

        String op1Str   = expression[0];
        String operator = expression[1];
        String op2Str   = expression[2];

        int op1, op2;

        if (RomanNumerals.isRoman(op1Str) && RomanNumerals.isRoman(op2Str)) {
            op1 = RomanNumerals.convertRomanToArabic(op1Str);
            op2 = RomanNumerals.convertRomanToArabic(op2Str);
        } else if (isNumeric(op1Str) && isNumeric(op2Str)) {
            op1 = Integer.parseInt(op1Str);
            op2 = Integer.parseInt(op2Str);
        } else {
            throw new InputMismatchException("Invalid input");
        }

        int result = performOperation(op1, op2, operator);

        if (RomanNumerals.isRoman(op1Str) && RomanNumerals.isRoman(op2Str)) {
            if (result <= 0) {
                throw new InputMismatchException("Negative or zero result in expression using Roman numerals");
            }
            return convertArabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static int performOperation(int op1, int op2, String operator) {
        switch (operator) {
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "*": return op1 * op2;
            case "/":
                if (op2 == 0) {
                    throw new InputMismatchException("Division by zero is not allowed");
                }
                return op1 / op2;
            default:
                throw new InputMismatchException("Invalid operator");
        }
    }

    public static String convertArabicToRoman(int arabicDigit) {
        String[] romanUnits      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romanTenths     = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanHundreds   = {"", "C"};
        int arabicUnits    = arabicDigit % 10;
        int arabicTenths   = (arabicDigit / 10) % 10;
        int arabicHundreds = ((arabicDigit / 100) % 10);
        return romanHundreds[arabicHundreds] + romanTenths[arabicTenths] + romanUnits[arabicUnits];
    }

    public static boolean isNumeric(String str) {
        if (!str.matches("[1-9]|10")) {
            throw new InputMismatchException("Invalid numeric value. Should be from 1 to 10 inclusively");
        }
        return true;
    }
}

class RomanNumerals {

    private static final HashMap<String, Integer> romanToArabicMap = new HashMap<>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
    }};

    public static boolean isRoman(String str) {
        return romanToArabicMap.containsKey(str);
    }

    public static int convertRomanToArabic(String romanNumeral) {
        return romanToArabicMap.get(romanNumeral);
    }

}



