import java.util.Arrays;

public class ColumnMethod {
    private static char[][] col;
    private static String tempKey;
    private static int numbRows;
    private static int index;
    private static int haveBorder;

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();

        Arrays.sort(tempArray);

        StringBuilder sb = new StringBuilder(tempArray.length);

        for (Character c : tempArray) {
            sb.append(c.charValue());
        }
        return sb.toString();
    }

    public static String encryptColumn(String text, String key) {
        int border = text.length() % key.length();

        if (border == 0) {
            haveBorder = 0;
        }
        else {
            haveBorder = 1;
        }
        numbRows = (int)(text.length() / key.length());
        col = new char[2 + numbRows + haveBorder][key.length()];

        for (int j = 0; j < key.length(); j++) {
            col[0][j] = key.charAt(j);
        }

        index = 0;
        for (int i = 2; i < numbRows + haveBorder + 2; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (index < text.length()) {
                    col[i][j] = text.charAt(index++);
                }
            }
        }

        tempKey = key;
        tempKey = sortString(tempKey);

        for (int j = 0; j < key.length(); j++) {
            col[1][j] = (char)tempKey.indexOf(key.charAt(j));
        }

        String result = "";

        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (i == (int)col[1][j]) {
                    for (int k = 2; k < numbRows + 2; k++) {
                        result += col[k][j];
                    }
                    if (j < border && haveBorder == 1) {
                        result += col[numbRows + 2][j];
                    }

                }
            }
        }

        return result;
    }

    public static String decryptColumn(String text, String key) {
        int border = text.length() % key.length();

        if (border == 0) {
            haveBorder = 0;
        }
        else {
            haveBorder = 1;
        }
        numbRows = (int)(text.length() / key.length());
        col = new char[2 + numbRows + haveBorder][key.length()];

        for (int j = 0; j < key.length(); j++) {
            col[0][j] = key.charAt(j);
        }

        tempKey = key;
        tempKey = sortString(tempKey);

        for (int j = 0; j < key.length(); j++) {
            col[1][j] = (char)tempKey.indexOf(key.charAt(j));
        }

        index = 0;
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (i == (int)col[1][j]) {
                    for (int k = 2; k < numbRows + 2; k++) {
                        if (index < text.length()) {
                            col[k][j] = text.charAt(index++);
                        }
                    }
                    if (j < border && haveBorder == 1 && index < text.length()) {
                        col[numbRows + 2][j] = text.charAt(index++);
                    }
                }
            }
        }

        String result = "";

        index = 0;
        for (int i = 2; i < numbRows + haveBorder + 2; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (index < text.length()) {
                    result += col[i][j];
                    index++;
                }
            }
        }

        return result;
    }
}
