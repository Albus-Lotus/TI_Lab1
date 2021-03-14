import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static void railFence() {
        String text;
        int key;
        Scanner in = new Scanner(System.in);
        String answer;
        boolean rightInput = false;

        System.out.print("    Введите сообщение: ");
        text = in.nextLine();
        System.out.print("    Введите ключ: ");
        key = Integer.parseInt(in.nextLine());

        while (!rightInput) {
            System.out.print("\n    Вы хотите зашифровать сообщение? Если ответ да, то введите YES, иначе же NO: ");
            answer = "";
            while (answer.isEmpty()) {
                answer = in.nextLine();
            }
            rightInput = true;
            if (answer.equals("YES")) {
                System.out.printf("\n    Зашифрованное сообщение: %s\n", RailFence.encryptRailFence(text, key));
            } else if (answer.equals("NO")) {
                System.out.printf("\n    Расшифрованное сообщение: %s\n", RailFence.decryptRailFence(text, key));
            }
            else {
                System.out.printf("\n    Вы неправильно ответили.\n");
                rightInput = false;
            }
        }
    }

    private static void columnMethod() {
        String text;
        String key;
        Scanner in = new Scanner(System.in);
        String answer;
        boolean rightInput = false;

        System.out.print("    Введите сообщение: ");
        text = in.nextLine();
        System.out.print("    Введите ключ: ");
        key = in.nextLine();

        while (!rightInput) {
            System.out.print("\n    Вы хотите зашифровать сообщение? Если ответ да, то введите YES, иначе же NO: ");
            answer = "";
            while (answer.isEmpty()) {
                answer = in.nextLine();
            }
            rightInput = true;
            if (answer.equals("YES")) {
                System.out.printf("\n    Зашифрованное сообщение: %s\n", ColumnMethod.encryptColumn(text, key.toUpperCase()));
            } else if (answer.equals("NO")) {
                System.out.printf("\n    Расшифрованное сообщение: %s\n", ColumnMethod.decryptColumn(text, key.toUpperCase()));
            }
            else {
                System.out.printf("\n    Вы неправильно ответили.\n");
                rightInput = false;
            }
        }
    }

    private static void vigenerCipher() {
        String text;
        String key;
        Scanner in = new Scanner(System.in);
        String answer;
        boolean rightInput = false;

        VigenerCipher.createMatrix();

        System.out.print("    Введите сообщение: ");
        text = in.nextLine();
        text = text.replaceAll(" ", "");
        System.out.print("    Введите ключ: ");
        key = in.nextLine();

        while (!rightInput) {
            System.out.print("\n    Вы хотите зашифровать сообщение? Если ответ да, то введите YES, иначе же NO: ");
            answer = "";
            while (answer.isEmpty()) {
                answer = in.nextLine();
            }
            rightInput = true;
            if (answer.equals("YES")) {
                System.out.printf("\n    Зашифрованное сообщение: %s\n", VigenerCipher.encryptVigenerCipher(text.toUpperCase(), key.toUpperCase()));
            } else if (answer.equals("NO")) {
                System.out.printf("\n    Расшифрованное сообщение: %s\n", VigenerCipher.decryptVigenerCipher(text.toUpperCase(), key.toUpperCase()));
            }
            else {
                System.out.printf("\n    Вы неправильно ответили.\n");
                rightInput = false;
            }
        }
    }

    private static void menu() {
        boolean rightInput = false;
        Scanner in = new Scanner(System.in);
        int method;
        boolean repeat = true;

        while (repeat) {
            String trash = in.nextLine();

            rightInput = false;

            System.out.printf("\n        Вам предложен выбор метода: \n");
            System.out.println("    1. Метод железнодорожной изгороди.");
            System.out.println("    2. Столбцовый метод.");
            System.out.println("    3. Метод поворащивающейся решетки.");
            System.out.println("    4. Шифр Вижинера.");
            System.out.println("    5. Выход.");

            while (!rightInput) {
                System.out.printf("\n    Напишите номер метода, который хотите использовать: ");
                method = Integer.parseInt(in.nextLine());
                rightInput = true;
                switch (method) {
                    case 1:
                        railFence();
                        break;
                    case 2:
                        columnMethod();
                        break;
                    case 3:
                        RotatingGrid.rotGtid();
                        break;
                    case 4:
                        vigenerCipher();
                        break;
                    case 5:
                        repeat = false;
                        break;
                    default:
                        rightInput = false;
                        System.out.printf("\n    Вы неправильно ввели номер метода.\n");
                }
            }
        }
    }

    public static void main(String Args[]) {
        menu();
    }
}
