<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospedagem Esportiva</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            <c:choose>
                <c:when test="${usuarioLogado != null}">
                    <h2>Editar Senha</h2>
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

                    <form action="${pageContext.request.contextPath}/editaSenha" method="POST">
                        <div class="form-group">
                            <label for="senhaAntiga">Senha antiga:</label> 
                            <input type="password" class="form-control" id="senhaAntiga" name="senhaAntiga"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="senhaNova">Nova senha:</label> 
                            <input type="password" class="form-control" id="senhaNova" name="senhaNova"/><br/>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                </c:when>
                <c:when test="${usuarioLogado == null}">
                    <div class="alert alert-danger">
                        Você não está logado.
                    </div>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>
