package lesson_1;

public interface JumpableRunnable extends Jumpable, Runnable {
    void showInfo();
    boolean isDisqualified();
}
