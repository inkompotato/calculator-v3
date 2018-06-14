import java.util.List;

public class SetA implements Set {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(Object element) {

    }

    @Override
    public Set combine(Set set) {
        return null;
    }

    @Override
    public void remove(Object element) {

    }

    @Override
    public Set subtract(Set set) {
        return null;
    }

    @Override
    public Set intersect(Set set) {
        return null;
    }

    @Override
    public List setEntriesAsList() {
        return null;
    }
}
