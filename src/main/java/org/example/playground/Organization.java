package org.example.playground;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Setter
@Getter
public class Organization implements Comparable<Organization> {

    private Long id;
    private String name;
    private Long parentId;
    private List<Organization> children;

    public Organization(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public int compareTo(@NotNull Organization o) {
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
