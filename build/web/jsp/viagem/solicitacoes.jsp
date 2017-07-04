<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospedagem Esportiva</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            <c:choose>
                <c:when test="${usuarioLogado != null}">
                    <h1>Solicitações</h1>
                    
                    <c:if test="${mensagemSolicitacao != null}">
                        <div class="alert alert-${classeAlert} alert-timer">
                            ${mensagemSolicitacao}
                            <c:remove var="classeAlert" scope="session" />
                            <c:remove var="mensagemSolicitacao" scope="session" />
                        </div>
                    </c:if>
                    
                    <div class="card">
                        <div class="card-header">
                            <ul class="nav nav-tabs card-header-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" href="#feitas" role="tab" data-toggle="tab">Feitas</a>
                                </li>
                                <c:if test="${usuarioLogado.dispostoReceber}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="#recebidas" role="tab" data-toggle="tab">Recebidas</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="card-block">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="feitas">
                                    <c:choose>
                                        <c:when test="${solicitacoesFeitas.isEmpty()}">
                                            <div class="alert alert-danger">
                                                Você não tem nenhuma solicitação de viagem feita!
                                            </div>
                                        </c:when>
                                        <c:when test="${!solicitacoesFeitas.isEmpty()}">
                                            <table class="table table-striped">
                                                <thead class="thead-default">
                                                    <tr>
                                                        <th>Cód.</th>
                                                        <th>Cidade</th>
                                                        <th>País</th>
                                                        <th>Início</th>
                                                        <th>Fim</th>
                                                        <th>Pessoas</th>
                                                        <th>Status</th>
                                                        <th>Anfitrião</th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="solicitacaoFeita" items="${solicitacoesFeitas}">
                                                        <tr>
                                                            <td>${solicitacaoFeita.id}</td>
                                                            <td>${solicitacaoFeita.cidadeDestino}</td>
                                                            <td>${solicitacaoFeita.paisDestino}</td>
                                                            <td>${solicitacaoFeita.dataInicioFormatada()}</td>
                                                            <td>${solicitacaoFeita.dataFimFormatada()}</td>
                                                            <td>${solicitacaoFeita.quantHospedes}</td>
                                                            <td>${solicitacaoFeita.statusViagem}</td>
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/mostraUsuario?id=${solicitacaoFeita.anfitriao.id}">
                                                                    ${solicitacaoFeita.anfitriao.nome}
                                                                </a>
                                                            </td>
                                                            <td><a href="${pageContext.request.contextPath}/excluirSolicitacao?id=${solicitacaoFeita.id}">Excluir</a></td>
                                                            <td>
                                                                <c:if test="${solicitacaoFeita.statusViagem == 'APROVADA'}">
                                                                    <a href="${pageContext.request.contextPath}/confirmaSolicitacao?id=${solicitacaoFeita.id}">Confirmar</a>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${solicitacaoFeita.verificaSeJaPassou() and solicitacaoFeita.podeAvaliar(usuarioLogado.id) and solicitacaoFeita.statusViagem == 'CONFIRMADA'}">
                                                                        <a href="${pageContext.request.contextPath}/jsp/viagem/avaliar.jsp?id=${solicitacaoFeita.id}">Avaliar</a>
                                                                    </c:when>
                                                                    <c:when test="${solicitacaoFeita.verificaSeJaPassou() and solicitacaoFeita.jaAvaliou(usuarioLogado.id) and solicitacaoFeita.statusViagem == 'CONFIRMADA'}">
                                                                        Já avaliou!
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <c:if test="${usuarioLogado.dispostoReceber}">
                                    <div role="tabpanel" class="tab-pane fade" id="recebidas">
                                        <c:choose>
                                            <c:when test="${solicitacoesRecebidas.isEmpty()}">
                                                <div class="alert alert-danger">
                                                    Você não tem nenhuma solicitação de viagem recebida!
                                                </div>
                                            </c:when>
                                            <c:when test="${!solicitacoesRecebidas.isEmpty()}">
                                                <table class="table table-striped">
                                                    <thead class="thead-default">
                                                        <tr>
                                                            <th>Cód.</th>
                                                            <th>Cidade</th>
                                                            <th>País</th>
                                                            <th>Início</th>
                                                            <th>Fim</th>
                                                            <th>Pessoas</th>
                                                            <th>Status</th>
                                                            <th>Hóspede</th>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="solicitacaoRecebida" items="${solicitacoesRecebidas}">
                                                            <tr>
                                                                <td>${solicitacaoRecebida.id}</td>
                                                                <td>${solicitacaoRecebida.cidadeDestino}</td>
                                                                <td>${solicitacaoRecebida.paisDestino}</td>
                                                                <td>${solicitacaoRecebida.dataInicioFormatada()}</td>
                                                                <td>${solicitacaoRecebida.dataFimFormatada()}</td>
                                                                <td>${solicitacaoRecebida.quantHospedes}</td>
                                                                <td>${solicitacaoRecebida.statusViagem}</td>
                                                                <td>
                                                                    <a href="${pageContext.request.contextPath}/mostraUsuario?id=${solicitacaoRecebida.hospede.id}">
                                                                        ${solicitacaoRecebida.hospede.nome}
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${solicitacaoRecebida.statusViagem == 'PENDENTE'}">
                                                                        <a href="${pageContext.request.contextPath}/aprovaSolicitacao?id=${solicitacaoRecebida.id}">Aprovar</a>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${solicitacaoRecebida.statusViagem == 'PENDENTE'}">
                                                                        <a href="${pageContext.request.contextPath}/rejeitaSolicitacao?id=${solicitacaoRecebida.id}">Rejeitar</a>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${solicitacaoRecebida.verificaSeJaPassou() and solicitacaoRecebida.podeAvaliar(usuarioLogado.id) and solicitacaoRecebida.statusViagem == 'CONFIRMADA'}">
                                                                            <a href="${pageContext.request.contextPath}/jsp/viagem/avaliar.jsp?id=${solicitacaoRecebida.id}">Avaliar</a>
                                                                        </c:when>
                                                                        <c:when test="${solicitacaoRecebida.verificaSeJaPassou() and solicitacaoRecebida.jaAvaliou(usuarioLogado.id) and solicitacaoRecebida.statusViagem == 'CONFIRMADA'}">
                                                                            Já avaliou!
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </c:if>
                            </div>
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
