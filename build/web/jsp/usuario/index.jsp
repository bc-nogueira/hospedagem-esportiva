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
                            Perfil do Usuário
                            <a href="${pageContext.request.contextPath}/jsp/usuario/edit.jsp" class="btn btn-secondary">Editar</a>
                        </h1>
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
                            <div class="col-4">
                                <c:choose>
                                    <c:when test="${usuarioLogado.dispostoReceber}">
                                        <span style="color: green">Sim</span>
                                    </c:when>
                                    <c:when test="${!usuarioLogado.dispostoReceber}">
                                        <span style="color: red">Não</span>
                                    </c:when>
                                </c:choose>
                            </div>
                            <c:if test="${usuarioLogado.dispostoReceber}">
                                <div class="col-2"><b>Quantos?</b></div>
                                <div class="col-4">${usuarioLogado.quantReceber}</div>
                            </c:if>
                        </div>
                        <hr/>
                        <c:if test="${recebidasAmigos.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-amigos">
                                <div class="col-3"><h4>Média dos amigos:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaAmigos}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasAmigos}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-amigos">
                                    <div class="card-header">
                                        <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
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
                        <c:if test="${recebidasHospede.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-hospede">
                                <div class="col-3"><h4>Média Hóspede:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaHospede}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasHospede}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-hospede">
                                    <div class="card-header">
                                        <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
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
                        <c:if test="${recebidasAnfitriao.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-anfitriao">
                                <div class="col-3"><h4>Média Anfitrião:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaAnfitriao}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasAnfitriao}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-anfitriao">
                                    <div class="card-header">
                                        <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
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
                        <c:if test="${recebidasLevaEsporte.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-leva">
                                <div class="col-3"><h4>Média ao levar para saída de esporte:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaLevaEsporte}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasLevaEsporte}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-leva">
                                    <div class="card-header">
                                        <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
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
                        <c:if test="${recebidasParticipaEsporte.size() > 0}">
                            <div class="row margin-bottom-20" id="media-notas-participa">
                                <div class="col-3"><h4>Média ao participar de saída de esporte:</h4></div>
                                <div class="col">
                                    <div class="rate-yo-read" data-rateyo-rating="${notaMediaParticipaEsporte}"></div>
                                </div>
                            </div>
                            <c:forEach var="avaliacao" items="${recebidasParticipaEsporte}">
                                <div class="card text-center margin-bottom-20" id="avaliacoes-participa">
                                    <div class="card-header">
                                        <a href="${pageContext.request.contextPath}/mostraUsuario?id=${avaliacao.avaliador.id}">${avaliacao.avaliador.nome} ${avaliacao.avaliador.sobrenome}</a>
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
