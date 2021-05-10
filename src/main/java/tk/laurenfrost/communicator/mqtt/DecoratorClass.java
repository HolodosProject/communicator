package tk.laurenfrost.communicator.mqtt;

import java.util.ArrayList;

public class DecoratorClass {

    private final DecoratedClass decorated;
    private final String a = "b";
    private final static int i = 19;

    public DecoratorClass(DecoratedClass obj) {
        this.decorated = obj;
    }

    public void generateObject() {
        decorated.generateObject();
    }

    private String createNewObject() {
        return "fff";
    }

    public ArrayList<Object> createManyObjects() {
        return new ArrayList<>(decorated.createManyObjects());
    }

    protected DecoratedClass recreateObject(Object oldObj) {
        Object a = decorated.recreateObject(oldObj);
        return new DecoratedClass();
    }

}
