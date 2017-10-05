/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author fhnri
 */
public class Promocao extends Observable{
    private int desconto;
    
    public void setDesconto(int desconto){
        this.desconto = desconto;
        setChanged();
        notifyObservers();
    }
    
    public int getDesconto(){
        return this.desconto;
    }
}
