package databases;

import objects.Item;
import objects.Process;
import objects.ProcessingTool;

import java.util.ArrayList;
import java.util.HashMap;

//TODO make this class
public class ProcessingToolDB {
    private static ArrayList<Process> allProcesses = new ArrayList<Process>();

    public static  void registerProcess(Process process){ if (!allProcesses.contains(process)){ allProcesses.add(process); } }
}
