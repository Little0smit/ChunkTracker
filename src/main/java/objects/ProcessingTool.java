package objects;

import constants.Constant;

import java.util.ArrayList;

public class ProcessingTool extends NamedThing {
    //TODO: Rename to SkillingLocation
    private String processToolName = "";
    private ArrayList<String> processesNames = new ArrayList<String>();

    public String getName() { return processToolName; }

    public ArrayList<String> getProcessesNames() { return processesNames; }

    public ArrayList<Process> getProcesses(){
        ArrayList<Process> al = new ArrayList<Process>();
        for (String string : processesNames){
            al.add(Constant.PROCESS_DATABASE.getElement(string));
        }
        return al;
    }
}
