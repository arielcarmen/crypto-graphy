package entities;

import hash.BlockchainUtils;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private long timestamp;

    private int nonce;


    // Block Constructor.
    public Block(ArrayList<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash();
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = BlockchainUtils.applySha256(
                previousHash +
                        Long.toString(timestamp) +
                        Integer.toString(nonce) +
                transactions.toString()
        );
        return calculatedhash;
    }

    // Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public boolean addTransaction(Transaction transaction){
        if (transaction == null){
            return false;
        }
        if (!previousHash.equals("0")){
            if (transaction.processTransaction()){
                System.out.println("Echec de traitement");
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }

}

