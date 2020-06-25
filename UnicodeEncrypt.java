package encryptdecrypt;

class UnicodeEncrypt implements Enigma {

    @Override
    public String encryptDecrypt(String message, int key) {
        StringBuilder res = new StringBuilder();

        for (char c : message.toCharArray()) {
            res.append((char) ((c + key) % 255));
        }

        return res.toString();
    }
}