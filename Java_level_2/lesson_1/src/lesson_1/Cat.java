package lesson_1;

public class Cat implements JumpableRunnable {

    private final String name;
    private final int runAbility;
    private final int jumpAbility;
    private boolean isDisqualified;

    Cat(String name) {
        this.name = name;
        runAbility = (int) (Math.random() * 61) + 80;
        jumpAbility = (int) (Math.random() * 9) + 7;
        isDisqualified = false;
    }

    public boolean isDisqualified() {
        return isDisqualified;
    }

    @Override
    public void run(Track track) {
        if (isDisqualified) return;
        System.out.println("Кот " + name + " пытается преодолеть снаряд " + track.getName() + ".");
        if (runAbility >= track.getLength()) System.out.println("Успешно!");
        else {
            System.out.println("Провал! " + name + " выбывает из соревнований!");
            isDisqualified = true;
        }
    }

    @Override
    public void jump(Wall wall) {
        if (isDisqualified) return;
        System.out.println("Кот " + name + " пытается преодолеть снаряд " + wall.getName() + ".");
        if (jumpAbility >= wall.getHeight()) System.out.println("Успешно!");
        else {
            System.out.println("Провал! " + name + " выбывает из соревнований!");
            isDisqualified = true;
        }
    }

    @Override
    public void showInfo() {
        System.out.println("Кот " + name);
    }
}
