package net.terrafirmainfinity.core.util;

public class InfinityUtil {
    public static String capitalize(String str) {
        StringBuilder out = new StringBuilder();
        out.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
        return out.toString();
    }

    private static String snakecaseToTitle(String snakeCase) {
        StringBuilder out = new StringBuilder();
        for (String part : snakeCase.split("_")) {
            if (part.isEmpty()) continue;
            if (out.length() > 0) out.append(' ');
            out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return out.toString();
    }
}
