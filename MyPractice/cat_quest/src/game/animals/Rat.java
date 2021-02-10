package game.animals;

import game.Logic;

public class Rat extends Animal {

    public String catMessage1 = " с размаху бьёт лапой крысу-мутанта по морде!";
    public String catMessage2 = " с разбегу таранит крысу-мутанта головой!";
    public String catMessage3 = " высоко подпрыгивает и приземляется задницей на голову крысы-мутанта!";
    public String enemyMessage1 = "Крыса-мутант плюётся в кошака кислотой!";
    public String enemyMessage2 = "Крыса-мутант выпускает в кошака лучи из глаз!";
    public String enemyMessage3 = "Крыса-мутант атакует кошака пятой лапой!";

    public Rat() {
        this.setMaxHitPoints(Logic.cat.getMaxHitPoints() - 100);
        this.setHitPoints(getMaxHitPoints());
        this.setPower(Logic.cat.getPower() - 20);
        this.setDefense(Logic.cat.getDefense() - 20);
    }
}
