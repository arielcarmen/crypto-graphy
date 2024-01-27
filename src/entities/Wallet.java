package entities;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;
    public HashMap<String, OutputTransaction> walletUTXOs = new HashMap<String, OutputTransaction>();

    public Wallet(){
        generateKeyPair();
    }
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random); //256
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public float balance(){
        float balance = 0;
        for (Map.Entry<String, OutputTransaction> out: Blockchain.UTXOs.entrySet()
             ) {
            OutputTransaction utxo = out.getValue();
            if (utxo.transactionIsMine(publicKey)){
                walletUTXOs.put(utxo.id, utxo);
                balance += utxo.value;
            }
        }
        return balance;
    }

    public Transaction sendMoney(float value, PublicKey recipient){
        if (balance() < value){
            System.out.println("Fonds insuffisants");
            return null;
        }

        float somme = 0;

        ArrayList<InputTransaction> inputs = new ArrayList<InputTransaction>();
        for (Map.Entry<String, OutputTransaction> out: walletUTXOs.entrySet()
        ) {
            OutputTransaction utxo = out.getValue();
            inputs.add(new InputTransaction(utxo.id));
            somme += utxo.value;
            if (somme >= value) break;
        }

        Transaction newTransaction = new Transaction(publicKey, recipient, value, inputs);
        newTransaction.generateSignature(privateKey);
        for (InputTransaction i: inputs
        ) {
            walletUTXOs.remove(i.utxo.id);
        }

        return newTransaction;
    }


}
