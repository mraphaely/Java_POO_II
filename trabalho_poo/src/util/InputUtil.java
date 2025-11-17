package util;

import java.util.Scanner;

public final class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    private InputUtil() {}

    public static String readText(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
}