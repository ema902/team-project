package entities;

import java.time.LocalDateTime;

public class Session {
    private final int durationSec;
    private int remainingTimeSec;
    private final LocalDateTime completedDate;
    //private String sessionType;

    public Session(int durationSec, int remainingTimeSec, LocalDateTime date) {
        this.durationSec = durationSec;
        this.remainingTimeSec = remainingTimeSec;
        this.completedDate = date;
    }

    public int getDurationSec() {
        return this.durationSec;
    }

    public int getRemainingTimeSec() {
        return this.remainingTimeSec;
    }

    public LocalDateTime getCompletedDate() {
        return this.completedDate;
    }

}