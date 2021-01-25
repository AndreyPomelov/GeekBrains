package game;

import game.animals.Cat;
import game.windows.Controller;

public class Logic {

    Controller controller;
    Cat cat;

    void startGame (String catName, Controller _controller) {
        controller = _controller;
        cat = new Cat(500, 500, 50, 50,
                catName, 1, 0, 0, 0);
        controller.updateLeftPanel();
    }
}
