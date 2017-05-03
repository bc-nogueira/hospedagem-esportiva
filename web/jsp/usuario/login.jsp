<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Hospedagem Hoteleira</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            <h2>Entrar</h2>
            
            <c:if test="${mensagem != null}">
                <div class="alert alert-danger alert-timer">
                    ${mensagem}
                    <c:remove var="mensagem" scope="session" />
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <div class="form-group">
                    <label for="email">E-mail:</label> 
                    <input type="email" class="form-control" id="email" name="email" required/><br/>
                </div>
                <div class="form-group">
                    <label for="senha">Senha:</label> 
                    <input type="password" class="form-control" id="senha" name="senha" required/><br/>
                </div>
                <button type="submit" class="btn btn-primary">Entrar</button>
            </form>
        </div>
    </body>
</html>
