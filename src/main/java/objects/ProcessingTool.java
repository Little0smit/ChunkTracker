package objects;

//TODO make this class
public class ProcessingTool {
    Process[] processes;
    int chunkLocation;
    String processName;

    public ProcessingTool(Process[] processes, int chunkLocation) {
        this.processes = processes;
        this.chunkLocation = chunkLocation;
        this.processName = processName;
    }

    public Process[] getProcesses() {
        return processes;
    }

    public int getChunkLocation() {
        return chunkLocation;
    }

    public String getProcessName() {
        return processName;
    }
}
