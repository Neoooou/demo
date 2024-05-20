package com.example.alor.interview;

import java.util.*;

/**
 * @author neo.zr
 * @since 2023/8/12
 */

public class Honor {

    private static int containByRolling(String str, String sub) {
        if (str == null || str.length() < 1 || sub == null || sub.length() < 1 || sub.length() > str.length()) {
            return 0;
        }

        Map<Character, Integer> strFreq = charFreq(str);
        Map<Character, Integer> subFreq = charFreq(sub);
        for (Character c : subFreq.keySet()) {
            if (!strFreq.containsKey(c) || subFreq.get(c) > strFreq.get(c)) {
                return 0;
            }
        }

        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) - sub.charAt(0) == 0) {
                // 首字母匹配，开始其余字母匹配
                if (check(str, i, sub) || checkReverse(str, i, sub)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private static Map<Character, Integer> charFreq(String str) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            freq.compute(str.charAt(i), (k, v) -> v == null ? 1 : 1 + v);
        }
        return freq;
    }

    /**
     * 顺时针匹配
     */
    private static boolean check(String str, int idx, String sub) {
        for (int j = 1; j < sub.length(); ++j) {
            char strChar = str.charAt((idx + 1) % str.length());
            if (sub.charAt(j) - strChar != 0) {
                return false;
            }
            idx++;
        }
        return true;
    }

    /**
     * 逆时针匹配
     */
    private static boolean checkReverse(String str, int idx, String sub) {
        int move;
        for (int j = 1; j < sub.length(); ++j) {
            move = idx - 1 < 0 ? idx - 1 + str.length() : idx - 1;
            char strChar = str.charAt(move);
            if (sub.charAt(j) - strChar != 0) {
                return false;
            }
            idx--;
        }
        return true;
    }


    final static int[] DAYS_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static int dayOfYear(int year, int month, int day) {
        if (year < 1 || month < 1 || day < 1) {
            throw new IllegalArgumentException("Invalid parameters, year, month or day requires to be positive");
        }
        if (month > 12 || day > 31) {
            throw new IllegalArgumentException("Invalid parameters, invalid month or day, month in [1, 12], day in [1, 31] ");
        }

        int ans = 0;
        for (int i = 0; i < month - 1; i++) {
            if (i == 1) {
                // 二月
                ans += isRunYear(year) ? 29 : 28;
            } else {
                ans += DAYS_OF_MONTH[i];
            }
        }
        return ans + day;
    }


    public static boolean isRunYear(int year) {
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }


    final static String SEPARATOR_COMMAND = "\\|";

    public static void main(String[] args) {
        String s = "1 i first line|1 a second line|2 r replace a line";
        simpleCommand(s);
    }
    private static void simpleCommand(String inputLine) {
        String[] commands = inputLine.split(SEPARATOR_COMMAND);
        List<String> out = new ArrayList<String>();
        boolean success = true;
        for (String command : commands) {
            try{
                executeSingleCommand(command, out);
            }catch (Exception e){
                System.out.println("error");
                success = false;
                break;
            }
        }
        if(success){
            out.forEach(e -> System.out.println(e));
        }
    }

    private static void executeSingleCommand(String singleCommand, List<String> out) throws Exception {
        Command command = Command.ofKey(singleCommand.substring(2,3));
        if(command == null){
            throw new IllegalArgumentException("Invalid Single Command " + singleCommand);
        }
        int row  = Integer.parseInt(singleCommand.substring(0,1));
        switch (command) {
            case APPEND:
                out.add(row, singleCommand.substring(4));
                break;
            case INSERT:
                out.add(row - 1, singleCommand.substring(4));
                break;
            case REPLACE:
                out.set(row-1, singleCommand.substring(4));
                break;
            case DELETE:
                out.remove(row-1);
                break;
            default:
                // do nothing
        }
    }

    enum Command {
        APPEND("a"),
        INSERT("i"),
        REPLACE("r"),
        DELETE("d");
        String key;

        Command(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Command ofKey(String key) {
            for (Command command : values()) {
                if (command.getKey().equals(key)) {
                    return command;
                }
            }
            return null;
        }
    }

}
