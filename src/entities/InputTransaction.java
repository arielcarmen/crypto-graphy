package entities;

import java.security.PublicKey;

public class InputTransaction{
    public String outputTransactionId;
    public OutputTransaction utxo;
    public InputTransaction(String outputTransactionId) {
        this.outputTransactionId = outputTransactionId;
    }


}
