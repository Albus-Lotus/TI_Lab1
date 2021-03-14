import java.util.Scanner;

public class RotatingGrid {
    private static String text;
    private static int matrixNumber[][];
    private static int key[][];
    private static int mHeight;

    public static int[][] turnToRight(int[][] array) {
        int[][] resultArray = new int[array[0].length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                resultArray[j][array.length - i - 1] = array[i][j];
            }
        }

        return resultArray;
    }

    public static boolean isRightKey(int[][] array, int[][] key) {
        boolean[] numbers = new boolean[mHeight + 1];

        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = false;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (key[i][j] == 1) {
                    if (numbers[array[i][j]]) {
                        return false;
                    }
                    else {
                        numbers[array[i][j]] = true;
                    }
                }
            }
        }

        for (int i = 1; i < numbers.length; i++) {
            if (!numbers[i]) {
                return false;
            }
        }

        return true;
    }

    private static String encrypt(int[][] array, int[][] key) {
        int index = -mHeight - 1;
        char matrixText[][] = new char[mHeight][mHeight];

        for (int k = 0; k < 4; k ++) {
            index += mHeight;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (key[i][j] == 1) {
                        if (index + array[i][j] < text.length()) {
                            matrixText[i][j] = text.charAt(index + array[i][j]);
                        }
                        else {
                            matrixText[i][j] = (char) ((char) index + 65);
                        }
                    }
                }
            }
            key = turnToRight(key);
        }

        String result = "";

        for (int i = 0; i < matrixText.length; i++) {
            for (int j = 0; j < matrixText[i].length; j++) {
                result += matrixText[i][j];
            }
        }

        return result;
    }

    private static String decrypt(int[][] array, int[][] key) {
        char matrixText[][] = new char[mHeight][mHeight];
        int index = 0;

        for (int i = 0; i < matrixText.length; i++) {
            for (int j = 0; j < matrixText[i].length; j++) {
                if (index < text.length()) {
                    matrixText[i][j] = text.charAt(index++);
                }
                else {
                    matrixText[i][j] = '%';
                }
            }
        }

        String result = "";
        char[] tempArray = new char[mHeight + 1];

        for (int k = 0; k < 4; k ++) {
            for (int i = 0; i < matrixText.length; i++) {
                for (int j = 0; j < matrixText[i].length; j++) {
                    if (key[i][j] == 1) {
                        tempArray[array[i][j]] = matrixText[i][j];
                    }
                }
            }
            for (int i = 0; i < tempArray.length; i++) {
                if (tempArray[i] != '%') {
                    result += tempArray[i];
                }
                else {
                    return result;
                }
            }
            key = turnToRight(key);
        }

        return result;
    }

    public static void rotGtid() {
        text = "";
        Scanner in = new Scanner(System.in);
        String answer;
        boolean rightInput = false;

        System.out.print("    Введите сообщение: ");
        text = in.nextLine();

        while (!rightInput) {
            rightInput = true;
            System.out.print("    Введите ширину матрицы (она должна быть четной): ");
            mHeight = Integer.parseInt(in.nextLine());
            if (mHeight % 2 == 1) {
                rightInput = false;
                System.out.printf("    Она должна быть четной.\n");
            }
        }
        matrixNumber = new int[mHeight][mHeight];
        key = new int[mHeight][mHeight];
        if (text.length() < mHeight * mHeight) {
            text += '%';
        }

        System.out.printf("    Введите матрицу: \n");
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mHeight; j++) {
                matrixNumber[i][j] = in.nextInt();
            }
        }

        rightInput = false;
        while (!rightInput) {
            System.out.printf("    Введите ключ, состоящий из 0 и 1: \n");
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mHeight; j++) {
                    key[i][j] = in.nextInt();
                }
            }
            rightInput = true;
            if (!isRightKey(matrixNumber, key)) {
                rightInput = false;
                System.out.printf("    Неправильно введенный ключ: \n");
            }
        }

        rightInput = false;
        while (!rightInput) {
            System.out.print("\n    Вы хотите зашифровать сообщение? Если ответ да, то введите YES, иначе же NO: ");
            answer = "";
            while (answer.isEmpty()) {
                answer = in.nextLine();
            }
            rightInput = true;
            if (answer.equals("YES")) {
                System.out.printf("\n    Зашифрованное сообщение: %s\n", encrypt(matrixNumber, key));
            } else if (answer.equals("NO")) {
                System.out.printf("\n    Расшифрованное сообщение: %s\n", decrypt(matrixNumber, key));
            }
            else {
                System.out.printf("\n    Вы неправильно ответили.\n");
                rightInput = false;
            }
        }
    }
}
