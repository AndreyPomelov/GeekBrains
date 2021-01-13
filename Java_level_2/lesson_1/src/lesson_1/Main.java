package lesson_1;

public class Main {

    public static void main(String[] args) {

        Human[] humans = {new Human("Ефистафий"), new Human("Евстигней"),
                new Human("Феофан"), new Human("Евлампий"),
                new Human("Поликарп"), new Human("Апполинарий"),
                new Human("Лука"), new Human("Харитон"),
                new Human("Пафнутий"), new Human("Митрофан")};

        Cat[] cats = {new Cat("Тимон"), new Cat("Пумба"),
                new Cat("Том"), new Cat("Джерри"),
                new Cat("Спайк"), new Cat("Бутч"),
                new Cat("Сильвестр"), new Cat("Гарфилд"),
                new Cat("Матроскин"), new Cat("Толстопуз")};

        Robot[] robots = {new Robot("R2-D2"), new Robot("C-3PO"),
                new Robot("Бендер"), new Robot("Калькулон"),
                new Robot("Дэниел"), new Robot("Жискар"),
                new Robot("Бамблби"), new Robot("Т-1000"),
                new Robot("Герти"), new Robot("Дейта")};

        Track[] tracks = {new Track("Беговая дорожка 1"), new Track("Беговая дорожка 2"),
                new Track("Беговая дорожка 3")};

        Wall[] walls = {new Wall("Стена 1"), new Wall("Стена 2"),
                new Wall("Стена 3")};

        System.out.println("Первое соревнование - беговые дорожки.\n");
        System.out.println("Дорожки пытаются преодолеть люди.\n");

        for (int i = 0; i < tracks.length; i++) {
            for (int j = 0; j < humans.length; j++) {
                humans[j].run(tracks[i]);
            }
            System.out.println();
        }

        System.out.println("Дорожки пытаются преодолеть коты.\n");

        for (int i = 0; i < tracks.length; i++) {
            for (int j = 0; j < cats.length; j++) {
                cats[j].run(tracks[i]);
            }
            System.out.println();
        }

        System.out.println("Дорожки пытаются преодолеть роботы.\n");

        for (int i = 0; i < tracks.length; i++) {
            for (int j = 0; j < robots.length; j++) {
                robots[j].run(tracks[i]);
            }
            System.out.println();
        }

        System.out.println("Второе соревнование - прыжки через стену.\n");
        System.out.println("Стены пытаются преодолеть люди.\n");

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < humans.length; j++) {
                humans[j].jump(walls[i]);
            }
            System.out.println();
        }

        System.out.println("Стены пытаются преодолеть коты.\n");

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < cats.length; j++) {
                cats[j].jump(walls[i]);
            }
            System.out.println();
        }

        System.out.println("Стены пытаются преодолеть роботы.\n");

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < robots.length; j++) {
                robots[j].jump(walls[i]);
            }
            System.out.println();
        }

        System.out.println("Успешно прошли все испытания:\n");
        System.out.println("Люди:\n");

        for (int i = 0; i < humans.length; i++) {
            if (!humans[i].isDisqualified()) System.out.println(humans[i].getName());
        }

        System.out.println();
        System.out.println("Коты:\n");

        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].isDisqualified()) System.out.println(cats[i].getName());
        }

        System.out.println();
        System.out.println("Роботы:\n");

        for (int i = 0; i < robots.length; i++) {
            if (!robots[i].isDisqualified()) System.out.println(robots[i].getName());
        }
    }
}
