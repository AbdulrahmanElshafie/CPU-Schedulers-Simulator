public class Process {
    public Process() {

    }

    private String name;
    private int arrival_time;
    private int burst_time;
    private int burst_timeCpy;
    private int priority;
    private int waiting_time;
    private int turnaround_time;
    private int completion_time;
    private int quantum_time;


    public int getQuantum_time() {
        return quantum_time;
    }

    public void setQuantum_time(int quantum_time) {
        this.quantum_time = quantum_time;
    }

    public int getBurst_timeCpy() {
        return burst_timeCpy;
    }


    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time() {
        this.waiting_time = completion_time - burst_timeCpy - arrival_time;
    }

    public int getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(int c) {
        this.completion_time = c;
    }

    public int getTurnaround_time() {
        return turnaround_time;
    }

    public void setTurnaround_time() {
        this.turnaround_time = waiting_time + burst_timeCpy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int a) {
        this.arrival_time = a;
    }

    public int getBurst_time() {
        return burst_time;
    }

    public void setBurst_time(int b) {
        this.burst_time = b;
    }

    public void setBurst_timeCpy(int b) {
        this.burst_timeCpy = b;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
