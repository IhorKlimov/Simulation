package org.example.oop;

public class Create extends Element {
    public Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct() throws Exception {
        super.outAct();
        super.setTnext(super.getTcurr() + super.getDelay());
        super.getNextElement().inAct();
    }
}
