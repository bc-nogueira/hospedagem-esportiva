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
                    <h2>Editar Usuário</h2>
                    <a href="${pageContext.request.contextPath}/jsp/usuario/editSenha.jsp">Alterar senha</a>
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

                    <form action="${pageContext.request.contextPath}/editaUsuario" method="POST">
                        <div class="form-group">
                            <label for="nome">Nome:</label> 
                            <input type="text" class="form-control" id="nome" 
                                   name="nome" value="${usuarioLogado.nome}"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="sobrenome">Sobrenome:</label> 
                            <input type="text" class="form-control" id="sobrenome" 
                                   name="sobrenome" value="${usuarioLogado.sobrenome}"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="localMoradia">Local de moradia:</label> 
                            <input type="text" class="form-control" id="localMoradia" 
                                   name="localMoradia"  value="${usuarioLogado.localMoradia}"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="esporteFavorito">Esporte Favorito:</label> 
                            <input type="text" class="form-control" id="esporteFavorito" 
                                   name="esporteFavorito" value="${usuarioLogado.esporteFavorito}"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail:</label> 
                            <input type="email" class="form-control" id="email" 
                                   name="email" value="${usuarioLogado.email}"/><br/>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" id="dispostoReceber" 
                                       name="dispostoReceber" <c:if test="${usuarioLogado.dispostoReceber}">checked</c:if>>
                                Aceita receber outros usuários em viagem?
                            </label>
                        </div>
                        <div class="form-group" id="divQuantReceber" <c:if test="${!usuarioLogado.dispostoReceber}">style="display: none"</c:if>>
                            <label for="quantReceber">Quantos?</label> 
                            <input type="number" class="form-control" id="quantReceber" 
                                   name="quantReceber" value="${usuarioLogado.dispostoReceber ? usuarioLogado.quantReceber : 1}" min="1"/><br/>
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
