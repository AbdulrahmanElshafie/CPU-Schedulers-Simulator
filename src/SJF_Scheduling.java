public class SJF_Scheduling extends Scheduler {
    private Process Current_process;

    public SJF_Scheduling() {

    }


    public void Shortest_Job_first_Scheduler(Process[] processes) {
        Set_Total_Burst(processes);
        int t = 0;
        while (Total_burst_time != 0) {
            Process p = Get_Shortest_Process(processes, t);

            if (Current_process == null && p != null) {
                Current_process = p;
                Execution_order.addElement(Current_process.getName());
                t += Context_Switch;
            } else if (Current_process != null && p != null) {
                if (Current_process.getBurst_time() > p.getBurst_time()) {
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