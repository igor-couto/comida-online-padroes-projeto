<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

	<link rel="stylesheet" type="text/css" href="style.css" />
        <title>Comida Online</title>
    </head>
    <body>
        <div class="log-form">
          <h2>Cardápio</h2>
          
              <ul>
                  ${list}
              </ul>
          
        </div>       
        <div class="carrinho">
          <h2>Carrinho</h2>
          
              <ul>
                  ${cart}
              </ul>

              <form action="FrontController?action=FechaPedido" method="post">
                  
                  Forma de Pagamento:
                  <br>
                  <input type="radio" id="pagamentoDinheiro"
                    name="formaPagamento" value="dinheiro" checked>
                  <label for="contactChoice1">Dinheiro</label>

                  <input type="radio" id="pagamentoCartao"
                    name="formaPagamento" value="cartao">
                  <label for="contactChoice2">Cartão</label>
                  <br>
                  
                  <input type="submit" value="Fechar pedido"/>
              </form>
        </div>
        
    </body>
</html>