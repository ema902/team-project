package interface_adapters.home;

/**
 * The State information representing the Home Page.
 */
public class HomeState {
    private String timerDisplay = "00:00";
    private boolean timerRunning = false;
    private String sessionType = "focus";

    /**
     * Returns the timer's current display value.
     * @return the remaining time formatted as a string
     */
    public String getTimerDisplay() { return timerDisplay; }

    /**
     * Sets the timer's display value.
     * @param timerDisplay the remaining time formatted as a string
     */
    public void setTimerDisplay(String timerDisplay) {this.timerDisplay = timerDisplay; }

    /**
     * Returns whether the timer is currently running.
     * @return true if the timer is running, false otherwise
     */
    public boolean isTimerRunning() {
        return timerRunning;
    }

    /**
     * Updates the timer's running state.
     * @param timerRunning true if the timer is running, false otherwise
     */
    public void setTimerRunning(boolean timerRunning) {
        this.timerRunning = timerRunning;
    }

    /**
     * Returns the current timer's session type.
     * @return "focus" or "break"
     */
    public String getSessionType() {
        return sessionType;
    }

    /**
     * Updates the timer's session type.
     * @param sessionType "focus" or "break"
     */
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
