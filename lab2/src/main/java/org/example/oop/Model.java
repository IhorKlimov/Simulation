package org.example.oop;

import java.util.ArrayList;

public class Model {
    private ArrayList<Element> list = new ArrayList<>();
    double tnext, tcurr;
    int event;

    public Model(ArrayList<Element> elements) {
        list = elements;
        tnext = 0.0;
        event = 0;
        tcurr = tnext;
    }

    public void simulate(double time) throws Exception {
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();
                }
            }
            System.out.println("\nIt's time for event in " + list.get(event).getName() + ", time = " + tnext);

            for (Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Element e : list) {
                e.setTcurr(tcurr);
            }
            list.get(event).outAct();
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }

            printInfo();
        }
        printResult();
    }

    public void printInfo() {
        for (Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        int numOfTasks = 0;
        double failureProbability = 0;
        for (Element e : list) {
            if (e instanceof Create) {
                numOfTasks = e.getQuantity();
            }
        }

        for (Element e : list) {
            e.printResult();
            if (e instanceof Process) {
                Process p = (Process) e;
                int numOfNotProcessed = numOfTasks - p.getQuantity();
                double meanQueue = p.getMeanQueue() / tcurr;
                failureProbability += p.getFailure() / (double) p.getQuantity();

                System.out.println(
                        "mean length of queue = " + p.getMeanQueue() / tcurr +
                                "\nmean load = " + p.getMeanLoad() / tcurr +
                                "\nfailure probability = " + p.getFailure() / (double) p.getQuantity());
                System.out.println("Number of non-processed tasks = " + numOfNotProcessed);
                System.out.println("Mean length of queue = " + (meanQueue / (list.size() - 1)));
                System.out.println("------------------------------------");
            }
        }

        System.out.println("Total failure probability = " + (failureProbability / (list.size() - 1)));
    }
}
