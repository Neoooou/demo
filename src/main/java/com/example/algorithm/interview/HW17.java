package com.example.algorithm.interview;

import java.util.LinkedList;
import java.util.List;

/**
 * @author neo.zr
 * @since 2024/3/15
 */

public class HW17 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        move(in.nextLine());
        Point point = new Point(1, 1);

        point.z = 0;
        point.plusZ();

        System.out.println(point);
    }

    final static String SEMICOLON = ";";

    final static String COMMA = ",";

    private static void move(String commandStr) {
        move(parseMultiCommands(commandStr));
    }

    /**
     * 描述
     * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
     * <p>
     * 输入：
     * <p>
     * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
     * <p>
     * 坐标之间以;分隔。
     * <p>
     * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
     */
    private static void move(List<Command> commandList) {
        Point point = new Point(0, 0);
        for (Command command : commandList) {
            point.executeCommand(command);
//            System.out.println(point);
        }
        System.out.println(point);
    }

    private static List<Command> parseMultiCommands(String commandStr) {
        List<Command> commands = new LinkedList<>();
        for (String str : commandStr.split(SEMICOLON)) {
            try {
                commands.add(parseSingleCommand(str));
            } catch (Exception e) {
                // ignore
            }
        }

        return commands;
    }

    private static Command parseSingleCommand(String commandStr) throws Exception {
        Direction direction = Direction.valueOf(commandStr.substring(0, 1));
        if (direction == null) {
            throw new IllegalArgumentException("Invalid Command Input");
        }
        int distance = Integer.parseInt(commandStr.substring(1));
        if (distance > 99 || distance < 0) {
            throw new IllegalArgumentException("Invalid Command Input");
        }
        return new Command(direction, distance);
    }

    static class Point {
        Integer x;
        Integer y;
        int z;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void plusZ(){
            z++;
        }
        public void executeCommand(Command command) {
            switch (command.getDirection()) {
                case A:
                    x = x - command.getDistance();
                    break;
                case D:
                    x = x + command.getDistance();
                    break;
                case W:
                    y = y + command.getDistance();
                    break;
                case S:
                    y = y - command.getDistance();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Command");

            }
        }

        @Override
        public String toString() {
            return this.x + COMMA + this.y + COMMA + this.z;
        }

    }

    static class Command {
        private Direction direction;
        private int distance;

        Command(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }

        public Direction getDirection() {
            return this.direction;
        }

        public int getDistance() {
            return this.distance;
        }

    }

    enum Direction {
        A("Left"),
        S("Downward"),
        D("Right"),
        W("Upward");

        String desc;

        Direction(String desc) {
            this.desc = desc;
        }

    }
}
