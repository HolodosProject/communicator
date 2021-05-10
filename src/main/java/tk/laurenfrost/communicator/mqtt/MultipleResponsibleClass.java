package tk.laurenfrost.communicator.mqtt;

import java.time.LocalDate;

public class MultipleResponsibleClass {

    int value;
    String name;
    int age;
    int salary;
    String department;

    public void employ(String dep, int salary) {
        this.department = dep;
        this.salary = salary;
    }

    public void promote(int sal, String dep) {
        this.salary += sal;
        if (this.department.equalsIgnoreCase(dep))
            System.out.println("remains here");
    }

    public void setDocumentsData(String name, LocalDate birthDate) {
        this.name = name;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
    }

    public String buildPassportData() {
        return this.name+this.value+this.salary;
    }

}
