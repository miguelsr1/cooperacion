package sv.gob.mined.cooperacion.util;

/**
 *
 * @author MISanchez
 */
public class RC4Crypter {

    int[] cipherBox = new int[256];
    int[] cipherKeyArray = new int[256];

    /**
     * Retorna una cadena String encriptada
     * @param key
     * @param data
     * @return
     */
    public String encrypt(String key, String data) {
        return toHex(apply(key, data));
    }

    /**
     * Retorna una cadena String desencriptada
     * @param key
     * @param data
     * @return
     */
    public String decrypt(String key, String data) {
        return apply(key, toStr(data));
    }

    private String apply(String cipherKey, String unencodedText) {
        int z;
        int t = 0;
        int i = 0;
        int cipherBy;
        int tempInt;
        String cipher = "";

        initKey(cipherKey);

        for (int a = 0; a < unencodedText.length(); a++) {
            i = (i + 1) % 256;
            t = (t + this.cipherBox[i]) % 256;
            tempInt = this.cipherBox[i];
            this.cipherBox[i] = this.cipherBox[t];
            this.cipherBox[t] = tempInt;

            z = this.cipherBox[((this.cipherBox[i] + this.cipherBox[t]) % 256)];
            char cipherText = unencodedText.charAt(a);
            cipherBy = cipherText ^ z;
            cipher = cipher + (char) cipherBy;
        }
        return cipher;
    }

    private void initKey(String thisKey) {
        this.cipherBox = new int[256];
        this.cipherKeyArray = new int[256];

        int keyLength;
        int asciiVal;

        keyLength = thisKey.length();

        for (int a = 0; a < 255; a++) {
            char asciiChar = thisKey.charAt(a % keyLength);
            asciiVal = asciiChar;
            this.cipherKeyArray[a] = asciiVal;
            this.cipherBox[a] = a;
        }
        int b = 0;
        for (int a = 0; a < 255; a++) {
            b = (b + this.cipherBox[a] + this.cipherKeyArray[a]) % 256;
            int dataSwap = this.cipherBox[a];
            this.cipherBox[a] = this.cipherBox[b];
            this.cipherBox[b] = dataSwap;
        }
    }

    private static String toHex(String ss) {
        String s = "";
        for (int i = 0; i < ss.length(); i++) {
            int ia = ss.charAt(i);
            int z1 = ia % 16;
            int z2 = (ia - z1) / 16;
            s = s + getHexChar(z2) + getHexChar(z1);
        }
        return s;
    }

    private static char getHexChar(int a) {
        if ((a >= 0) && (a <= 9)) {
            return ("" + a).charAt(0);
        }
        if (a == 10) {
            return 'A';
        }
        if (a == 11) {
            return 'B';
        }
        if (a == 12) {
            return 'C';
        }
        if (a == 13) {
            return 'D';
        }
        if (a == 14) {
            return 'E';
        }
        if (a == 15) {
            return 'F';
        }
        return '\000';
    }

    private static String toStr(String s) {
        String ss = "";
        for (int i = 0; i < s.length(); i += 2) {
            int c1 = toInt(s.charAt(i));
            int c2 = toInt(s.charAt(i + 1));
            ss = ss + (char) (c1 * 16 + c2);
        }
        return ss;
    }

    private static int toInt(char a) {
        if (a == '0') {
            return 0;
        }
        if (a == '1') {
            return 1;
        }
        if (a == '2') {
            return 2;
        }
        if (a == '3') {
            return 3;
        }

        if (a == '4') {
            return 4;
        }
        if (a == '5') {
            return 5;
        }
        if (a == '6') {
            return 6;
        }
        if (a == '7') {
            return 7;
        }

        if (a == '8') {
            return 8;
        }
        if (a == '9') {
            return 9;
        }
        if ((a == 'A') || (a == 'a')) {
            return 10;
        }
        if ((a == 'B') || (a == 'b')) {
            return 11;
        }

        if ((a == 'C') || (a == 'c')) {
            return 12;
        }
        if ((a == 'D') || (a == 'd')) {
            return 13;
        }
        if ((a == 'E') || (a == 'e')) {
            return 14;
        }
        if ((a == 'F') || (a == 'f')) {
            return 15;
        }

        return a;
    }
}