package org.example.playground;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.example.util.ConsoleUtil;
import org.jetbrains.annotations.NotNull;

public class EclipseCollectionsTest {

    public record Person(String firstName, String lastName) {

        public boolean lastNameEquals(@NotNull String name) {
            return name.equals(this.lastName);
        }
    }

    public static void main(String[] args) {
        ImmutableList<Person> people = Lists.immutable.with(
                new Person("Sally", "Smith"),
                new Person("Ted", "Watson"),
                new Person("Mary", "Williams"));
        var lastNames1 = people.collect(Person::lastName);
        System.out.println(lastNames1.makeString()); // "Smith, Watson, Williams"
        var lastNames2 = people.asLazy().collect(Person::lastName);
        System.out.println(lastNames2.makeString()); // "Smith, Watson, Williams"

        ConsoleUtil.printSeparateLine();
        var smiths1 = people.select(person -> person.lastNameEquals("Smith"));
        System.out.println(smiths1.makeString());
        var smiths2 = people.selectWith(Person::lastNameEquals, "Smith");
        System.out.println(smiths2.makeString());
        var notSmiths1 = people.reject(person -> person.lastNameEquals("Smith"));
        System.out.println(notSmiths1.makeString());
        var notSmiths2 = people.rejectWith(Person::lastNameEquals, "Smith");
        System.out.println(notSmiths2.makeString());

        ConsoleUtil.printSeparateLine();
        // Any
        System.out.println(people.anySatisfy(person -> person.lastNameEquals("Smith")));
        System.out.println(people.anySatisfyWith(Person::lastNameEquals, "Smith"));
        // All
        System.out.println(people.allSatisfy(person -> person.lastNameEquals("Smith")));
        System.out.println(people.allSatisfyWith(Person::lastNameEquals, "Smith"));
        // None
        System.out.println(people.noneSatisfy(person -> person.lastNameEquals("Smith")));
        System.out.println(people.noneSatisfyWith(Person::lastNameEquals, "Smith"));

        ConsoleUtil.printSeparateLine();
        Pair<String, String> pair = Tuples.pair("Str", "Int");
        System.out.println(pair.getOne());
        System.out.println(pair.getTwo());
    }
}
