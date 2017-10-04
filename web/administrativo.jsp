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
            <div class="pedido">
                <h2>${usuario}</h2>
                <form action="FrontController?action=NovoUsuario" method="post"> <!-- Para qual action isso vai? -->
                    <br>
                    ${list}
                    <br>
                    Status:
                    <select name='status'>
                        <option value='aberto'>Aberto</option>
                        <option value='cancelado'>Cancelado</option>
                        <option value='em-preparo'>Em preparo</option>
                        <option value='saiu-entrega'>Em preparo</option>
                        <option value='recebido'>Recebido</option>
                    </select>
                    <button type="submit" class="btn">Alterar</button>
                </form>    
            </div>
        </div>       
    </body>
</html>
