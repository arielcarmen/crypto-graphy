package entities;

import com.google.gson.GsonBuilder;
import hash.HashUtils;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

    private int nonce;


    // Block Constructor.
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash();
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = HashUtils.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                data
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

}

