package org.example.playground;

import com.plugatar.jkscope.JKScope;
import lombok.Getter;
import lombok.Setter;

import static com.plugatar.jkscope.JKScope.with;

@Getter
@Setter
class Worker implements JKScope<Worker> {

    private String name;

    private int lengthOfService;

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", lengthOfService=" + lengthOfService +
                '}';
    }

    public static void main(String[] args) {
        Worker worker = new Worker().let(it -> {
            it.setName("xxx");
            it.setLengthOfService(3);
        });

        System.out.println(worker);

        with(worker, System.out::println);
    }
}
