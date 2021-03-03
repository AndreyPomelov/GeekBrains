package lesson_5;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static final Thread[] threads = new Thread[CARS_COUNT];
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }

        for (int i = 0; i < cars.length; i++) {
            threads[i].join();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победитель - " + Car.winnerName);
    }
}
