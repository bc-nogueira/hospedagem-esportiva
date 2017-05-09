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
