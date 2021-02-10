package game.animals;

import game.Logic;

public class Mouse extends Animal {

    public String catMessage1 = " поскальзывается и всем весом падает на мышь!";
    public String catMessage2 = " зверски кусает мышь!";
    public String catMessage3 = " свирепо расцарапывает мышь!";
    public String enemyMessage1 = "Мышь кусает кошака в нос!";
    public String enemyMessage2 = "Мышь уворачивается от прыжка, и " + Logic.cat.getName() + " влетает мордой в стену!";
    public String enemyMessage3 = "Мышь бегает между лапами, и " + Logic.cat.getName() + " по ошибке кусает себя за лапу!";

    public Mouse() {
        this.setMaxHitPoints(Logic.cat.getMaxHitPoints() - 200);
        this.setHitPoints(getMaxHitPoints());
        this.setPower(Logic.cat.getPower() - 30);
        this.setDefense(Logic.cat.getDefense() - 30);
    }
}
