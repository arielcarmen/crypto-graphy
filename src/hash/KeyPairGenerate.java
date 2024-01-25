package hash;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyPairGenerate {
        public static KeyPair generateKeyPair() {
            try {
                // Créer une instance de KeyPairGenerator pour l'algorithme RSA
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

                // Initialiser le générateur avec la taille de clé (par exemple, 2048 bits)
                keyPairGenerator.initialize(2048);

                // Générer la paire de clés
                KeyPair keyPair = keyPairGenerator.generateKeyPair();

                return keyPair;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace(); // Gérer l'exception selon votre besoin
                return null;
            }
        }

}
q