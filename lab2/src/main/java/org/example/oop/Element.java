package org.example.oop;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Random;

public class Element {
    private String name;
    private double tnext;
    private double delayMean, delayDev;
    private String distribution;
    private int quantity;
    private double tcurr;
    private int state;
    private List<NextElement> nextElements;
    private static int nextId = 0;
    private int id;

    public Element() {
        tnext = 0.0;
        delayMean = 1.0;
        distribution = "exp";
        tcurr = tnext;
        state = 0;
        nextElements = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public Element(double delay) {
        name = "anonymus";
        tnext = 0.0;
        delayMean = delay;
        distribution = "";
        tcurr = tnext;
        state = 0;
        nextElements = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public Element(String nameOfElement, double delay) {
        name = nameOfElement;
        tnext = 0.0;
        delayMean = delay;
        distribution = "exp";
        tcurr = tnext;
        state = 0;
        nextElements = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public double getDelay() {
        double delay = getDelayMean();
        if ("exp".equalsIgnoreCase(getDistribution())) {
            delay = FunRand.Exp(getDelayMean());
        } else {
            if ("norm".equalsIgnoreCase(getDistribution())) {
                delay = FunRand.Norm(getDelayMean(), getDelayDev());
            } else {
                if ("unif".equalsIgnoreCase(getDistribution())) {
                    delay = FunRand.Unif(getDelayMean(), getDelayDev());

                } else {
                    if ("".equalsIgnoreCase(getDistribution())) delay = getDelayMean();
                }
            }
        }
        return delay;
    }

    public double getDelayDev() {
        return delayDev;
    }

    public void setDelayDev(double delayDev) {
        this.delayDev = delayDev;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTcurr() {
        return tcurr;
    }

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<NextElement> getNextElements() {
        return nextElements;
    }

    public void setNextElements(List<NextElement> nextElements) {
        float sum = 0f;
        for (NextElement nextElement : nextElements) {
            sum += nextElement.getProbability();
        }
        if (sum != 1f) {
            throw new InvalidParameterException("Total sum of probabilities should equal to 1.0");
        }

        this.nextElements = nextElements;
    }

    public Element getNextElement() {
        float rand = new Random().nextFloat();
        float currentValue = 0f;
        if (nextElements != null) {
            for (NextElement nextElement : nextElements) {
                currentValue += nextElement.getProbability();
                if (rand < currentValue) {
                    return nextElement.getElement();
                }
            }
        }
        return null;
    }

    public void inAct() {
    }

    public void outAct() throws Exception {
        quantity++;
    }

    public double getTnext() {
        return tnext;
    }

    public void setTnext(double tnext) {
        this.tnext = tnext;
    }

    public double getDelayMean() {
        return delayMean;
    }

    public void setDelayMean(double delayMean) {
        this.delayMean = delayMean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printResult() {
        System.out.println(getName() + " quantity = " + quantity);
    }

    public void printInfo() {
        System.out.println(getName() + " state= " + state + " quantity = " + quantity + " tnext= " + tnext);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doStatistics(double delta) {
    }
}
