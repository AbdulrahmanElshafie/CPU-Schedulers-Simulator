import java.util.Vector;

public abstract class Scheduler {
    protected double Total_burst_time;
    protected Vector<String> Execution_order;
    protected int Context_Switch;
    protected int quantum_time;

    public void setQuantum_time(int quantum_time) {
        this.quantum_time = quantum_time;
    }


    Scheduler() {
        Total_burst_time = 0;
        Context_Switch = 0;
        quantum_time = 0;
        Execution_order = new Vector<String>();
    }

    public Vector<String> getExecution_order() {
        return Execution_order;
    }

    public double getAvg_Waiting_time(Process[] processes) {
        double sum = 0;
        for (int i = 0; i < processes.length; ++i) {
            sum += processes[i].getWaiting_time();
        }

        return (sum / processes.length);
    }

    public double getAvg_Turnaround_time(Process[] processes) {
        double sum = 0;
        for (int i = 0; i < processes.length; ++i) {
            sum += processes[i].getTurnaround_time();
        }

        return (sum / processes.length);
    }

    public void Set_Total_Burst(Process[] processes) {
        for (int i = 0; i < processes.length; ++i) {
            Total_burst_time += processes[i].getBurst_time();
        }
    }

    public void setContext_Switch(int s) {
        Context_Switch = s;
    }

    public Process Get_Highest_Priority_Process(Process[] processes, int time) {
        double highest = 1e9;
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getPriority() < highest && processes[i].getBurst_time() != 0) {
                    highest = processes[i].getPriority();
                }
            }
        }
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getPriority() == highest && processes[i].getBurst_time() != 0) {
                    return processes[i];
                }
            }
        }
        return null;
    }

    public Process Get_Shortest_Process(Process[] processes, int time) {
        double min = 1e9;
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getBurst_time() < min && processes[i].getBurst_time() > 0) {
                    min = processes[i].getBurst_time();
                }
            }
        }
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getBurst_time() == min) {
                    return processes[i];
                }
            }
        }
        return null;
    }

    public Process Get_First_come_process(Process[] processes, int time) {
        double min = 1e9;
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time && processes[i].getArrival_time() < min && processes[i].getBurst_time() > 0) {
                min = processes[i].getArrival_time();
            }
        }

        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time && processes[i].getArrival_time() == min && processes[i].getBurst_time() > 0) {
                return processes[i];
            }
        }
        return null;
    }
}
