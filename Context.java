package encryptdecrypt;

class Context {

    private Enigma strategy;

    Context(Enigma strategy) {
        this.strategy = strategy;
    }

    public String execute(String message, int key, String out) {
        return strategy.encryptDecrypt(message, key);
    }
}