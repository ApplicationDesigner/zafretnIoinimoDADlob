/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 *
 * @author Marcelo
 */
public class JPTableroPokerComboItem {
    private String key;
    private int value;

    public JPTableroPokerComboItem(String key, int value) {
        this.key = key;
        this.value = value;
    }

    
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key;
    }
    
    
    
    
}
