package core.utils;

import java.util.concurrent.TimeUnit;

public class WaitingUtils {

    public static void sleep(int delayInMillis) {
        try {
            TimeUnit.MILLISECONDS.sleep(delayInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
