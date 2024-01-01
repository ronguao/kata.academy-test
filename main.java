import java.util.HashMap;
import java.util.Scanner;


class Calculator {
    static int num1;
    static int dif;
    static int num2;
    static int result;
    static String operator;
    static String resultStr;
    static String part1;
    static String part2;

    //Код ниже преобразует любое рисмское число в арабское число типа int
    static int romToInt(String roman) {
        HashMap<Character, Integer> romAndInt = new HashMap<>();
        romAndInt.put('I', 1);
        romAndInt.put('V', 5);
        romAndInt.put('X', 10);
        int a = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int curValue = romAndInt.get(roman.charAt(i));
            if (curValue < prevValue) {
                a -= curValue;
            } else {
                a += curValue;
            }
            prevValue = curValue;
        }
        return a;
    }
    //Код ниже разбивает заданную пользователем строчку на 3 строчки, где 1 строчка все что до первого пробела, вторая строчка - это сам знак и третья - это остаток после знака.
    //Так же проверяет, точно ли введено все верно.
    static void splitPrimer(String example) {
        String[] tokens = example.split(" ");
        if (tokens.length != 3) {
            System.out.println("Cтрока не является математической операцией");
            return;
        }
        part1 = tokens[0];
        operator = tokens[1];
        part2 = tokens[2];
    }
//Код ниже переводит строчки в числовое значение, если это возможно. Если невозможно выдает -1.
    static int convertToInt(String number) {
        if (number.matches("-?\\d+")) {
            return Integer.parseInt(number);
        } else return -1;
    }
    // Код ниже переводит обратно из арабского типа int в римское типа String
    static String intToRom(int a){
        int[] number = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romNum = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < number.length; i++) {
            while (a >= number[i]) {
                a -= number[i];
                b.append(romNum[i]);
            }
        }
        resultStr = b.toString();
        return resultStr;
    }
    // код ниже проверяет, были ли римские числа или числа больше 10 в задании и передает данные в арабском виде в калькулятор.
    static void calc(int c, int d, String e, String f) {
        if (c == -1 && d == -1) {
            c =  romToInt(e);
            d = romToInt(f);
            dif = 0;
            if (c > 10 || d > 10) {
                System.out.println("Заданные числа должны лежать в промежутке 1-10");
            } else {
                resInt(c, d, operator);
            }
        } else if  (c == -1 ^ d == -1) {
            System.out.println("используются одновременно разные системы счисления или присутствуют отрицательные/нецелые числа");
        }else if (c > 10 || d > 10) {
            System.out.println("Заданные числа должны лежать в промежутке 1-10");
        } else{
            dif = 1;
            resInt(c, d, operator);
        }
    }
    //Код ниже проверяет знак и занимается арифметикой.
    static void resInt(int a, int b, String operator)
    {
        switch (operator) {
            case "+":
                result = a + b;
                if (dif == 0){
                    intToRom(result);
                    System.out.println("Результат: " + resultStr);
                    break;
                } else {
                System.out.println("Результат: " + result);
                break;}
            case "-":
                result = a - b;
                if ( a<=b & dif == 0){
                    System.out.println("в римской системе нет отрицательных чисел и 0");
                    break;
                } else if (dif == 0) {
                    intToRom(result);
                    System.out.println("Результат: " + resultStr);
                    break;
                } else {
                    System.out.println("Результат: " + result);
                    break;
                }
            case "*":
                result = a * b;
                if (dif == 0){
                    intToRom(result);
                    System.out.println("Результат: " + resultStr);
                    break;
                } else {
                    System.out.println("Результат: " + result);
                    break;
                }
            case "/":
                if (b == 0) {
                    System.out.println("Деление на 0 невозможно");
                    break;
                } else if (dif == 0) {
                    result = a / b;
                    if ( a<=b & dif == 0) {
                        System.out.println("в римской системе нет отрицательных чисел и 0");
                    } else {
                        intToRom(result);
                        System.out.println("Результат: " + resultStr);
                        break;
                    }
                } else {
                    result = a / b;
                    System.out.println("Результат: " + result);
                    break;
                }

        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение (например 2 + 3):");
        String example = scanner.nextLine();
        splitPrimer(example);
        num1 = convertToInt(part1);
        num2 = convertToInt(part2);
        calc(num1, num2, part1, part2);
    }
}
