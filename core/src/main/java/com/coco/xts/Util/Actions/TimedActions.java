package com.coco.xts.Util.Actions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimedActions {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;

    public void startAction(Runnable action, long delayInSeconds) {
        scheduledFuture = scheduler.scheduleAtFixedRate(action, 0, delayInSeconds, TimeUnit.SECONDS);
    }

    public void stopAction() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }
}
