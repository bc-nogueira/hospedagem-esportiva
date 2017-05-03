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
                    <c:if test="${mensagem != null}">
                        <div class="alert alert-success alert-timer">
                            ${mensagem}
                            <c:remove var="mensagem" scope="session" />
                        </div>
                    </c:if>

                    <div class="jumbotron">
                        <h1 class="display-4">Perfil do Usuário</h1>
                        <hr class="my-4"/>
                        <div class="row">
                            <div class="col-2"><b>Nome:</b></div>
                            <div class="col">${usuarioLogado.nome}</div>
                            <div class="col-2"><b>Sobrenome:</b></div>
                            <div class="col-4">${usuarioLogado.sobrenome}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>Local de Moradia:</b></div>
                            <div class="col-4">${usuarioLogado.localMoradia}</div>
                            <div class="col-2"><b>Esporte Favorito:</b></div>
                            <div class="col-4">${usuarioLogado.esporteFavorito}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>E-mail:</b></div>
                            <div class="col">${usuarioLogado.email}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>Disposto a receber?</b></div>
                            <div class="col-4">${usuarioLogado.dispostoReceber}</div>
                            <c:if test="${usuarioLogado.dispostoReceber}">
                                <div class="col-2"><b>Quantos?</b></div>
                                <div class="col-4">${usuarioLogado.quantReceber}</div>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-2">Média dos amigos:</div>
                            <div class="col-2">5</div>
                            <div class="col-2">Média dos amigos:</div>
                            <div class="col-2">5</div>
                            <div class="col-2">Média dos amigos:</div>
                            <div class="col-2">5</div>
                        </div>
                        <div class="row">
                            <div class="col-2">Média dos amigos:</div>
                            <div class="col-2">5</div>
                            <div class="col-2">Média dos amigos:</div>
                            <div class="col-2">5</div>
                        </div>    
                    </div>
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
