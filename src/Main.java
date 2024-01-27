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

    public static int difficulty = 5;

    public static void main(String[] args) {
        addBlock(new Block("First block", "0"));
        addBlock(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        addBlock(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }


}