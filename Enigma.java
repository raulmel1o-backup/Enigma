package com.raulmel1o;

import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

class Enigma {
	public static String encryption(String input, int key) {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			char newCh = (char) ((ch + key) % 255);
			result.append(newCh);
		}
		
		return result.toString();
	}
	
	public static String decryption(String input, int key) {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			char newCh = (char) ((ch - key) % 255);
			result.append(newCh);
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		StringBuilder data = new StringBuilder();
		StringBuilder input = new StringBuilder();
		String mode = new String();
		String key = new String();
		String in = new String();
		String out = new String();
		String res = new String();
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-mode")) {
				mode = args[i + 1].toLowerCase();
			} else if (args[i].equals("-key")) {
				key = args[i + 1];
			} else if (args[i].equals("-in")) {
				in = args[i + 1];
			} else if (args[i].equals("-out")) {
				out = args[i + 1];
			} else if (args[i].equals("-data")) {
				for (int j = i; j < args.length - 1; j++) {
					if (args[j + 1].startsWith("-")) {
						break;
					} else {
						data.append(args[j + 1]).append(" ");
					}
				}
			}
		}
		
		if (!in.isBlank()) {
			try (Scanner scan = new Scanner(new File(in))) {
				while (scan.hasNext()) {
					input.append(scan.nextLine());
				}
			} catch (FileNotFoundException e) {
				System.out.printf("An exception was found: %s\n", e.getMessage());
			}
		} else if (in.isBlank()) {
			input.append(data.substring(0, data.length() - 1));
		} else {
			input.append("");
		}
		
		
		if (mode.equals("dec")) {
			res = decryption(input.toString(), Integer.parseInt(key));
		} else if (mode.equals("enc") || mode.isBlank()) {
			res = encryption(input.toString(), Integer.parseInt(key));
		} else {
			System.out.println("Unknown operation");
		}
		
		if (out.isBlank()) {
			System.out.println(res);
		} else {
			try (FileWriter writer = new FileWriter(out)) {
				writer.write(res);
			} catch (IOException e) {
				System.out.printf("An exception was found: %s\n", e.getMessage());
			}
		}
	}
}