package objects;

//TODO make this class
public class ProcessingTool {
    Process[] processes;
    int chunkLocation;

    public ProcessingTool(Process[] processes, int chunkLocation) {
        this.processes = processes;
        this.chunkLocation = chunkLocation;
    }

    public Process[] getProcesses() {
        //TODO
        return processes;
    }

    public int getChunkLocation() {
        return chunkLocation;
    }
}
