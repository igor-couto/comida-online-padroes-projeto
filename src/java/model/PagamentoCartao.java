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
public class PagamentoCartao implements FormaPagamento{

    @Override
    public String getNome() {
        return "Cartão de crédito";
    }

    @Override
    public int getFormaPagamento() {
        return 2;
    }

    @Override
    public float precoFinal(float total) {
        return total;
    }
    
}
