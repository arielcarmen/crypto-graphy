package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hasher {

    public static String hashMessage(String message) {
        try {
            // Créer une instance de l'algorithme de hachage SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convertir le message en tableau de bytes
            byte[] messageBytes = message.getBytes();

            // Mettre à jour le message digest avec les bytes du message
            md.update(messageBytes);

            // Récupérer le résultat du hachage
            byte[] hashedBytes = md.digest();

            // Convertir le résultat du hachage en une représentation hexadécimale
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Gérer l'exception selon votre besoin
            return null;
        }
    }

    public static void main(String[] args) {
        String message = "Hello, world!";
        String hashedMessage = hashMessage(message);
        System.out.println("Message: " + message);
        System.out.println("SHA-256 Hash: " + hashedMessage);
    }
}
