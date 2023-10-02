import java.util.HashSet;
import java.util.Set;

public class Process {
    enum Status {
        READY,
        WAITING,
        TERMINATED
    }

    private int pid;
    private String name;
    private Status state;
    private int timeSlice;
    private int timeRemaining;
    private Set files;

   
 public Process(int pid, String name, int timeRemaining)
 {
     this.pid = pid;
     this.name = name;
     state = Status.READY;
     timeSlice = 10;
     this.timeRemaining = timeRemaining;
     files = new HashSet<String>();
 }
public void openFile(String newFile)
{
    try
    {
        files.add(newFile);
    }catch (Exception e)
    {
        throw new IllegalArgumentException("That file isn't running");
    }

}
    public void closeFile(String newFile)
    {
        files.remove(newFile);
    }
public void run()
{
    timeRemaining = timeRemaining - timeSlice;
}
@Override public String toString()
{
    return "Process" + pid + " " + name + ": " + state + ", " + timeRemaining + " ms remaining, " + files.size() + " files open";
}
    public String getName() {
        return name;
    }

    public int getPid() {
        return pid;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getTimeSlice() {
        return timeSlice;
    }

    public Status getState()
    {
        return state;
    }

    public void setFiles(Set files) {
        this.files = files;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void setTimeSlice(int timeSlice) {
        this.timeSlice = timeSlice;
    }

    public Set getFiles() {
        return files;
    }







    //    // Example of how to use enum  (delete this)
//    public void doSomething(){
//        Status s = Status.READY;
//        switch(s) {
//            case READY:
//                // do something
//                break;
//            case WAITING:
//                // do something else
//                break;
//            case TERMINATED:
//                // something else
//                break;
//        }
//    }

}
