package org.himusharier;

public class Dev {

    private int age;
    private int salary;

    private Computer comp;

    public Dev() {
        System.out.println("dev constructor");
    }

    public Dev(int age, int salary) {
        this.age = age;
        this.salary = salary;
        System.out.println("dev age constructor");
    }

    public Dev(Laptop laptop) {
        this.comp = laptop;
        System.out.println("dev laptop constructor");
    }

    public Dev(int age, int salary, Laptop laptop) {
        this.age = age;
        this.salary = salary;
        this.comp = laptop;
        System.out.println("dev constructor with age, salary, laptop");
    }

    public void build() {
        System.out.println("working on spring project");
        comp.compile();
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public void setLaptop(Laptop laptop) {
//        this.laptop = laptop;
//    }
    public Computer getComp() {
        return comp;
    }
    public void setComp(Computer comp) {
        this.comp = comp;
    }
}
