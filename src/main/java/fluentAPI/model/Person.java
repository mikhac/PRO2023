package fluentAPI.model;

import fluentAPI.interfaces.IPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Person implements IPerson {

    List<Person> friends = new ArrayList<>();
    String name;
    Enum title;

    protected Person(String name, fluentAPI.interfaces.Title title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public IPerson addFriend(Person friend) {
        this.friends.add(friend);
        return this;
    }

    @Override
    public IPerson sayHelloToFriends() {
        for (Person friend : this.friends) {
            System.out.println("Hello " + friend.name);
        }
        return this;
    }

    @Override
    public IPerson processFriends(Function<List<Person>, List<Person>> processor) {
//        processor = processor.andThen(x -> this.friends.removeAll(x));
        processor.apply(this.friends);
        return this;
    }

    @Override
    public IPerson chooseBestFriend(Function<List<Person>, Person> picker) {
        return picker.apply(this.friends);
    }

    @Override
    public void print() {
        System.out.println(this.title + " " + this.name);
    }
}
