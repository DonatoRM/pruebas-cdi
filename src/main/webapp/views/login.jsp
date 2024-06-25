<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="layout/header.jsp"/>
<body class="vh-100 vw-100 d-flex align-items-center justify-content-center">
<main>
    <section>
        <form method="post" action="${pageContext.request.contextPath}/views/login">
            <fieldset class="border border-2 border-dark rounded-2 shadow bg-light p-3">
                <legend class="text-center text-primary">Login</legend>
                <div class="position-relative">
                    <div class="row m-0 p-0 mb-4">
                        <small class="p-0 m-0"><label for="username"
                                                      class="form-label p-0 m-0 fw-bold">Usuario</label></small>
                        <div class="p-0 m-0">
                            <input type="text" name="username" id="username" class="form-control"
                                   value="${param.username}">
                        </div>
                    </div>
                    <c:if test="${errors.size()>0 && errors.containsKey('username')}">
                        <div class="m-0 p-0 text-danger position-absolute"
                             style="top: 57px; left: 16px;"><small>${errors.get('username')}</small></div>
                    </c:if>
                </div>
                <div class="position-relative">
                    <div class="row m-0 p-0 mb-4">
                        <small class="p-0 m-0"><label for="password"
                                                      class="form-label p-0 m-0 fw-bold">Contraseña</label></small>
                        <div class="p-0 m-0">
                            <input type="password" name="password" id="password" class="form-control"
                                   value="${param.password}">
                        </div>
                    </div>
                    <c:if test="${errors.size()>0 && errors.containsKey('password')}">
                        <div class="m-0 p-0 text-danger position-absolute"
                             style="top: 57px; left: 16px;"><small>${errors.get('password')}</small></div>
                    </c:if>
                </div>
                <div class="text-center mt-5">
                    <button type="submit" class="btn btn-primary">Aceptar</button>
                </div>
            </fieldset>
        </form>
    </section>
    <c:if test="${errors.size()>0 && errors.containsKey('valid')}">
        <div class="m-0 p-0 text-danger"><small>${errors.get('valid')}</small></div>
    </c:if>
</main>
</body>
<jsp:include page="layout/footer.jsp"/>