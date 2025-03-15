import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();
        
        System.out.print("Do you want to include special characters? (yes/no): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("yes");
        
        System.out.print("How many passwords do you want to generate? ");
        int numberOfPasswords = scanner.nextInt();
        
        System.out.println("Generated passwords:");
        StringBuilder passwords = new StringBuilder();
        
        for (int i = 0; i < numberOfPasswords; i++) {
            String password = generatePassword(length, includeSpecial);
            System.out.println(password);
            passwords.append(password).append("\n");
        }
        
        savePasswordsToFile(passwords.toString());
    }

    public static String generatePassword(int length, boolean includeSpecial) {
        SecureRandom random = new SecureRandom();
        String characterPool = includeSpecial ? ALL_CHARACTERS : LOWERCASE + UPPERCASE + DIGITS;
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }
        
        return password.toString();
    }

    public static void savePasswordsToFile(String passwords) {
        try (FileWriter writer = new FileWriter("generated_passwords.txt")) {
            writer.write(passwords);
            System.out.println("Passwords have been saved to 'generated_passwords.txt'.");
        } catch (IOException e) {
            System.out.println("Error saving passwords: " + e.getMessage());
        }
    }
}
