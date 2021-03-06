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
                            <c:if test="${usuarioMostrar.podeAvaliar(usuarioLogado.id)}">
                                <a href="${pageContext.request.contextPath}/jsp/usuario/avaliar.jsp?id=${usuarioMostrar.id}" class="btn btn-secondary">Avaliar</a>
                            </c:if>
                        </h1>
                        <hr class="my-4"/>
                        
                        <div class="row">
                            <div class="col-3">
                                <img src="${pageContext.request.contextPath}/img/profile.png"/>
                            </div>
                            <div class="col-9 d-flex flex-column justify-content-around">
                                <div class="row">
                                    <div class="col-3"><b>Nome:</b></div>
                                    <div class="col-3">${usuarioMostrar.nome}</div>
                                    <div class="col-3"><b>Sexo:</b></div>
                                    <div class="col-3">${usuarioMostrar.sexo}</div>
                                </div>
                                <div class="row">
                                    <div class="col-3"><b>Cidade de Moradia:</b></div>
                                    <div class="col-3">${usuarioMostrar.cidadeMoradia}</div>
                                    <div class="col-3"><b>País de Moradia:</b></div>
                                    <div class="col-3">${usuarioMostrar.paisMoradia}</div>
                                </div>
                                <div class="row">
                                    <div class="col-3"><b>E-mail:</b></div>
                                    <div class="col-3">${usuarioMostrar.email}</div>
                                    <div class="col-3"><b>Esporte Favorito:</b></div>
                                    <div class="col-3">${usuarioMostrar.esporteFavorito}</div>
                                </div>
                                <div class="row">
                                    <div class="col-3"><b>Disposto a receber?</b></div>
                                    <div class="col-3">
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
                                        <div class="col-3"><b>Quantos?</b></div>
                                        <div class="col-3">${usuarioMostrar.quantReceber}</div>
                                    </c:if>
                                </div>
                            </div>
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
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome}
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
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${avaliacao.horaFormatada()}
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
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome}
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
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${avaliacao.horaFormatada()}
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
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome}
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
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${avaliacao.horaFormatada()}
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
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome}
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
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${avaliacao.horaFormatada()}
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
                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome}</a>
                                            </c:when>
                                            <c:when test="${avaliacao.avaliador.id == usuarioLogado.id}">
                                                ${avaliacao.avaliador.nome}
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
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${avaliacao.horaFormatada()}
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
