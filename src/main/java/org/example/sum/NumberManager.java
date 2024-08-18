package org.example.sum;

public class NumberManager {
    private final NumberGetter numberGetter;

    public NumberManager(NumberGetter numberGetter){
        this.numberGetter = numberGetter;
    }

    public int getNumericValue(){
        return numberGetter.get();
    }
}
