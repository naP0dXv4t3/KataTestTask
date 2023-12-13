import java.io.Serializable;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Integer dictionaryToArab(String Value) {
        Map<String, Integer> RomanToArab = new HashMap<String, Integer>();

        RomanToArab.put("", 0);
        RomanToArab.put("I", 1);
        RomanToArab.put("IV", 4);
        RomanToArab.put("V", 5);
        RomanToArab.put("IX", 9);
        RomanToArab.put("X", 10);

        return RomanToArab.get(Value);
    }
    static String dictionaryToRoman(String Value) {
        Map<String, String> ArabToRoman = new HashMap<String, String>();

        ArabToRoman.put("1","I");
        ArabToRoman.put("2","II");
        ArabToRoman.put("3","III");
        ArabToRoman.put("4","IV");
        ArabToRoman.put("5","V");
        ArabToRoman.put("6","VI");
        ArabToRoman.put("7","VII");
        ArabToRoman.put("8","VIII");
        ArabToRoman.put("9","IX");
        ArabToRoman.put("10","X");
        ArabToRoman.put("20","XX");
        ArabToRoman.put("30","XXX");
        ArabToRoman.put("40","XL");
        ArabToRoman.put("50","L");
        ArabToRoman.put("60","LX");
        ArabToRoman.put("70","LXX");
        ArabToRoman.put("80","LXXX");
        ArabToRoman.put("90","XC");
        ArabToRoman.put("100","C");

        return ArabToRoman.get(Value);
    }
    static public Serializable calc(String input) {
        int a = 0;
        int b = 0;
        int x =0;
        int y = 0;
        int result = 0;
        String s;
        String op;
        String[] strings = input.replace(" ", "").split("\\W");
        String[] operator = input.replace(" ", "").split("\\w");
        for (String string : operator) {
            int k = 0;
            if ("+".equals(string))
                k++;
            if (k < 1) {
                throw new ArithmeticException();
            }else{


        op = operator[operator.length - 1];
        try {
            a = Integer.parseInt(strings[0]);
            b = Integer.parseInt(strings[1]);
            return (operation(a, b, op));
        } catch (Exception e) {
            if (!strings[0].equals("IV") && !strings[0].equals("IX")) {
                char[] chars = strings[0].toCharArray();
                for (char aChar : chars) {
                    String Value = String.valueOf(aChar);
                    x += dictionaryToArab(Value);
                }
            }else{
                x = dictionaryToArab(strings[0]);
            }
            if (!strings[1].equals("IV") && !strings[1].equals("IX")) {
                char[] chars1 = strings[1].toCharArray();
                for (char aChar : chars1) {
                    String Value = String.valueOf(aChar);
                    y += dictionaryToArab(Value);
                }
            }else{
                y = dictionaryToArab(strings[1]);
            }
            result = operation(x, y, op);
            if (result < 0 ) {
                throw new ArithmeticException();
            }else {
                int units = result % 10;
                int dozens = result / 10;
                if (dozens == 0) {
                    return (dictionaryToRoman(String.valueOf(units)));
                } else if (units == 0) {
                    return (dictionaryToRoman(String.valueOf(dozens * 10)));
                }
                {
                    return (dictionaryToRoman(String.valueOf(dozens * 10)) + dictionaryToRoman(String.valueOf(units)));
                }
            }
        }
    }}
        return null;
    }

    static int operation(int a, int b, String op) {
        return switch (op) {
            case "*" -> a * b;
            case "+" -> a + b;
            case "-" -> a - b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        System.out.println(calc(sc.nextLine()));
    }
}