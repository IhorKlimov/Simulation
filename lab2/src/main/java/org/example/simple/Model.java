package org.example.simple;

public class Model {
    private double tnext;
    private double tcurr;
    private double tNextCreate, tNextDispose;
    private double delayCreate, delayProcess;
    private int numCreate, numProcess, failure;
    private int state, maxqueue, queue;
    private int nextEventType;

    public Model(double delay0, double delay1) {
        delayCreate = delay0;
        delayProcess = delay1;
        tnext = 0.0;
        tcurr = tnext;
        tNextCreate = tcurr;
        tNextDispose = Double.MAX_VALUE;
        maxqueue = 0;
    }

    public Model(double delay0, double delay1, int maxQ) {
        delayCreate = delay0;
        delayProcess = delay1;
        tnext = 0.0;
        tcurr = tnext;
        tNextCreate = tcurr;
        tNextDispose = Double.MAX_VALUE;
        maxqueue = maxQ;
    }

    public void simulate(double timeModeling) {
        while (tcurr < timeModeling) {
            tnext = tNextCreate;
            nextEventType = 0;
            if (tNextDispose < tnext) { // adjust back as jumped too far
                tnext = tNextDispose;
                nextEventType = 1;
            }
            tcurr = tnext;
            System.out.println(tcurr + " " + tnext + " " + tNextCreate + " " + tNextDispose);
            switch (nextEventType) {
                case 0:
                    event0();

                    break;
                case 1:
                    event1();
            }
            printInfo();
        }
        printStatistic();
    }

    public void printStatistic() {
        System.out.println(" numCreate= " + numCreate + " numProcess = " + numProcess + " failure = " + failure);
    }

    public void printInfo() {
        System.out.println(" t= " + tcurr + " state = " + state + "queue = " + queue);
    }

    public void event0() {
        // Create event
        System.out.println("Model.event0");
        tNextCreate = tcurr + getDelayOfCreate();
        numCreate++;
        if (state == 0) { // State is available
            state = 1;
            tNextDispose = tcurr + getDelayOfProcess();
        } else { // State is busy
            if (queue < maxqueue) {
                queue++;
            } else {
                failure++;
            }
        }
        System.out.println(tcurr + " " + tnext + " " + tNextCreate + " " + tNextDispose);


    }

    public void event1() {
        // Exit ?? Dispose ?? event
        System.out.println("Model.event1");
        tNextDispose = Double.MAX_VALUE;
        state = 0;
        if (queue > 0) {
            queue--;
            state = 1;
            tNextDispose = tcurr + getDelayOfProcess();
        }
        numProcess++;
    }

    private double getDelayOfCreate() {
        return FunRand.Exp(delayCreate);
    }

    private double getDelayOfProcess() {
        return FunRand.Exp(delayProcess);
    }
}