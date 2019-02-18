package objects;

import java.util.ArrayList;

//TODO make this class
public class ProcessingTool {
    ArrayList<Process> processes;
    int chunkLocation;
    String processToolName;

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
