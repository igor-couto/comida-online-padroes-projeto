/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ice
 */
public abstract class Funcionario {
    
    protected ArrayList<PedidoStatus> podeCancelar = new ArrayList();
    
    protected Funcionario superior;
    
    protected String name;

    public Funcionario getSuperior() {
        return superior;
    }

    public void setSuperior(Funcionario superior) {
        this.superior = superior;
    }
    
    public abstract String getCargo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String cancelarPedido(Pedido pedido){
        
        if(podeCancelar.contains(pedido.getStatus())){
        
            return "Cancelado";
        
        }else{
        
            return superior.cancelarPedido(pedido);
            
            
        }
    }
    
    
}
