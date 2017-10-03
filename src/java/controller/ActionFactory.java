package controller;

import java.util.List;
import model.Produto;
import percistence.ProdutoDAO;

public class ActionFactory {

    static Action create(String action) {
        Action actionObject = null;
        String nomeClasse = "action." + action + "Action";
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch( Exception ex){
            System.out.println(ex);
            return null;
        }
        
        if( !(objeto instanceof Action) ){
            return null;
        }
        
        actionObject = (Action) objeto;
        return actionObject;
    }
    
}
