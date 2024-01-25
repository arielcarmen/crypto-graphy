import com.google.gson.GsonBuilder;
import entities.Block;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static void main(String[] args) {
        blockchain.add(new Block("First block", "0"));
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }


}