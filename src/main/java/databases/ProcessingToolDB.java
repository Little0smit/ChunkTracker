package databases;

import objects.Item;
import objects.Process;
import objects.ProcessingTool;

import java.util.ArrayList;
import java.util.HashMap;

//TODO make this class
public class ProcessingToolDB {
    private static HashMap<String, ProcessingTool> allProcesses = new HashMap<String, ProcessingTool>();

    public static void registerProcess(ProcessingTool process){ if (!allProcesses.containsValue(process)){ allProcesses.put(process.getProcessName(), process); } }

    public static ProcessingTool getProcess(String processName){ return allProcesses.get(processName); }
}
