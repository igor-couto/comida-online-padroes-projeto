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
public class PagamentoDinheiro implements FormaPagamento{

    @Override
    public String getNome() {
        return "Pagamento em dinheiro";
    }

    @Override
    public int getFormaPagamento() {
        return 1;
    }

    @Override
    public float precoFinal(float total) {
        return total*0.95f;
    }
    
}
