package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        String mode = "";
        int key = 0;
        String alg = "";
        String in = "";
        String out = "";
        StringBuilder data = new StringBuilder();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode": mode = args[i + 1].toLowerCase();
                break;
                case "-key":
                    try {
                        key = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key!");
                    }
                break;
                case "-alg": alg = args[i + 1];
                break;
                case "-in": in = args[i + 1];
                break;
                case "-out": out = args[i + 1];
                break;
                case "-data":
                    for (int j = i + 1; j < args.length; j++) {
                        if (!args[j].endsWith("\"")) {
                            data.append(args[j]);
                            break;
                        } else {
                            data.append(args[j]);
                        }
                    }
            }
        }

        if (!in.isBlank()) {
            try (Scanner scan = new Scanner(new File(in))) {
                while (scan.hasNext()) {
                    message.append(scan.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            message = data;
        }

        Context ctx = null;

        switch (mode) {
            case "enc":
                if (alg.equals("shift"))
                    ctx = new Context(new ShiftEncrypt());
                else if (alg.equals("unicode"))
                    ctx = new Context(new UnicodeEncrypt());
                break;
            case "dec":
                if (alg.equals("shift"))
                    ctx = new Context(new ShiftDecrypt());
                else if (alg.equals("unicode"))
                    ctx = new Context(new UnicodeDecrypt());
            default: System.out.println("Invalid algorithm");
        }

        if (out.isBlank()) {
            assert ctx != null;
            System.out.println(ctx.execute(message.toString(), key, out));
        } else {
            try (FileWriter writer = new FileWriter(out)) {
                assert ctx != null;
                writer.write(ctx.execute(message.toString(), key, out));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}