package java_level_2.lesson_2.exception;

public class MyArrayDataException extends IllegalArgumentException {

    private final String message;

    public MyArrayDataException(int x, int y) {
        message = "Ошибка данных в строке " + (x + 1) + ", столбце " + (y + 1);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
