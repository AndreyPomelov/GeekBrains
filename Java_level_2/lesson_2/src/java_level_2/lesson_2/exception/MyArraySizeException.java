package java_level_2.lesson_2.exception;

public class MyArraySizeException extends IllegalArgumentException {

    private final String message = "Массив с размерами, отличными от 4х4, недопустим";

    @Override
    public String getMessage() {
        return message;
    }
}
