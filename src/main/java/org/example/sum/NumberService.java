package org.example.sum;

public class NumberService {
    private Number number;

    public NumberService(Number number){
        this.number = number;
    }

    public int getNumber(){
        return number.get();
    }
}
