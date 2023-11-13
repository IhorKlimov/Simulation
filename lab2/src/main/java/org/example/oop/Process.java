package org.example.oop;

import java.util.List;

public class Process extends Element {
    private final List<Device> devices;
    private int queue, maxqueue, failure;
    private double meanQueue;
    private double meanLoad;

    public Process(double delay, List<Device> devices) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
        setTnext(Double.MAX_VALUE);
        this.devices = devices;
    }

    @Override
    public void inAct() {
        if (super.getState() == 0) {
            for (Device device : devices) {
                if (device.getState() == 0) {
                    device.onInAct();
                    device.setTnext(super.getTcurr() + super.getDelay());
                    break;
                }
            }

            boolean hasAvailableDevices = false;
            for (Device device : devices) {
                if (device.getState() == 0) {
                    hasAvailableDevices = true;
                    break;
                }
            }

            if (!hasAvailableDevices) {
                super.setState(1);
            }

            double earliestTime = Double.MAX_VALUE;
            for (Device device : devices) {
                if (device.getState() == 1 && device.getTnext() < earliestTime) {
                    earliestTime = device.getTnext();
                }
            }

            super.setTnext(earliestTime);
        } else {
            if (getQueue() < getMaxqueue()) {
                setQueue(getQueue() + 1);
            } else {
                failure++;
            }
        }
    }

    @Override
    public void outAct() {
        super.outAct();

        super.setTnext(Double.MAX_VALUE);
        super.setState(0);

        for (Device device : devices) {
            if (device.getState() == 1 && device.getTnext() == super.getTcurr()) {
                device.onOutAct();
                super.setState(0);
            }
        }

        double earliestTime = Double.MAX_VALUE;
        for (Device device : devices) {
            if (device.getState() == 1 && device.getTnext() < earliestTime) {
                earliestTime = device.getTnext();
                super.setState(1);
            }
        }
        super.setTnext(earliestTime);


        if (getQueue() > 0) {
            setQueue(getQueue() - 1);
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        }
        if (getNextElement() != null) {
            getNextElement().inAct();
        }
    }

    public int getFailure() {
        return failure;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getMaxqueue() {
        return maxqueue;
    }

    public void setMaxqueue(int maxqueue) {
        this.maxqueue = maxqueue;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + this.getFailure());
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue = getMeanQueue() + queue * delta;
        meanLoad = getMeanLoad() + getState() * delta;
    }

    public double getMeanQueue() {
        return meanQueue;
    }

    public double getMeanLoad() {
        return meanLoad;
    }
}
