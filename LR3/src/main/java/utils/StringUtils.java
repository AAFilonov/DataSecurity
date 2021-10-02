package utils;

import java.util.*;

public class StringUtils {
    public static  String removeDuplicates(String arg){
        char[] chars = arg.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }
}
