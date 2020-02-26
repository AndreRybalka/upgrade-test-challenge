package core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExrMatchSubstringUtil {

    public static String matchString(String pattern, String matcher){
        String result = "";
        Pattern patTern = Pattern.compile(pattern);
        Matcher matCher = patTern.matcher(matcher);
        if(matCher.find()){
            result = matCher.group();
        }
        return result;
    }

    public static String replaceUselessString(String pattern, String matcher){
        String result = "";
        String stringWithoutNewLine = matcher.replace("\n", " ");
        Pattern patTern = Pattern.compile(pattern);
        Matcher matCher = patTern.matcher(stringWithoutNewLine);
        if(matCher.find()){
            result = matCher.group();
        }
        return stringWithoutNewLine.replace(result, "");
    }
}

