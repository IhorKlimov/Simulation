package org.example.oop;

public class NextElement {
    private Element element;
    private float probability;

    public NextElement(Element element, float probability) {
        this.element = element;
        this.probability = probability;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
