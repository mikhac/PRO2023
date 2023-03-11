package second.shortcuts;

import java.util.List;

public class ClassThatHaveItAll implements InterfaceOne {
    String name;
    Integer number;
    List<Long> list;
    public ClassThatHaveItAll(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public void printMe(String info) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
