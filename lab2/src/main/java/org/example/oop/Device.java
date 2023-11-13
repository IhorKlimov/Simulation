package org.example.oop;

public abstract class Device {
    private int state;
    private double tnext;

    public void onInAct() {
        state = 1;
    }

    public void onOutAct() {
        state = 0;
    }

    public int getState() {
        return state;
    }

    private void setState(int state) {
        this.state = state;
    }

    public double getTnext() {
        return tnext;
    }

    public void setTnext(double tnext) {
        this.tnext = tnext;
    }
}
