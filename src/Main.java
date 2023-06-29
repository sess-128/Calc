import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        // Ввод данных

        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();
        String[] collected = userInputString.split(" ");

        // Проверка инпута на длину

        if (collected.length != 3) throw new Exception("Строка должна содержать" +
                " два операнда и арифметичский знак") {
            };
        // Массивы чисел для проверки
        String[] arabicNumbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romanNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
//
        // Проверяем полученные данные на вхождение в массивы
        String searchedNumber1 = collected[0];
        String searchedNumber2 = collected[2];
//        // Арабские
        boolean checkedArabic1 = Arrays.asList(arabicNumbers).contains(searchedNumber1);
        boolean checkedArabic2 = Arrays.asList(arabicNumbers).contains(searchedNumber2);
        boolean goodA = checkedArabic2 && checkedArabic1;

        // Римские
        boolean checkedRoman1 = Arrays.asList(romanNumbers).contains(searchedNumber1);
        boolean checkedRoman2 = Arrays.asList(romanNumbers).contains(searchedNumber2);
        boolean goodR = checkedRoman1 && checkedRoman2;

        // Проверка на ввод чисел из одной системы счисления
        if (goodA || goodR) {
            System.out.println("Все гуд");
        } else {
            throw new Exception("Числа должны быть разных систем счисления");
        }

        String operand = collected[1];
        if (goodA){
            int first = Integer.parseInt(collected[0]);
            int second = Integer.parseInt(collected[2]);
            if ((first < 1 || first >10) || (second<1 || second >10)){
                throw new Exception("Out of bounds");
            } else {
                Calc arabic = new Calc();
                int result = arabic.calculateA(first, second, operand);
                System.out.println(result);
            }
        } else if (goodR) {
            Calc roman = new Calc();
            int first = roman.romanArabic(collected[0]);
            int second = roman.romanArabic(collected[2]);
            if ((first < 1 || first >10) || (second<1 || second >10)){
                throw new Exception("Out of bounds");
            } else {
            int toRoman = roman.calculateR(first,second,operand);
            String result = roman.arabicRoman(toRoman);
            System.out.println(result);
            }
// hello git again
        }
    }
}
class Calc {
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
            case "/" -> res = a / b;
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

