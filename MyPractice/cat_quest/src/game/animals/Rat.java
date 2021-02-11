package game.animals;

import game.Logic;

public class Rat extends Animal {

    public Rat() {
        this.setMaxHitPoints(Logic.cat.getMaxHitPoints() - 100);
        this.setHitPoints(getMaxHitPoints());
        this.setPower(Logic.cat.getPower() - 20);
        this.setDefense(Logic.cat.getDefense() - 20);
        super.catMessage1 = " с размаху бьёт лапой крысу-мутанта по морде!";
        super.catMessage2 = " с разбегу таранит крысу-мутанта головой!";
        super.catMessage3 = " высоко подпрыгивает и приземляется задницей на голову крысы-мутанта!";
        super.enemyMessage1 = "Крыса-мутант плюётся в кошака кислотой!";
        super.enemyMessage2 = "Крыса-мутант выпускает в кошака лучи из глаз!";
        super.enemyMessage3 = "Крыса-мутант атакует кошака пятой лапой!";
        super.whatAnimal = "Rat";
    }
}
