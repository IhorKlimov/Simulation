package org.example.oop;

import java.util.ArrayList;
import java.util.List;

public class DKR {
    public static void main(String[] args) throws Exception {
        Create c = new Create(2.0);
        Process smo1 = new Process(1.0, List.of(new Device() {}, new Device() {}));
        Process smo2 = new Process(1.0, List.of(new Device() {}));
        Process smo3 = new Process(1.0, List.of(new Device() {}));

        System.out.println("id0 = " + c.getId() + " id1=" + smo1.getId() + " id2=" + smo2.getId() + " id3=" + smo3.getId());

        c.setNextElements(List.of(new NextElement(smo1, 1f)));
        smo1.setNextElements(List.of(new NextElement(smo2, 0.75f), new NextElement(smo3, 0.25f)));
        smo2.setNextElements(new ArrayList<>());
        smo3.setNextElements(new ArrayList<>());

        smo1.setMaxqueue(Integer.MAX_VALUE);
        smo2.setMaxqueue(0);
        smo3.setMaxqueue(2);

        c.setName("CREATOR");
        smo1.setName("SMO 1");
        smo2.setName("SMO 2");
        smo3.setName("SMO 3");

        c.setDistribution("exp");
        smo1.setDistribution("exp");
        smo2.setDistribution("exp");
        smo3.setDistribution("exp");

        ArrayList<Element> list = new ArrayList<>();
        list.add(c);
        list.add(smo1);
        list.add(smo2);
        list.add(smo3);

        Model model = new Model(list);
        model.simulate(1000.0);
    }
}
