package api.hbm.util;

public class HBMUtil {

    /**
     * Does almost the same thing as LOWER_UNDERSCORE.to(UPPER_CAMEL, string), but it also removes underscores before
     * numbers.
     *
     * @param string Any string with ASCII Characters.
     * @return A string that is all lowercase, with underscores inserted before word/number boundaries.
     *      "depleted_plutonium_239" -> "depletedPlutonium239"
     */
    public static String lowerUnderscoreToUpperCamel(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '_')
                continue;
            if (i == 0 || string.charAt(i - 1) == '_') {
                result.append(Character.toUpperCase(string.charAt(i)));
            } else {
                result.append(string.charAt(i));
            }
        }
        return result.toString();
    }
}
