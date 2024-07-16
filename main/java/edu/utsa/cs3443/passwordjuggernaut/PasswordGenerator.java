package edu.utsa.cs3443.passwordjuggernaut;

import java.util.Random;

public class PasswordGenerator {
    private boolean includeNumbers;
    private boolean includeSpecialChars;
    private boolean includeUppercase;
    private int length;

    public PasswordGenerator(boolean includeNumbers, boolean includeSpecialChars, boolean includeUppercase, int length) {
        this.includeNumbers = includeNumbers;
        this.includeSpecialChars = includeSpecialChars;
        this.includeUppercase = includeUppercase;
        this.length = length;
    }

    public String generate() {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder charPool = new StringBuilder(lowerCase);

        if (includeNumbers) {
            charPool.append(numbers);
        }

        if (includeSpecialChars) {
            charPool.append(specialChars);
        }

        if (includeUppercase) {
            charPool.append(upperCase);
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }

        return password.toString();
    }
}