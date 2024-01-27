package entities;

import hash.HashUtils;

import java.security.PublicKey;

public class OutputTransaction{
    public String id;
    public PublicKey recipient;
    public float value;
    public String parentTransactionId;

    public OutputTransaction(PublicKey recipient, float value, String parentTransactionId) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = HashUtils.applySha256(
                HashUtils.keyToString(recipient) +
                        Float.toString(value) +
                        parentTransactionId
        );
    }

    public boolean transactionIsMine(PublicKey publicKey){
        return publicKey.equals(recipient);
    }
}
