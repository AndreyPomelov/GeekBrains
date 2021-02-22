package game;

import game.animals.Cat;
import game.animals.Dog;

import java.io.Serializable;

public class SavedGame implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean winGame;
    private boolean allBossesBeaten;
    private String catName;
    private int catLevel;
    private int foodCount;
    private int valCount;
    private int exp;
    private int catMaxHP;
    private int catHP;
    private int catPower;
    private int catDefense;
    private String dogName;
    private int dogLevel;
    private int nameCount;

    public boolean isWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }

    public boolean isAllBossesBeaten() {
        return allBossesBeaten;
    }

    public void setAllBossesBeaten(boolean allBossesBeaten) {
        this.allBossesBeaten = allBossesBeaten;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(int catLevel) {
        this.catLevel = catLevel;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getValCount() {
        return valCount;
    }

    public void setValCount(int valCount) {
        this.valCount = valCount;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCatMaxHP() {
        return catMaxHP;
    }

    public void setCatMaxHP(int catMaxHP) {
        this.catMaxHP = catMaxHP;
    }

    public int getCatHP() {
        return catHP;
    }

    public void setCatHP(int catHP) {
        this.catHP = catHP;
    }

    public int getCatPower() {
        return catPower;
    }

    public void setCatPower(int catPower) {
        this.catPower = catPower;
    }

    public int getCatDefense() {
        return catDefense;
    }

    public void setCatDefense(int catDefense) {
        this.catDefense = catDefense;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogLevel() {
        return dogLevel;
    }

    public void setDogLevel(int dogLevel) {
        this.dogLevel = dogLevel;
    }

    public int getNameCount() {
        return nameCount;
    }

    public void setNameCount(int nameCount) {
        this.nameCount = nameCount;
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
