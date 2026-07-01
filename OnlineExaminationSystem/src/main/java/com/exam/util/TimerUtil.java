package com.exam.util;

import javax.swing.Timer;
import java.time.Duration;

public final class TimerUtil {
    private TimerUtil() {
    }

    public static String format(Duration duration) {
        long totalSeconds = Math.max(0, duration.toSeconds());
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static Timer countdown(Duration duration, TickHandler tickHandler, Runnable onComplete) {
        final long[] remainingSeconds = {duration.toSeconds()};
        Timer timer = new Timer(1000, event -> {
            remainingSeconds[0]--;
            tickHandler.onTick(Duration.ofSeconds(Math.max(0, remainingSeconds[0])));
            if (remainingSeconds[0] <= 0) {
                ((Timer) event.getSource()).stop();
                onComplete.run();
            }
        });
        tickHandler.onTick(Duration.ofSeconds(remainingSeconds[0]));
        return timer;
    }

    public interface TickHandler {
        void onTick(Duration remaining);
    }
}
