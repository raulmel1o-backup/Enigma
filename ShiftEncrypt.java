package encryptdecrypt;

class ShiftEncrypt implements Enigma {
    @Override
    public String encryptDecrypt(String message, int key) {
        StringBuilder res = new StringBuilder();
        char ch = ' ';

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    ch = (char) ((c + key - 65) % 26 + 65);
                } else {
                    ch = (char) ((c + key - 97) % 26 + 97);
                }
            } else {
                ch = c;
            }

            res.append(ch);
        }

        return res.toString();
    }
}