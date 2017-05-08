<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospedagem Esportiva</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mask.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $("#formBuscar").submit(function(e) {
                    $.ajax({
                        type: "POST",
                        url : "${pageContext.request.contextPath}/buscaUsuarioViagem",
                        data : {
                            cidadeDestino : $("#cidadeDestino").val(),
                            paisDestino : $("#paisDestino").val(),
                            quantidade : $("#quantidade").val(),
                            chegada : $("#chegada").val(),
                            saida : $("#saida").val()
                        },
                        success : function(responseJson) {
                            $("#resultado").empty();
                            var $table = $("<table class='table'>").appendTo($("#resultado"))
                                    .append($("<thead class='thead-default'>")
                                        .append($("<tr>")
                                            .append($("<th>").text("Nome"))
                                            .append($("<th>").text("E-mail"))
                                            .append($("<th>").text(""))
                                            .append($("<th>").text(""))));
                            $.each(responseJson, function(index, usuarioBusca) {
                                $("<tbody>").appendTo($table)
                                    .append($("<tr>")
                                        .append($("<td>").text(usuarioBusca.nome))
                                        .append($("<td>").text(usuarioBusca.email))
                                        .append($("<td>").append($("<a href='${pageContext.request.contextPath}/mostraUsuario?id=" + usuarioBusca.id + "'>Ver</a>")))
                                        .append($("<td>").append($("<a href='${pageContext.request.contextPath}/solicitarHospedagem?id=" + usuarioBusca.id + "'>Solicitar</a>"))));
                            });
                        },
                        error: function(e){
                            alert(JSON.stringify(e));
                        }
                    });
                    e.preventDefault()
                });
            });
        </script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            <c:choose>
                <c:when test="${usuarioLogado != null}">
                    <h1>Solicitar hospedagem</h1>
                    
                    <form id="formBuscar">
                        <div class="form-group">
                            <label for="cidadeDestino">Cidade de Destino:</label> 
                            <input type="text" class="form-control" id="cidadeDestino" name="cidadeDestino"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="paisDestino">País de Destino:</label> 
                            <input type="text" class="form-control" id="paisDestino" name="paisDestino"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="quantidade">Quantas pessoas?</label> 
                            <input type="number" class="form-control" id="quantidade" name="quantidade" value="1" min="1"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="chegada">Chegada:</label> 
                            <input type="date" class="form-control" id="chegada" name="chegada"/><br/>
                        </div>
                        <div class="form-group">
                            <label for="saida">Saída:</label> 
                            <input type="date" class="form-control" id="saida" name="saida"/><br/>
                        </div>
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </form>
                    
                    <strong>Resultados:</strong>
                    <div id="resultado">
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
