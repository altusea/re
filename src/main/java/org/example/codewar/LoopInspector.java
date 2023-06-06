package org.example.codewar;

public class LoopInspector {

    interface Node {
        Node getNext();
    }

    public int loopSize(Node node) {
        Node a = node, b = node.getNext();
        while (a != b) {
            a = a.getNext();
            b = b.getNext().getNext();
        }
        int counter = 1;
        b = b.getNext();
        while (b != a) {
            b = b.getNext();
            counter++;
        }
        return counter;
    }
}
