package core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static core.SLF4JLogger.error;

public class Utils {

    public static String timestamp(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static void sleep(int delayInMillis) {
        try {
            TimeUnit.MILLISECONDS.sleep(delayInMillis);
        } catch (InterruptedException e) {
            error("Exception in sleep occurred" + e.getMessage());
        }
    }

}
