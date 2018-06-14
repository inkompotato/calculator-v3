import java.util.ArrayList;
import java.util.List;

public class Set<E>{

    ArrayList<E> list;

    Set(){
        list = new ArrayList<>();
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }


    public boolean contains(E element) {
        return list.contains(element);
    }


    public int size() {
        return list.size();
    }


    public void add(E element) {
        if (!list.contains(element)) {
            list.add(element);
        }
    }


    public Set combine(Set<E> setAdd) {
        Set<E> s = new Set<>();
        for(E element : list){
            s.add(element);
        }
        for(E element : setAdd.setEntriesAsList()){
            s.add(element);
        }
        return s;
    }

    public void remove(E element) {
        if(list.contains(element)){
            list.remove(element);
        }

    }

    public Set subtract(Set<E> setSub) {
        Set<E> s = new Set<>();
        for(E element : list){
            s.add(element);
        }
        for(E element : setSub.setEntriesAsList()){
            s.remove(element);
        }
        return s;
    }


    public Set intersect(Set <E> setInt) {
        Set<E> s = new Set<>();
        for(E element : setInt.setEntriesAsList()){
            if(list.contains(element)){
                s.add(element);
            }
        }

        return s;
    }

    public List<E> setEntriesAsList() {
        return list;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(E element : list){
            s.append(element).append(",");
        }
        return s.toString().substring(0, s.length()-1);
    }
}
