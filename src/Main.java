import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList alphabet = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                                "n","o","p","q","r","s","t","u","v","w","x","y","z"," ", "!"));
        String encryptedMassage = "";
        String decryptedMassage = "";

        System.out.println("Votre message crypté: ");

        Scanner message_listener = new Scanner(System.in);
        char[] message = message_listener.nextLine().toLowerCase().toCharArray();

        for (int i = 0; i < message.length; i++) {
            String encryptedChar = alphabetCorrespond(String.valueOf(message[i]), alphabet);
            encryptedMassage += encryptedChar;
            if (i < message.length-1){
                encryptedMassage += "-";
            }
        }

        System.out.println("Message crypté: "+encryptedMassage);

        decryptedMassage = decryption(encryptedMassage, alphabet);

        System.out.println("Message décrypté: "+decryptedMassage);

    }

    static String alphabetCorrespond(String c, ArrayList l){
        if(l.contains(c)){
            return Integer.toString(l.indexOf(c)+1);
        } else {
            return "28";
        }
    }

    static String decryption(String code, ArrayList l){
        String decrypetdCode = "";
        String[] cryptedTable =  code.split("-");
        for (int i = 0; i < cryptedTable.length; i++) {
            decrypetdCode += l.get(Integer.parseInt(cryptedTable[i])-1);
        }

        return decrypetdCode;
    }

}