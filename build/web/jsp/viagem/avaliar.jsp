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
        <!-- Star Rating files -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.rateyo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.rateyo.js"></script>
        
        <script type="text/javascript">
            $(function () {
                $("#avaliacaoViagem").rateYo({
                    halfStar: true,
                    onSet: function (rating, rateYoInstance) {
                        $('#nota').val(rating);
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
                    <h1>Avaliar hospedagem</h1>
                    <hr/>
                    
                    <form action="${pageContext.request.contextPath}/avaliarViagem" method="POST">
                        <input type="hidden" id="idViagem" name="idViagem" value="${param.id}"/>
                        
                        <div style="display:inline-block;" id="avaliacaoViagem"></div>
                        <input type="hidden" id="nota" name="nota"/>
                        
                        <div class="form-group">
                            <label for="descricao">Descrição</label>
                            <textarea id="descricao" name="descricao" class="form-control" rows="4" cols="50"></textarea>
                        </div>
                        <input type="submit" class="btn btn-primary" value="Enviar"/>
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
