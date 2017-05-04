<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}">Home<span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${usuarioLogado != null}">
                <li class="nav-item">
                    <a class="nav-link" href="#">Quero viajar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Praticar esportes</a>
                </li>
            </c:if>
        </ul>
            
        <c:choose>
            <c:when test="${usuarioLogado == null}">
                <a class="btn btn-secondary btn-navbar" href="${pageContext.request.contextPath}/jsp/usuario/login.jsp">Entrar</a>
                <a class="btn btn-secondary btn-navbar" href="${pageContext.request.contextPath}/jsp/usuario/cadastro.jsp">Cadastrar</a>
            </c:when>
            <c:when test="${usuarioLogado != null}">
                <span style="margin-top: 6px">
                    Olá 
                    <a href="${pageContext.request.contextPath}/jsp/usuario/index.jsp">
                        ${usuarioLogado.nome}
                    </a>!
                </span>
                
                <form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="submit" class="btn btn-secondary btn-navbar" value="Deslogar" />
                </form>
                
            </c:when>
        </c:choose>
        
    </div>
</nav>