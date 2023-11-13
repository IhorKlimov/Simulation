package org.example.oop;

import java.util.ArrayList;
import java.util.List;

public class SimModel {
    public static void main(String[] args) throws Exception {
        Create c = new Create(2.0);
        List<Device> devices1 = List.of(new Device() {});
        List<Device> devices2 = List.of(new Device() {});
        List<Device> devices3 = List.of(new Device() {});
        Process p = new Process(1, devices1);
        Process p2 = new Process(1.0, devices2);
        Process p3 = new Process(1.0, devices3);

        System.out.println("id0 = " + c.getId() + " id1=" + p.getId() + " id2=" + p2.getId() + " id3=" + p3.getId());

        c.setNextElements(List.of(new NextElement(p, 1f)));
        p.setNextElements(List.of(new NextElement(p2, 0.5f), new NextElement(p3, 0.5f)));
        p2.setNextElements(List.of(new NextElement(p3, 1f)));
        p3.setNextElements(List.of(new NextElement(null, 0.9f), new NextElement(p, 0.1f)));

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
