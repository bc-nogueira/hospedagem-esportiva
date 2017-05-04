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
        
        <!-- Star Rating files -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.rateyo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.rateyo.js"></script>
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
                        <h1 class="display-4 d-flex justify-content-between align-items-center">
                            Visualizar Perfil de outro Usuário
                            <a href="#" class="btn btn-secondary">Adicionar</a>
                        </h1>
                        <hr class="my-4"/>
                        <div class="row">
                            <div class="col-2"><b>Nome:</b></div>
                            <div class="col">${usuarioMostrar.nome}</div>
                            <div class="col-2"><b>Sobrenome:</b></div>
                            <div class="col-4">${usuarioMostrar.sobrenome}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>Local de Moradia:</b></div>
                            <div class="col-4">${usuarioMostrar.localMoradia}</div>
                            <div class="col-2"><b>Esporte Favorito:</b></div>
                            <div class="col-4">${usuarioMostrar.esporteFavorito}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>E-mail:</b></div>
                            <div class="col">${usuarioMostrar.email}</div>
                        </div>
                        <div class="row">
                            <div class="col-2"><b>Disposto a receber?</b></div>
                            <div class="col-4">
                                <c:choose>
                                    <c:when test="${usuarioMostrar.dispostoReceber}">
                                        <span style="color: green">Sim</span>
                                    </c:when>
                                    <c:when test="${!usuarioMostrar.dispostoReceber}">
                                        <span style="color: red">Não</span>
                                    </c:when>
                                </c:choose>
                            </div>
                            <c:if test="${usuarioMostrar.dispostoReceber}">
                                <div class="col-2"><b>Quantos?</b></div>
                                <div class="col-4">${usuarioMostrar.quantReceber}</div>
                            </c:if>
                        </div>
                        <hr/>
                        <c:if test="${recebidasAmigosUsuarioMostrar.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-amigos">
                                <div class="col-3"><h4>Média dos amigos:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaAmigosUsuarioMostrar}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasAmigosUsuarioMostrar}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-amigos">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${avaliacao.avaliador.id != usuarioLogado.id}">
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="card-block">
                                        <h4 class="card-title">
                                            <div class="rate-yo-read" style="margin: auto" data-rateyo-rating="${avaliacao.nota}"></div>
                                        </h4>
                                        <p class="card-text">
                                            ${avaliacao.descricao}
                                        </p>
                                        <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        11/10/2015 <span style="color: red">Colocar data do BD</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${recebidasHospedeUsuarioMostrar.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-hospede">
                                <div class="col-3"><h4>Média Hóspede:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaHospedeUsuarioMostrar}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasHospedeUsuarioMostrar}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-hospede">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${avaliacao.avaliador.id != usuarioLogado.id}">
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="card-block">
                                        <h4 class="card-title">
                                            <div class="rate-yo-read" style="margin: auto" data-rateyo-rating="${avaliacao.nota}"></div>
                                        </h4>
                                        <p class="card-text">
                                            ${avaliacao.descricao}
                                        </p>
                                        <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        11/10/2015 <span style="color: red">Colocar data do BD</span>
                                    </div>
                                </div>
                            </c:forEach>    
                        </c:if>
                        <c:if test="${recebidasAnfitriaoUsuarioMostrar.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-anfitriao">
                                <div class="col-3"><h4>Média Anfitrião:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaAnfitriaoUsuarioMostrar}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasAnfitriaoUsuarioMostrar}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-anfitriao">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${avaliacao.avaliador.id != usuarioLogado.id}">
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="card-block">
                                        <h4 class="card-title">
                                            <div class="rate-yo-read" style="margin: auto" data-rateyo-rating="${avaliacao.nota}"></div>
                                        </h4>
                                        <p class="card-text">
                                            ${avaliacao.descricao}
                                        </p>
                                        <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        11/10/2015 <span style="color: red">Colocar data do BD</span>
                                    </div>
                                </div>
                            </c:forEach>    
                        </c:if>
                        <c:if test="${recebidasLevaEsporteUsuarioMostrar.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-leva">
                                <div class="col-3"><h4>Média ao levar para saída de esporte:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaLevaEsporteUsuarioMostrar}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasLevaEsporteUsuarioMostrar}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-leva">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${avaliacao.avaliador.id != usuarioLogado.id}">
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="card-block">
                                        <h4 class="card-title">
                                            <div class="rate-yo-read" style="margin: auto" data-rateyo-rating="${avaliacao.nota}"></div>
                                        </h4>
                                        <p class="card-text">
                                            ${avaliacao.descricao}
                                        </p>
                                        <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        11/10/2015 <span style="color: red">Colocar data do BD</span>
                                    </div>
                                </div>
                            </c:forEach>    
                        </c:if>
                        <c:if test="${recebidasParticipaEsporteUsuarioMostrar.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-participa">
                                <div class="col-3"><h4>Média ao participar de saída de esporte:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaParticipaEsporteUsuarioMostrar}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasParticipaEsporteUsuarioMostrar}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-participa">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${avaliacao.avaliador.id != usuarioLogado.id}">
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="card-block">
                                        <h4 class="card-title">
                                            <div class="rate-yo-read" style="margin: auto" data-rateyo-rating="${avaliacao.nota}"></div>
                                        </h4>
                                        <p class="card-text">
                                            ${avaliacao.descricao}
                                        </p>
                                        <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        11/10/2015 <span style="color: red">Colocar data do BD</span>
                                    </div>
                                </div>
                            </c:forEach>    
                        </c:if>
                    </div>
                    <c:remove var="usuarioMostrar" scope="session"/>
                    <c:remove var="recebidasAmigosUsuarioMostrar" scope="session"/>
                    <c:remove var="notaMediaAmigosUsuarioMostrar" scope="session"/>
                    <c:remove var="recebidasHospedeUsuarioMostrar" scope="session"/>
                    <c:remove var="notaMediaHospedeUsuarioMostrar" scope="session"/>
                    <c:remove var="recebidasAnfitriaoUsuarioMostrar" scope="session"/>
                    <c:remove var="notaMediaAnfitriaoUsuarioMostrar" scope="session"/>
                    <c:remove var="recebidasLevaEsporteUsuarioMostrar" scope="session"/>
                    <c:remove var="notaMediaLevaEsporteUsuarioMostrar" scope="session"/>
                    <c:remove var="recebidasParticipaEsporteUsuarioMostrar" scope="session"/>
                    <c:remove var="notaMediaParticipaEsporteUsuarioMostrar" scope="session"/>
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
