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
        <div class="container">
            <div>
                <form action="FrontController?action=NovaPromocao" method="POST">
                    <label>Digite o valor da nova promoção</label>
                    <input type="text" name="desconto"/>
                    <input type="submit" value="Enviar"/>
                </form>
            </div>
            <div class="pedidosAdmin">
                <h2>Pedidos abertos</h2>
                <br>
                ${list}
                <br>
            </div>
        </div>       
    </body>
</html>
