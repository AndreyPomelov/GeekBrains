package lesson_1;

public class Main {

    public static void main(String[] args) {

        JumpableRunnable[] competitors = {new Human("Ефистафий"), new Human("Евстигней"),
                new Human("Феофан"), new Human("Евлампий"),
                new Human("Поликарп"), new Human("Апполинарий"),
                new Human("Лука"), new Human("Харитон"),
                new Human("Пафнутий"), new Human("Митрофан"),
                new Cat("Тимон"), new Cat("Пумба"),
                new Cat("Том"), new Cat("Джерри"),
                new Cat("Спайк"), new Cat("Бутч"),
                new Cat("Сильвестр"), new Cat("Гарфилд"),
                new Cat("Матроскин"), new Cat("Толстопуз"),
                new Robot("R2-D2"), new Robot("C-3PO"),
                new Robot("Бендер"), new Robot("Калькулон"),
                new Robot("Дэниел"), new Robot("Жискар"),
                new Robot("Бамблби"), new Robot("Т-1000"),
                new Robot("Герти"), new Robot("Дейта")};

        Obstacle[] obstacles = {new Track("Беговая дорожка 1"), new Track("Беговая дорожка 2"),
                new Track("Беговая дорожка 3"), new Wall("Стена 1"),
                new Wall("Стена 2"), new Wall("Стена 3")};

        for (Obstacle o : obstacles) {
            for (JumpableRunnable j : competitors) {
             if (o instanceof Track) j.run((Track)o);
             if (o instanceof Wall) j.jump((Wall)o);
            }
        }

        System.out.println("\nУспешно прошли все испытания:\n");

        for (JumpableRunnable j : competitors) {
            if (!j.isDisqualified()) j.showInfo();
        }
    }
}
