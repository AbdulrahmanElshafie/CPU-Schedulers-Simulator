import java.util.*;

public class RR_Scheduling extends Scheduler {


    public RR_Scheduling() {
        quantum_time = 0;
    }

    public Vector<Process> getReadyProcesses(Process[] processes, int time) {
        Vector<Process> ready_queue = new Vector<>();
        for (int i = 0; i < processes.length; ++i) {
            if (processes[i].getArrival_time() <= time && processes[i].getBurst_time() > 0) {
                ready_queue.add(processes[i]);
            }
        }
        return ready_queue;
    }

    public void Round_Robin_Scheduler(Process[] processes) {
        int t = 0;
        Process lastExecuted = null;
        Set_Total_Burst(processes);
        Vector<Process> ready_queue = new Vector<>(getReadyProcesses(processes, t));

        while (Total_burst_time != 0) {

            Vector<Process> checkReady = getReadyProcesses(processes, t);
            for (int j = 0; j < ready_queue.size(); j++) {
                for (int i = 0; i < checkReady.size(); ++i) {
                    if (checkReady.get(i) == ready_queue.get(j)) {
                        checkReady.remove(i);
                    }
                }
            }

            ready_queue.addAll(checkReady);

            if (lastExecuted != null) {
                ready_queue.remove(lastExecuted);
                ready_queue.addElement(lastExecuted);
            }

            Process p = ready_queue.firstElement();
            p.setBurst_time(p.getBurst_time() - quantum_time);

            t += quantum_time + Context_Switch;
            Total_burst_time -= quantum_time;
            Execution_order.addElement(p.getName());

            if (p.getBurst_time() <= 0) {
                if (p.getBurst_time() < 0) {
                    Total_burst_time += (p.getBurst_time() * -1);
                    t -= (p.getBurst_time() * -1);
                    p.setBurst_time(0);
                }
                p.setCompletion_time(t);
                p.setWaiting_time();
                p.setTurnaround_time();
                ready_queue.remove(ready_queue.firstElement());
                lastExecuted = null;
            } else {
                lastExecuted = p;

            }
        }
    }
}
