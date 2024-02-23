import java.util.Vector;

public class AG extends Scheduler {

    private final Vector<Vector<Integer>> History_update;

    AG() {
        History_update = new Vector<Vector<Integer>>();
    }

    public Vector<Vector<Integer>> getHistory_update() {
        return History_update;
    }

    public void setHistory_update(Process[] processes) {
        Vector<Integer> CurrentQuantum = new Vector<Integer>();

        for (int i = 0; i < processes.length; i++) {
            CurrentQuantum.addElement(processes[i].getQuantum_time());
        }

        History_update.addElement(CurrentQuantum);
    }

    public void AG_Scheduler(Process[] processes) {
        // add the processes in the execution order vector, quantum update history
        Set_Total_Burst(processes);
        setHistory_update(processes);
        int t = 0;
        Process p = null;
        while (Total_burst_time != 0) {
            if (p == null) {
                p = Get_First_come_process(processes, t);
            }
            int QCpy = p.getQuantum_time();
            p.setBurst_time(p.getBurst_time() - Math.ceilDiv(p.getQuantum_time(), 4));
            Total_burst_time -= Math.ceilDiv(p.getQuantum_time(), 4);
            t += Math.ceilDiv(p.getQuantum_time(), 4);
            p.setQuantum_time(p.getQuantum_time() - Math.ceilDiv(p.getQuantum_time(), 4));
            int Cnt = 1;

            Execution_order.addElement(p.getName());

            if (p.getQuantum_time() == 0) { //Quantum = 0 && burst time > 0
                p.setQuantum_time(QCpy + 2);
                setHistory_update(processes);
                continue;
            }

            Process p2 = Get_Highest_Priority_Process(processes, t);
            Process p3 = null;
            if (p == p2) {

                p.setBurst_time(p.getBurst_time() - Math.ceilDiv(p.getQuantum_time(), 4));
                Total_burst_time -= Math.ceilDiv(p.getQuantum_time(), 4);
                t += Math.ceilDiv(p.getQuantum_time(), 4);
                p.setQuantum_time(p.getQuantum_time() - Math.ceilDiv(p.getQuantum_time(), 4));
                Cnt++;

                if (p.getQuantum_time() == 0) { //Quantum = 0 && burst time > 0
                    p.setQuantum_time(QCpy + 2);
                    setHistory_update(processes);
                    continue;
                }

                p3 = Get_Shortest_Process(processes, t);
                if (p == p3) {
                    while (p.getBurst_time() != 0 && p == p3) {
                        p.setBurst_time(p.getBurst_time() - 1);
                        t++;
                        Total_burst_time--;
                        p.setQuantum_time(p.getQuantum_time() - 1);
                        p3 = Get_Shortest_Process(processes, t);
                    }
                }
            }
            //scenario 4
            if (p.getBurst_time() <= 0) {
                if (p.getBurst_time() < 0) {
                    Total_burst_time += (p.getBurst_time() * -1);
                    t -= (p.getBurst_time() * -1);
                    p.setBurst_time(0);
                }
                p.setCompletion_time(t);
                p.setWaiting_time();
                p.setTurnaround_time();
                p.setQuantum_time(0);
                p = null;
                setHistory_update(processes);
            } else {
                //scenario 1
                if (p.getQuantum_time() == 0) { //Quantum = 0 && burst time > 0
                    p.setQuantum_time(QCpy + 2);
                    setHistory_update(processes);
                    continue;
                }
                if (Cnt == 1) { //scenario 2
                    p.setQuantum_time(QCpy + Math.ceilDiv(p.getQuantum_time(), 2));
                    p = p2;
                    setHistory_update(processes);
                } else if (Cnt == 2) {
                    p.setQuantum_time(QCpy + p.getQuantum_time());
                    p = p3;
                    setHistory_update(processes);
                }
            }
        }
    }
}