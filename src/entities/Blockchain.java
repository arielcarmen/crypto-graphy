package entities;

import com.google.gson.GsonBuilder;
import entities.Block;
import entities.OutputTransaction;

import java.util.*;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String, OutputTransaction> UTXOs = new HashMap<String, OutputTransaction>();
    public static int difficulty = 5;

    public static Wallet ariel;
    public static Wallet carmen;
    public static Wallet baseWallet;
    public static Transaction transactionOfGenesis;

    public static void main(String[] args) {
        ariel = new Wallet();
        carmen = new Wallet();

        transactionOfGenesis = new Transaction(ariel.publicKey, carmen.publicKey, 1000, null);
        transactionOfGenesis.generateSignature(baseWallet.privateKey);
        transactionOfGenesis.transactionId = "0";
        transactionOfGenesis.outputs.add(new OutputTransaction(transactionOfGenesis.recipient, ));
        UTXOs.put(transactionOfGenesis.outputs.get(0).id, transactionOfGenesis.outputs.get(0));

        Block genesisBlock = new Block(
                null,
                "0"
        );

        genesisBlock.addTransaction()


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