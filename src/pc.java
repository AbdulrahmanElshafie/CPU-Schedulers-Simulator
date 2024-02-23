import java.util.Scanner;

class pc {

    public static void Output(Process[] processes, Scheduler scheduler, int processes_number) {
        System.out.println("Execution Order");
        System.out.println(scheduler.getExecution_order());

        for (int i = 0; i < processes_number; ++i) {
            System.out.print(processes[i].getName());
            System.out.print(", Waiting Time: ");
            System.out.print(processes[i].getWaiting_time());
            System.out.print(", Turnaround Time: ");
            System.out.print(processes[i].getTurnaround_time());
            System.out.println();
        }
        System.out.println();

        System.out.print("Avg. Waiting Time ");
        System.out.print(scheduler.getAvg_Waiting_time(processes));
        System.out.println();

        System.out.print("Avg. Turnaround Time ");
        System.out.print(scheduler.getAvg_Turnaround_time(processes));
        System.out.println();
    }

    public static void ShortestJobFirst(int context_switching, int processes_number, Process[] processes) {
        SJF_Scheduling scheduler = new SJF_Scheduling();
        scheduler.setContext_Switch(context_switching);

        scheduler.Shortest_Job_first_Scheduler(processes);

        Output(processes, scheduler, processes_number);
    }

    public static void RoundRobin(int context_switching, int processes_number, int quantum_time, Process[] processes) {
        RR_Scheduling scheduler = new RR_Scheduling();
        scheduler.setContext_Switch(context_switching);
        scheduler.setQuantum_time(quantum_time);

        scheduler.Round_Robin_Scheduler(processes);

        Output(processes, scheduler, processes_number);
    }

    public static void PriorityScheduling(int context_switching, int processes_number, int quantum_time, Process[] processes) {
        Priority_Scheduling scheduler = new Priority_Scheduling();
        scheduler.setContext_Switch(context_switching);
        scheduler.setQuantum_time(quantum_time);

        scheduler.Priority_Scheduler(processes);

        Output(processes, scheduler, processes_number);
    }

    public static void AG(int processes_number, Process[] processes) {
        Scanner input = new Scanner(System.in);

        AG scheduler = new AG();


        System.out.println("Enter the quantum of each process");
        for (int i = 0; i < processes_number; i++) {
            processes[i].setQuantum_time(input.nextInt());
        }

        scheduler.AG_Scheduler(processes);

        Output(processes, scheduler, processes_number);

        System.out.println("Quantum time history update per process");
        for (int i = 0; i < scheduler.getHistory_update().size(); i++) {
            System.out.print("Quantum ");
            System.out.println(scheduler.getHistory_update().get(i));
        }
    }

    public static void main(String[] args) {
        int processes_number, time_quantum, context_switching;
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter processes' number, RR time quantum, and context switching");
        processes_number = input.nextInt();
        time_quantum = input.nextInt();
        context_switching = input.nextInt();
        Process[] processes1 = new Process[processes_number], processes2 = new Process[processes_number],
                processes3 = new Process[processes_number], processes4 = new Process[processes_number];

        for (int i = 0; i < processes_number; ++i) {
            //System.out.println("Enter process name, arrival, burst, priority");
            input.nextLine();
            processes1[i] = new Process();
            processes1[i].setName(input.nextLine());
            processes1[i].setArrival_time(input.nextInt());
            processes1[i].setBurst_time(input.nextInt());
            processes1[i].setBurst_timeCpy(processes1[i].getBurst_time());
            processes1[i].setPriority(input.nextInt());

            processes2[i] = new Process();
            processes2[i].setName(processes1[i].getName());
            processes2[i].setArrival_time(processes1[i].getArrival_time());
            processes2[i].setBurst_time(processes1[i].getBurst_time());
            processes2[i].setBurst_timeCpy(processes1[i].getBurst_time());
            processes2[i].setPriority(processes1[i].getPriority());

            processes3[i] = new Process();
            processes3[i].setName(processes1[i].getName());
            processes3[i].setArrival_time(processes1[i].getArrival_time());
            processes3[i].setBurst_time(processes1[i].getBurst_time());
            processes3[i].setBurst_timeCpy(processes1[i].getBurst_time());
            processes3[i].setPriority(processes1[i].getPriority());

            processes4[i] = new Process();
            processes4[i].setName(processes1[i].getName());
            processes4[i].setArrival_time(processes1[i].getArrival_time());
            processes4[i].setBurst_time(processes1[i].getBurst_time());
            processes4[i].setBurst_timeCpy(processes1[i].getBurst_time());
            processes4[i].setPriority(processes1[i].getPriority());
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Shortest Job First");
        ShortestJobFirst(context_switching, processes_number, processes1);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Round Robin");
        RoundRobin(context_switching, processes_number, time_quantum, processes2);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Priority Scheduling");
        PriorityScheduling(context_switching, processes_number, time_quantum, processes3);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("AG Scheduling");
        AG(processes_number, processes4);

    }
}
