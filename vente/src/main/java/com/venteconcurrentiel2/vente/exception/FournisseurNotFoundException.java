package com.venteconcurrentiel2.vente.exception;

public class FournisseurNotFoundException extends RuntimeException {
    public FournisseurNotFoundException(String mess){
        super(mess);
    }
}
