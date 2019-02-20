package objects;

import java.util.ArrayList;

public class ProcessingTool extends NamedThing {
    private ArrayList<Process> processes;
    private int chunkLocation;
    private String processToolName;

    public ProcessingTool(ArrayList<Process> processes, int chunkLocation, String processToolName) {
        this.processes = processes;
        this.chunkLocation = chunkLocation;
        this.processToolName = processToolName;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public int getChunkLocation() {
        return chunkLocation;
    }

    public String getProcessName() {
        return processToolName;
    }
}
