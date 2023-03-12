package collections;

import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import streams.model.Human;

import java.util.List;
import java.util.stream.Collector;

public class MainEclipseCollections {

    public static void main(String[] args) {
        FastListMultimap<String, String> citiesToPeople = FastListMultimap.newMultimap();

        citiesToPeople.put("Poznan", "Nowak");
        citiesToPeople.put("Poznan", "Kowalski");

        citiesToPeople.get("Poznan").forEach(System.out::println);
    }

    public static MutableListMultimap<Boolean, Human> partitionAdults(List<Human> collection) {
//        MutableListMultimap<Boolean, Human> result = ;
        return null;
    }
}
