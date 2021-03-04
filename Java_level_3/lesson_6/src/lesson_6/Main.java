package lesson_6;

public class Main {

    public static void main(String[] args) {

    }

    public static int[] arrayProcessor1(int[] sourceArray) {
        int index = -1;
        for (int i = sourceArray.length - 1; i >= 0; i--) {
            if (sourceArray[i] == 4) {
                index = i;
                break;
            }
        }
        if (index == -1) throw new RuntimeException("Массив должен содержать хотя бы одну цифру 4.");
        int[] array = new int[sourceArray.length - index - 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = sourceArray[i + index + 1];
        }
        return array;
    }

    public static boolean arrayProcessor2(int[] array) {
        int count1 = 0;
        int count4 = 0;
        for (int j : array) {
            if (j == 1) count1++;
            else if (j == 4) count4++;
            else throw new RuntimeException("Массив должен содержать только цифры 1 и 4");
        }
        return count1 != 0 && count4 != 0;
    }
}
