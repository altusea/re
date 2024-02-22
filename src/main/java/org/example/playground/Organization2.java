package org.example.playground;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Setter
@Getter
public class Organization2 implements Comparable<Organization2> {

    private Long id;
    private String name;
    private Long parentId;
    private Set<Organization2> children;

    public Organization2(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public int compareTo(@NotNull Organization2 o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
