<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Inquilinos</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Edad</th>
                                <th>Telefono</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de inquilinos -->
                            <c:forEach var="inquilino" items="${inquilinos}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${inquilino.nombre} ${inquilino.apellido} </td>
                                    <td>${inquilino.edad}</td>
                                    <td>+52 ${inquilino.telefono}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idInquilino=${inquilino.idInquilino}"
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

<!-- Agregar inquilino MODAL -->
<jsp:include page="/WEB-INF/paginas/inquilino/agregarInquilino.jsp"/>
                        