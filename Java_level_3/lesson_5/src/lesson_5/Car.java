package lesson_5;

public class Car implements Runnable {
    private static volatile boolean isRaceStarted;
    private static volatile boolean isWinnerFinished;
    public static volatile String winnerName;
    public static final Object monitor;
    private static int CARS_COUNT;
    public static volatile int CARS_READY;
    static {
        CARS_COUNT = 0;
        CARS_READY = 0;
        monitor = new Object();
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public synchronized void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            CARS_READY++;
            if (!isRaceStarted && CARS_READY == MainClass.CARS_COUNT) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                isRaceStarted = true;
            }
            synchronized (monitor) {
                monitor.notifyAll();
                while (!(MainClass.CARS_COUNT == CARS_READY)) {
                    monitor.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!isWinnerFinished) {
            winnerName = this.name;
            isWinnerFinished = true;
        }
    }
}