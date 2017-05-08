<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
        <title>Hospedagem Hoteleira</title>
    </head>
    <body>
        <c:import url="jsp/_comum/navbar.jsp"/>
        
        <div class="container container-principal">
            
            <c:if test="${mensagemLogout != null}">
                <div class="alert alert-success alert-timer">
                    ${mensagemLogout}
                    <c:remove var="mensagemLogout" scope="session" />
                </div>
            </c:if>
            
            <h1 class="text-center">Bem-vindo ao sistema da Hospedagem Esportiva!</h1>
        </div>
        <c:import url="jsp/_comum/footer.jsp"/>
    </body>
</html>
