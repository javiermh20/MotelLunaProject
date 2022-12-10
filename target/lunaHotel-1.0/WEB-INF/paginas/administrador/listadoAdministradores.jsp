<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container p-5">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Administradores</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Usuario</th>
                                <th>Contraseña</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de administradores -->
                            <c:forEach var="administrador" items="${administradores}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${administrador.nombre}</td>
                                    <td>${administrador.usuario}</td>
                                    <td>${administrador.pass}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorAdministrador?accion=editar&idAdministrador=${administrador.idAdministrador}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Agregar administrador MODAL -->
<jsp:include page="/WEB-INF/paginas/administrador/agregarAdministrador.jsp"/>