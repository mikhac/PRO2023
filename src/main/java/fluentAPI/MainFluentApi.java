package fluentAPI;

import fluentAPI.interfaces.Title;
import fluentAPI.model.Person;
import fluentAPI.model.PersonBuilder;

import java.util.List;
import java.util.function.Function;

public class MainFluentApi {

    public static void main(String[] args) {

        //Package-private constructor is not accessible here, we must use the builder:
        //Person p = new Person("a", Title.PROF);

        PersonBuilder personBuilder = new PersonBuilder();

        Person jose = personBuilder.withName("Jose").withTitle(Title.DR).build();
        Person miguel = personBuilder.withName("Miguel").withTitle(Title.DR).build();
        Person raul = personBuilder.withName("Raul").withTitle(Title.DR).build();
        Person santiago = personBuilder.withName("Santiago").withTitle(Title.DR).build();

        jose.addFriend(miguel).addFriend(raul).addFriend(santiago);
        jose.sayHelloToFriends();

//        jose.processFriends(friends -> {
//            friends.clear();
//            return friends;
//        }).sayHelloToFriends();

        Function<List<Person>, Person> firstBest = friends -> friends.get(0);
        jose.chooseBestFriend(firstBest).print();
    }
}