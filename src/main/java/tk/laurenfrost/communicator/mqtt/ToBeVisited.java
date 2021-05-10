package tk.laurenfrost.communicator.mqtt;

import java.util.List;

public class ToBeVisited {

    private int number;
    private String someString;
    private List<String> names;
    private Object data;
    private float ratio;

    private List<ToBeVisited> childNodes;
    private ToBeVisited parent;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public List<ToBeVisited> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<ToBeVisited> childNodes) {
        this.childNodes = childNodes;
    }

    public ToBeVisited getParent() {
        return parent;
    }

    public void setParent(ToBeVisited parent) {
        this.parent = parent;
    }
}
