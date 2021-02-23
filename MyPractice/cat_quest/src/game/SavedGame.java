package game;

import java.io.Serializable;

public class SavedGame implements Serializable {

    private static final long serialVersionUID = 1L;

    private final boolean winGame;
    private final boolean allBossesBeaten;
    private final String catName;
    private final int catLevel;
    private final int foodCount;
    private final int valCount;
    private final int exp;
    private final int catMaxHP;
    private final int catHP;
    private final int catPower;
    private final int catDefense;
    private final String dogName;
    private final int dogLevel;
    private final int nameCount;

    public boolean isWinGame() {
        return winGame;
    }

    public boolean isAllBossesBeaten() {
        return allBossesBeaten;
    }

    public String getCatName() {
        return catName;
    }

    public int getCatLevel() {
        return catLevel;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getValCount() {
        return valCount;
    }

    public int getExp() {
        return exp;
    }

    public int getCatMaxHP() {
        return catMaxHP;
    }

    public int getCatHP() {
        return catHP;
    }

    public int getCatPower() {
        return catPower;
    }

    public int getCatDefense() {
        return catDefense;
    }

    public String getDogName() {
        return dogName;
    }

    public int getDogLevel() {
        return dogLevel;
    }

    public int getNameCount() {
        return nameCount;
    }

    public SavedGame(boolean winGame, boolean allBossesBeaten, String catName, int catLevel, int foodCount, int valCount, int exp, int catMaxHP, int catHP, int catPower, int catDefense, String dogName, int dogLevel, int nameCount) {
        this.winGame = winGame;
        this.allBossesBeaten = allBossesBeaten;
        this.catName = catName;
        this.catLevel = catLevel;
        this.foodCount = foodCount;
        this.valCount = valCount;
        this.exp = exp;
        this.catMaxHP = catMaxHP;
        this.catHP = catHP;
        this.catPower = catPower;
        this.catDefense = catDefense;
        this.dogName = dogName;
        this.dogLevel = dogLevel;
        this.nameCount = nameCount;
    }
}
