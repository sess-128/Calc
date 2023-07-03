import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        // Ввод данных
        Calc calculator = new Calc();
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();
        String[] collected = userInputString.split(" ");
        calculator.analyzer(collected);
    }
}
class Calc {
    List<String> arabicNumbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    List<String> romanNumbers = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    void analyzer(String[] userInput) throws Exception {
        if (userInput.length <3){
            throw new Exception("No mathematical operations have been identified OR incorrect input format");
        } else if (userInput.length > 3) {
            throw new Exception("Enter two operands and an arithmetic sign in the format");
        }
        String first = userInput[0];
        String second = userInput[2];
        String operand = userInput[1];

        if (arabicNumbers.contains(first) && arabicNumbers.contains(second)){
                int firstA = Integer.parseInt(first);
                int secondA = Integer.parseInt(second);
                    Calc arabic = new Calc();
                    int result = arabic.calculateA(firstA, secondA, operand);
                    System.out.println(result);
                } else if (romanNumbers.contains(first) && romanNumbers.contains(second)){
                Calc roman = new Calc();
                int firstR = roman.romanArabic(first);
                int secondR = roman.romanArabic(second);
                int toRoman = roman.calculateR(firstR,secondR,operand);
                String result = roman.arabicRoman(toRoman);
                System.out.println(result);
            } else if ((arabicNumbers.contains(first) && romanNumbers.contains(second)) || (arabicNumbers.contains(second) && romanNumbers.contains(first))) {
            throw new Exception("Different number systems!");
        } else throw new Exception("Out of range of 1 to 10 OR NaN");
    }

    int romanArabic(String c) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);

        int lastChar = c.length() - 1;
        char[] inside = c.toCharArray();
        int flow;
        int result = map.get(inside[lastChar]);

        for (int i = lastChar - 1; i >= 0; i--) {
            flow = map.get(inside[i]);
            if (flow < map.get(inside[i + 1])) {
                result -= flow;
            } else {
                result += flow;
            }
        }
        return result;
    }

    String arabicRoman(int a) {
        int[] numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length && a > 0; i++) {
            while (a >= numbers[i]) {
                a -= numbers[i];
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }
    int calculateA(int a, int b, String c) throws Exception {
        int res;
        switch (c) {
            case "+" -> res = a + b;
            case "-" -> res = a - b;
            case "*" -> res = a * b;
            case "/" -> {
                if (b == 0){throw new Exception("Cannot divided by 0");}
                res = a / b;}
            default -> throw new Exception("Unknown operand");
        }
        return res;
    }
    int calculateR(int a, int b, String c) throws Exception {
        int res;
        switch (c) {
            case "+" -> res = a + b;
            case "-" -> {
                if (b >= a){
                    throw new Exception("The result cannot be equal to or less than 0 in Roman System");
                }res = a - b;}
            case "*" -> res = a * b;
            case "/" -> res = a / b;
            default -> throw new Exception("Unknown operand");
        }
        return res;
    }
}

