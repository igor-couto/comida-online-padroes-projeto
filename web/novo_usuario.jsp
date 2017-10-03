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
          <h2>Bem vindo! Crie sua conta</h2>
          <form action="FrontController?action=NovoUsuario" method="post">
              <input type="text" name ="nome" title="nome" placeholder="nome" />
              <input type="text" name ="email" title="email" placeholder="email" />
              <input type="password" name ="senha" title="senha" placeholder="senha" />
              Deseja receber notificações de promoções?<input type="checkbox" name="recebeNotificacao" value="true">
              <button type="submit" class="btn">Criar</button>
              <a class="forgot" href="index.jsp">Voltar</a>
          </form>
        </div>       
        
    </body>
</html>
