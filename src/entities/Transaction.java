package entities;

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
}
