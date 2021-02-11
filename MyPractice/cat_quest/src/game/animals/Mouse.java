package game.animals;

import game.Logic;

public class Mouse extends Animal {

    public Mouse() {
        this.setMaxHitPoints(Logic.cat.getMaxHitPoints() - 200);
        this.setHitPoints(getMaxHitPoints());
        this.setPower(Logic.cat.getPower() - 30);
        this.setDefense(Logic.cat.getDefense() - 30);
        super.catMessage1 = " поскальзывается и всем весом падает на мышь!";
        super.catMessage2 = " зверски кусает мышь!";
        super.catMessage3 = " свирепо расцарапывает мышь!";
        super.enemyMessage1 = "Мышь кусает кошака в нос!";
        super.enemyMessage2 = "Мышь уворачивается от прыжка, и " + Logic.cat.getName() + " влетает мордой в стену!";
        super.enemyMessage3 = "Мышь бегает между лапами, и " + Logic.cat.getName() + " по ошибке кусает себя за лапу!";
        super.whatAnimal = "Mouse";
    }
}
