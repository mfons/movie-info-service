package io.javabrains.movie_info_service.models;

public class Message {
    String message;
    int age;

    public Message(int age, String message) {
        this.age = age;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", age=" + age +
                '}';
    }

    public Message() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
