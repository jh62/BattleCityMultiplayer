package com.battlecity.main.events;

import com.badlogic.gdx.*;
import com.battlecity.main.global.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author JH62
 */
public class TimerManager {

    final ScheduledThreadPoolExecutor pool;

    public TimerManager() {
        pool = new ScheduledThreadPoolExecutor(1);
        pool.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        pool.setRemoveOnCancelPolicy(true);
    }

    public void stop() {
        try {
            pool.shutdownNow();
            pool.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManager.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            pool.purge();
        }
    }

    public boolean isShutdown() {
        return pool.isShutdown();
    }

    /**
     * Fires a one-time action after the given delay.
     *
     * @param task
     * @param delay - in milliseconds
     */
    public void scheduleTask(Task task, long delay) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(task);
            }
        };

        ScheduledFuture s = pool.schedule(r, delay, TimeUnit.MILLISECONDS);
        task.start(s);
    }

    /**
     * Fires a repeating action after the given delay and with the specified interval after that.
     *
     * @param task
     * @param delay - in milliseconds
     * @param interval - in milliseconds
     */
    public void scheduleTask(Task task, long delay, long interval) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(task);
            }
        };

        ScheduledFuture s = pool.scheduleAtFixedRate(r, delay, interval,
                TimeUnit.MILLISECONDS);
        task.start(s);
    }

    public abstract static class Task implements Runnable {

        private ScheduledFuture<Task> sf;

        public Task() {

        }

        void start(ScheduledFuture<Task> sf) {
            this.sf = sf;
        }

        public void cancel() {
            sf.cancel(true);
        }

        public boolean isRunning() {
            return sf != null && !sf.isDone();
        }
    }
}
