package tk.laurenfrost.communicator.mqtt;

public class StrangeFactory {

    private int id;
    private String name;
    private int age;
    private float ratio;

    public static StrangeFactory newInstance() {
        return new StrangeFactory();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
