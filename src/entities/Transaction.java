package entities;

import hash.BlockchainUtils;

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
        String data = BlockchainUtils.keyToString(sender) +
                BlockchainUtils.keyToString(recipient) +
                Float.toString(value) +
                Long.toString(timestamp);

        signature = BlockchainUtils.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature(){
        String data = BlockchainUtils.keyToString(sender) +
                BlockchainUtils.keyToString(recipient) +
                Float.toString(value) +
                Long.toString(timestamp);

        return BlockchainUtils.verifyECDSASig(sender, data, signature);
    }

    public String calculateHash(){

        return BlockchainUtils.applySha256(data);
    }

    public boolean processTransaction(){
        if (!verifySignature()){
            System.out.println("Bad signature");
            return  false;
        }

        for (InputTransaction input: inputs
             ) {
            input.utxo = Blockchain.UTXOs.get(input.outputTransactionId);
        }

        if (calculateInputValue() < value){
            System.out.println("Solde insuffisant");
        }

        if (calculateInputValue() == value) {
            outputs.add(new OutputTransaction(recipient, value, transactionId));
        } else {
            float leftover = calculateInputValue() - value;
            outputs.add(new OutputTransaction(recipient, value, transactionId));
            outputs.add(new OutputTransaction(sender, leftover, transactionId));
        }

        for (OutputTransaction o: outputs
             ) {
            Blockchain.UTXOs.put(o.id, o);
        }

        for (InputTransaction i: inputs
             ) {
            if (i.utxo == null) continue;
            Blockchain.UTXOs.remove(i.utxo.id);
        }

        return true;
    }

    public float calculateInputValue(){
        float total = 0;

        for(InputTransaction i: inputs){
            if (i.utxo == null) continue;
            total += i.utxo.value;
        }

        return total;
    }
}
