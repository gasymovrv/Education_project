package examples.collections.map;

import java.util.Objects;

public class ClassWithBadHash {
    private Long id;

    public ClassWithBadHash(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassWithBadHash{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassWithBadHash)) return false;
        ClassWithBadHash that = (ClassWithBadHash) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
