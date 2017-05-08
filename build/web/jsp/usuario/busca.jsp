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
        
        <script type="text/javascript">
            $(document).ready(function() {
                $("#texto").on("propertychange input",function() {
                    if($("#texto").val() != "") {
                        $.ajax({
                            url : "${pageContext.request.contextPath}/buscaUsuario",
                            data : {
                                texto : $("#texto").val()
                            },
                            success : function(responseJson) {
                                $("#resultado").empty();
                                var $table = $("<table class='table'>").appendTo($("#resultado"))
                                        .append($("<thead class='thead-default'>")
                                            .append($("<tr>")
                                                .append($("<th>").text("Nome"))
                                                .append($("<th>").text("E-mail"))
                                                .append($("<th>").text(""))));
                                $.each(responseJson, function(index, usuario) {
                                    $("<tbody>").appendTo($table)
                                        .append($("<tr>")
                                            .append($("<td>").text(usuario.nome))
                                            .append($("<td>").text(usuario.email))
                                            .append($("<a href='${pageContext.request.contextPath}/mostraUsuario?id=" + usuario.id + "'>Ver</a>")));
                                });
                            },
                            error: function(e){
                                alert(JSON.stringify(e))
                            }
                        });
                    } else {
                        $("#resultado").empty();
                    }
                });
            });
        </script>
    </head>
    <body>
        <c:import url="../_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            <c:choose>
                <c:when test="${usuarioLogado != null}">
                    
                    <form>
                        <div class="form-group">
                            <label for="texto">Insira o texto desejado:</label>
                            <input type="text" class="form-control" id="texto" placeholder="Nome ou e-mail"/>
                        </div>
                        <!--<button type="submit" id="procurar" class="btn btn-primary">Procurar</button>-->
                    </form>
                    
                    <br/>
                    <br/>
                    
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
