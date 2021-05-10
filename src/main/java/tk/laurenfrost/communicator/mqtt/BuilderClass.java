package tk.laurenfrost.communicator.mqtt;

public class BuilderClass {

    private int intField;
    private String stringField;
    private Object objectField;

    private BuilderClass(){}

    public static BuilderClass newInstance() {
        return new BuilderClass();
    }

    public int getIntField() {
        return intField;
    }

    public BuilderClass setIntField(int intField) {
        this.intField = intField;
        return this;
    }

    public String getStringField() {
        return stringField;
    }

    public BuilderClass setStringField(String stringField) {
        this.stringField = stringField;
        return this;
    }

    public Object getObjectField() {
        return objectField;
    }

    public BuilderClass setObjectField(Object objectField) {
        this.objectField = objectField;
        return this;
    }
}
