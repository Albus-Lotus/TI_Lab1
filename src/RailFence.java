public class RailFence {

    public static String encryptRailFence(String text, int key) {
        char[][] rail = new char[key][text.length()];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean dirDown = false;
        int row = 0;
        int column = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }

            rail[row][column++] = text.charAt(i);

            if (dirDown) {
                row++;
            } else {
                row--;
            }
        }

        String result = "";

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != '\n') {
                    result += rail[i][j];
                }
            }
        }

        return result;
    }

    public static String decryptRailFence(String text, int key) {
        char[][] rail = new char[key][text.length()];

        boolean dirDown = false;
        int row = 0;
        int column = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }

            rail[row][column++] = '%';

            if (dirDown) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0;

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] == '%') {
                    rail[i][j] = text.charAt(index++);
                }
            }
        }

        String result = "";

        dirDown = false;
        row = 0;
        column = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1) {
                dirDown = !dirDown;
            }

            result += rail[row][column++];

            if (dirDown) {
                row++;
            } else {
                row--;
            }
        }

        return result;
    }
}
