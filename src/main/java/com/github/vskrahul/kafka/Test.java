package com.github.vskrahul.kafka;

public class Test {
    public static void main(String[] args) {
        System.out.println(Name.RAHUL.toString());
    }
}

enum Name {
    RAHUL("rahul");
    String value;
    Name(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return this.value;
    }
}
