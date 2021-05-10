package tk.laurenfrost.communicator.mqtt;

import java.util.Date;
import java.util.List;

public class Visited {

    private long id;
    private String fullName;
    private Date startDate;
    private Date endDate;
    private Object info;

    private List<Visited> childNodes;
    private Visited parentNode;

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public List<Visited> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Visited> childNodes) {
        this.childNodes = childNodes;
    }

    public Visited getParentNode() {
        return parentNode;
    }

    public void setParentNode(Visited parentNode) {
        this.parentNode = parentNode;
    }
}
