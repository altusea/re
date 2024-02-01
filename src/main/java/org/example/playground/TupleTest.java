package org.example.playground;

import kala.collection.immutable.ImmutableSet;
import kala.collection.mutable.MutableList;
import kala.tuple.Tuple2;

public class TupleTest {

    public static void main(String[] args) {
        MutableList<Tuple2<String, String>> tupleList = MutableList.create();
        tupleList.append(new Tuple2<>("aaa", "1"));
        tupleList.append(new Tuple2<>("aaa", "1"));
        tupleList.append(new Tuple2<>("bbb", "2"));
        tupleList.append(new Tuple2<>("ccc", "3"));
        tupleList.append(new Tuple2<>("ccc", "3"));
        System.out.println(tupleList.size());

        ImmutableSet<Tuple2<String, String>> tupleSet = tupleList.toImmutableSet();
        System.out.println(tupleSet.size());
    }

}
