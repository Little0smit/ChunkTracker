package objects;

import java.util.ArrayList;

public class ProcessingTool extends NamedThing {
    private ArrayList<Process> processes = new ArrayList<Process>();
    private String processToolName = "";
    private ArrayList<String> processesNames = new ArrayList<String>();

    /*public ProcessingTool(ArrayList<Process> processes, int chunkLocation, String processToolName) {
        this.processes = processes;
        this.chunkLocation = chunkLocation;
        this.processToolName = processToolName;
    }*/

    public ArrayList<Process> getProcesses() { return processes; }

    public String getName() { return processToolName; }

    public ArrayList<String> getProcessesNames() { return processesNames; }
}
