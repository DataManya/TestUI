package ru.seleniumtest.util;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleniumtest.core.Driver;
import ru.seleniumtest.config.TimeoutConfig;

import java.util.concurrent.Callable;


public class WaitUtil {

    private static final Logger log = LoggerFactory.getLogger(WaitUtil.class);

    private static final By UNLOCK = By.xpath(".//div[@class='blockUI blockOverlay ui-widget-overlay']");

    private WaitUtil() {
    }

    public static void delay(final Integer timeoutInMilliSeconds) {
        try {
            Thread.sleep(timeoutInMilliSeconds);
        } catch (Exception ex) {
            log.error("Ошибка ожидания потока: ", ex);
        }
    }

    public static void delay() {
        delay(TimeoutConfig.ANIMATION_DELAY);
    }

    private static boolean waitForCondition(Callable<Boolean> condition,
                                            final Integer sleepInMilliSeconds,
                                            final Integer delayBetweenRetries) {
        long start = System.currentTimeMillis();
        Boolean result;
        while (System.currentTimeMillis() - start <= sleepInMilliSeconds) {
            try {
                result = condition.call();
            } catch (Exception e) {
                result = false;
            }
            if (result) {
                return true;
            } else {
                delay(delayBetweenRetries);
            }
        }
        return false;
    }

    public static boolean waitForCondition(Callable<Boolean> condition) {
        return waitForCondition(condition, TimeoutConfig.DEFAULT_TIMEOUT);
    }

    public static boolean waitForCondition(Callable<Boolean> condition, final int sleepInMilliSeconds) {
        return waitForCondition(condition, sleepInMilliSeconds, TimeoutConfig.WAITER_HELPER_TIMEOUT);
    }

    public static void waitForUnlock(Driver driver) {
        if (driver.findElements(UNLOCK).isEmpty()) {
            log.info("Блокировки страницы нет.");
        } else {
            log.info("Блокировка страницы.");
            boolean blockUi = driver.findElements(UNLOCK).isEmpty();

            int time = 500;
            int countTime = 0;

            while (!blockUi) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    log.error("Ошибка ожидании разблокировки UI: ", ex);
                }

                countTime += time;

                if (countTime > time * 60) {
                    break;
                }
            }
        }
    }
}
