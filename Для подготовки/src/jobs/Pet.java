package jobs;

public class Pet {
    private String name;

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    {
        System.out.println("3");
    }

    public Pet() {
        System.out.println("4");
    }

    public Pet(String name) {
        System.out.println("5");
        this.name = name;
    }
}

class Cat extends Pet {
    private String name;

    static {
        System.out.println("Cat - 1");
    }

    {
        System.out.println("Cat - 2");
    }

    {
        System.out.println("Cat - 3");
    }

    public Cat() {
        System.out.println("Cat - 4");
    }

    public Cat(String name) {
        super(name);
        System.out.println("Cat - 5");
        this.name = name;
    }
}

class Start {
    public static void main(String[] args) {
        Cat cat = new Cat("Murrrr");
    }
}
