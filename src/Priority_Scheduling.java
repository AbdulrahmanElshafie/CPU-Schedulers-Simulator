public class Priority_Scheduling extends Scheduler {

    public Priority_Scheduling() {

    }

    private Process Current_process;

    public void Starvation_Solution(Process[] processes, int time) {
        double lowest = -1e9;
        for (int i = 0; i < processes.length; ++i) {
            int waiting_time = time - (processes[i].getBurst_timeCpy() - processes[i].getBurst_time()) - processes[i].getArrival_time();
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getPriority() > lowest && processes[i].getBurst_time() != 0 && waiting_time >= quantum_time) {
                    lowest = processes[i].getPriority();
                }
            }
        }
        for (int i = 0; i < processes.length; ++i) {
            int waiting_time = time - (processes[i].getBurst_timeCpy() - processes[i].getBurst_time()) - processes[i].getArrival_time();
            if (processes[i].getArrival_time() <= time) {
                if (processes[i].getPriority() == lowest && waiting_time >= quantum_time) {
                    processes[i].setPriority(processes[i].getPriority() - 1);
                }
            }
        }
    }


    public void Priority_Scheduler(Process[] processes) {
        Set_Total_Burst(processes);
        int t = 0;
        while (Total_burst_time != 0) {

            if (t % quantum_time == 0 && t != 0) {
                Starvation_Solution(processes, t);
            }

            Process p = Get_Highest_Priority_Process(processes, t);

            if (Current_process == null && p != null) {
                Current_process = p;
                Execution_order.addElement(Current_process.getName());
                t += Context_Switch;
            } else if (Current_process != null && p != null) {
                if (Current_process.getPriority() > p.getPriority()) {
                    Current_process = p;
                    Execution_order.addElement(Current_process.getName());
                    t += Context_Switch;
                }
            }
            t++;
            if (Current_process != null) {
                Current_process.setBurst_time(Current_process.getBurst_time() - 1);
                if (Current_process.getBurst_time() == 0) {
                    Current_process.setCompletion_time(t);
                    Current_process.setWaiting_time();
                    Current_process.setTurnaround_time();
                    Current_process = null;
                }
                Total_burst_time--;
            }
        }
    }
}
