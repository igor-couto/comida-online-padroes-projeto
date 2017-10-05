/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fhnri
 */
public interface FormaPagamento {
    public String getNome();
    public int getFormaPagamento();
    public float precoFinal(float total);
}
