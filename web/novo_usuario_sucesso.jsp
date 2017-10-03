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
          <h2>Faça Login na sua conta!</h2>
          <form action="FrontController?action=Login" method="post">
                <input type="text" title="email" placeholder="email" name="email"/>
                <input type="password" title="senha" placeholder="senha" name="senha"/>
                <button type="submit" class="btn">Entrar</button>
                <a class="forgot" href="novo_usuario">Criar uma conta</a>
                <p class="bg-danger" style="margin-top: 10%;"> Conta criada com sucesso! Por favor efetue login.</p>
          </form>
        </div>       
        
    </body>
</html>
