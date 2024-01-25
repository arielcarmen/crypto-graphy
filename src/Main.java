import java.security.KeyPair;
import java.util.Scanner;

import static hash.KeyPairGenerate.generateKeyPair;
import static hash.SHA256Hasher.hashMessage;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message;

        System.out.println("Mesage à hasher: ");
        message = sc.nextLine();

        System.out.println(hashMessage(message));

        // Générer une paire de clés
        KeyPair keyPair = generateKeyPair();

        // Récupérer la clé publique et privée
        System.out.println("Génération des clés...");
        if (keyPair != null) {
            System.out.println("Clé publique: " + keyPair.getPublic());
            System.out.println("Clé privée: " + keyPair.getPrivate());
        }

    }

}