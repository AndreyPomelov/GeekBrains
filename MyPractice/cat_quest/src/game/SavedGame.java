package game;

import game.animals.Cat;
import game.animals.Dog;

import java.io.Serializable;

public class SavedGame implements Serializable {

    private static final long serialVersionUID = 1L;

    public int dogNameCount;
    public int dogLastLevel;
    public Cat cat;
    public Dog dog;
    public boolean winGame;
    public boolean allBossesBeaten;

    public SavedGame(int dogNameCount, int dogLastLevel, Cat cat, Dog dog, boolean winGame, boolean allBossesBeaten) {
        this.dogNameCount = dogNameCount;
        this.dogLastLevel = dogLastLevel;
        this.cat = cat;
        this.dog = dog;
        this.winGame = winGame;
        this.allBossesBeaten = allBossesBeaten;
    }
}
