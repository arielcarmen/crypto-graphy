package entities;

import hash.HashUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;
    public ArrayList<InputTransaction> inputs = new ArrayList<InputTransaction>();
    public ArrayList<OutputTransaction> outputs = new ArrayList<OutputTransaction>();
    public long timestamp;

    public Transaction(PublicKey sender, PublicKey recipient, float value, ArrayList<InputTransaction> inputs){
        this.sender = sender;
        this.recipient = recipient;
        this.value = value;
        this.inputs = inputs;
    }

    public void generateSignature(PrivateKey privateKey){
        String data = HashUtils.keyToString(sender) +
                HashUtils.keyToString(recipient) +
                Float.toString(value) +
                Long.toString(timestamp);

        signature = HashUtils.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature(){
        String data = HashUtils.keyToString(sender) +
                HashUtils.keyToString(recipient) +
                Float.toString(value) +
                Long.toString(timestamp);

        return HashUtils.verifyECDSASig(sender, data, signature);
    }

    public String calculateHash(){

        return HashUtils.applySha256(data);
    }

    public boolean processTransaction(){
        if (verifySignature()){

        } el
    }
}
