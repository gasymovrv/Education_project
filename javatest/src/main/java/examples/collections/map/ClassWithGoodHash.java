package examples.collections.map;

import java.util.Objects;

public class ClassWithGoodHash implements Comparable<ClassWithGoodHash> {
    private Long id;

    public ClassWithGoodHash(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassWithGoodHash{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassWithGoodHash)) return false;
        ClassWithGoodHash that = (ClassWithGoodHash) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(ClassWithGoodHash o) {
        return id.compareTo(o.id);
    }
}
