package net.terrafirmainfinity.core.util;

public class InfinityUtil {
    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String snakecaseToTitle(String snakeCase) {
        StringBuilder out = new StringBuilder();
        for (String part : snakeCase.split("_")) {
            if (part.isEmpty()) continue;
            if (out.length() > 0) out.append(' ');
            out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return out.toString();
    }

    public static String snakecaseToCamel(String input) {
        String str = snakecaseToTitle(input).replace(" ", "");
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}
