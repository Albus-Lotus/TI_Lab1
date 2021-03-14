public class VigenerCipher {
    private static char[][] matrixCipher;

    public static void createMatrix() {
        matrixCipher = new char[26][26];

        for (int i = 0; i < 26; i ++) {
            for (int j = 0; j < 26; j++) {
                if (i + j < 26) {
                    matrixCipher[i][j] = (char) (65 + i + j);
                }
                else {
                    matrixCipher[i][j] = (char) (65 + i + j - 26);
                }
            }
        }
    }

    public static String encryptVigenerCipher(String text, String key) {
        int index = 0;

        if (key.length() < text.length()) {
            while (key.length() != text.length()) {
                if (index >= text.length()) {
                    index = 0;
                }
                key += text.charAt(index++);
            }
        }

        String result = "";

        for (int i = 0; i < text.length(); i++) {
                result += matrixCipher[key.charAt(i) - 65][text.charAt(i) - 65];
        }

        return result;
    }

    private static char findPlace(int i, char character) {
        for (int j = 0; j < 27; j++) {
            if (matrixCipher[i][j] == character) {
                return (char) (j + 65);
            }
        }

        return ' ';
    }

    public static String decryptVigenerCipher(String text, String key) {
        int numRepeat = text.length() / key.length();
        int keyLen = key.length();
        String result = "";

        for (int i = 0; i < numRepeat; i++) {

        }

        for (int i = 0; i < text.length(); i++) {
            result += findPlace(key.charAt(i) - 65, text.charAt(i));
            key += result.charAt(i);
        }

        return result;
    }
}
