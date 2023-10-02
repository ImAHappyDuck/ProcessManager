
public class ProcessManager {
  //  private static int nextAvailableId; // increments every time a process is created
   private ReadyQueue rq;
   private static int nextPid;
    

    // there should be only one ProcessManager object, so we will use a singleton
 private static ProcessManager manager = null;


    // as part of the singleton pattern, the constructor is private
    private ProcessManager() {
nextPid = 1;
rq = new ReadyQueue();


    }

    // as part of the singleton pattern, create this accessor which will also be the
    // only way that a new ProcessManager can be created

    public static ProcessManager getInstance()
    {
        if(manager == null)
        {
            manager = new ProcessManager();
        }
        return manager;

    }
private int getNextId()
    {
       if(nextPid == 1)
       {
           nextPid++;
           return 1;
       }
       nextPid++;
       return nextPid-1;

    }
    public Process createProcess (String name, int timeRemaining)
    {
        Process p = new Process(getNextId(),name,timeRemaining);
        rq.addProcess(p);
        return p;
    }
    public void process(int time)
    {
        int agg = 0;

        while (agg < time)
        {

            if (rq.getFirst().getState() == Process.Status.TERMINATED) {
                try {
                    rq.removeProcess();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            if (rq.getFirst().getState() == Process.Status.WAITING) {
                rq.contextSwitch();
            }
            if (rq.getFirst().getState() == Process.Status.READY) {
                agg = rq.getFirst().getTimeSlice() + agg;
                if(rq.getFirst().getTimeRemaining() -rq.getFirst().getTimeSlice()<=0)
                {
                    try {
                        rq.removeProcess();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    rq.getFirst().setTimeRemaining(rq.getFirst().getTimeRemaining()-rq.getFirst().getTimeSlice());
                    rq.contextSwitch();
                }

            }
        }
    }

    public void shortPrint()
    {
        String gb = "[ ";
        for(int i = 0; i < rq.getSize(); i ++)
        {
           gb = gb + rq.getFirst().getPid() + ", ";
            rq.contextSwitch();
        }
        gb = gb +"]";
        System.out.println(gb);
        System.out.println();
    }
    public void fullPrint()
    {
        System.out.println(rq.toString());
        System.out.println();
    }


    
    // example code showing enum use, you can delete this
//    public static void main(String[] args) {
//        // example of how to access the State enum values in Process
//        // TODO: remove this method
//        Process.Status s = Process.Status.READY;
//        if (s == Process.Status.READY) {
//            System.out.println("ready");
//        }
//    }
    }
