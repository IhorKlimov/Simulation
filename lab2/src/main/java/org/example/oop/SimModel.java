package org.example.oop;

import java.util.ArrayList;
import java.util.List;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(2.0);
        List<Device> devices1 = List.of(new Device() {});
        List<Device> devices2 = List.of(new Device() {});
        List<Device> devices3 = List.of(new Device() {});
        Process p = new Process(1, devices1);
        Process p2 = new Process(1.0, devices2);
        Process p3 = new Process(1.0, devices3);

        System.out.println("id0 = " + c.getId() + " id1=" + p.getId() + " id2=" + p2.getId() + " id3=" + p3.getId());

        c.setNextElement(p);
        p.setNextElement(p2);
        p2.setNextElement(p3);

        p.setMaxqueue(5);
        p2.setMaxqueue(5);
        p3.setMaxqueue(5);

        c.setName("CREATOR");
        p.setName("PROCESSOR 1");
        p2.setName("PROCESSOR 2");
        p3.setName("PROCESSOR 3");

        c.setDistribution("exp");
        p.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");

        ArrayList<Element> list = new ArrayList<>();
        list.add(c);
        list.add(p);
        list.add(p2);
        list.add(p3);

        Model model = new Model(list);
        model.simulate(1000.0);
    }
}
