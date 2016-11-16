package debug.stopwatch;

/**
 * Created by Alexander on 15.11.2016.
 */
public class StopWatch {
    private long startTime;
    private long periodStartTime;
    private final String tab;
    private final boolean active;
    private long sumInterval = 0;

    public StopWatch() {
        this(true, 0);
    }

    public StopWatch(boolean active) {
        this(active, 0);
    }

    public StopWatch(int level) {
        this(true, level);
    }

    public StopWatch(boolean active, int level) {
        this.active = active;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        tab = sb.toString();
    }

    public void start() {
        if (active) {
            System.out.println(tab + "START");
        }
        periodStartTime = startTime = System.nanoTime();
    }

    public void point(String label) {
        long currentTime = System.nanoTime();
        if (active) {
            long periodInterval = currentTime - periodStartTime;
            sumInterval += periodInterval;
            System.out.println(tab + label + " " + String.valueOf(periodInterval));
        }
        periodStartTime = System.nanoTime();
    }

    public void stop() {
        long currentTime = System.nanoTime();
        if (active) {
            long allTime = currentTime - startTime;
            System.out.println(tab + "STOP " + String.valueOf(allTime) + " (overhead: " + String.valueOf(allTime - sumInterval) + ")");
        }
    }
}
