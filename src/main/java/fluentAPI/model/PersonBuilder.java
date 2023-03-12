package fluentAPI.model;

import fluentAPI.interfaces.IPersonBuilder;
import fluentAPI.interfaces.Title;

public class PersonBuilder implements IPersonBuilder {

    Title title;
    String name;

    public Person build() {
        return new Person(this.name, this.title);
    }

    @Override
    public IPersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IPersonBuilder withTitle(Title title) {
        this.title = title;
        return this;
    }
}
