package kz.zarema.spring;
public class human {

    int id;
    String name;
    String country;
    int age;

    public human() {
        this.id = 0;
        this.name = "";
        this.country = "";
        this.age = 0;
    }

    public human(String name, String country, int age) {
        this.id = 0;
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Имя=%s, Страна=%s, Возраст=%d", name, country, age);
    }
}