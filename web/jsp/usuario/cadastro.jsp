<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro - Hospedagem Hoteleira</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/usuario/cadastro.js"></script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        <div class="container container-principal">
            <h2>Novo Usuário</h2>
            <c:if test="${mensagem != null}">
                <div class="alert alert-danger">
                    ${mensagem}
                    <c:remove var="mensagem" scope="session" />
                    <c:if test="${!parametrosVazios.isEmpty()}">
                        <ul>
                        <c:forEach var="parametro" items="${parametrosVazios}">
                            <li>${parametro}</li>
                        </c:forEach>
                        </ul>
                    </c:if>
                    <c:remove var="parametrosVazios" scope="session" />
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/executa" method="POST">
                <input type="hidden" name="acao" value="NovoUsuario"/>
                <div class="form-group">
                    <label for="nome">Nome:</label> 
                    <input type="text" class="form-control" id="nome" name="nome"/><br/>
                </div>
                <div class="form-group">
                    <label for="sobrenome">Sobrenome:</label> 
                    <input type="text" class="form-control" id="sobrenome" name="sobrenome"/><br/>
                </div>
                <div class="form-group">
                    <label for="localMoradia">Local de moradia:</label> 
                    <input type="text" class="form-control" id="localMoradia" name="localMoradia"/><br/>
                </div>
                <div class="form-group">
                    <label for="esporteFavorito">Esporte Favorito:</label> 
                    <input type="text" class="form-control" id="esporteFavorito" name="esporteFavorito"/><br/>
                </div>
                <div class="form-group">
                    <label for="email">E-mail:</label> 
                    <input type="email" class="form-control" id="email" name="email"/><br/>
                </div>
                <div class="form-group">
                    <label for="senha">Senha:</label> 
                    <input type="password" class="form-control" id="senha" name="senha"/><br/>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" id="dispostoReceber" name="dispostoReceber">
                        Aceita receber outros usuários em viagem?
                    </label>
                </div>
                <div class="form-group" id="divQuantReceber" style="display: none">
                    <label for="quantReceber">Quantos?</label> 
                    <input type="number" class="form-control" id="quantReceber" name="quantReceber" value="1" min="1"/><br/>
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
    </body>
    
<!--
    private Boolean dispostoReceber;
    private Integer quantReceber;
-->
</html>
