package org.lab3;

public class Main {
    public static void main(String[] args) {
        User user = new User("John", "john@example.com");
        UserJsonSerializer jsonSerializer = new UserJsonSerializer();
        String json = jsonSerializer.serialize(user);
        System.out.println(json);

        Dog dog = new Dog(5, "white", 7, "Milo");
        DogJsonSerializer dogJsonSerializer = new DogJsonSerializer();
        json = dogJsonSerializer.serialize(dog);
        System.out.println(json);

        Tea tea = new Tea("black", 1.2, "large");
        TeaXmlSerializer teaXmlSerializer = new TeaXmlSerializer();
        String xml = teaXmlSerializer.serialize(tea);
        System.out.println(xml);
    }
}