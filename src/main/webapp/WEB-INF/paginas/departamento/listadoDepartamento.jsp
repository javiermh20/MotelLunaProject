<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Departamentos</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Numero</th>
                                <th>Habitaciones</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de departamentos -->
                            <c:forEach var="departamento" items="${departamentos}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td> <fmt:formatNumber value="${departamento.numeroDepartamento}"/></td>
                                    <td>${departamento.habitaciones} </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorDepartamento?accion=editar&idDepartamento=${departamento.idDepartamento}"
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

<!-- Agregar departamento MODAL -->
<jsp:include page="/WEB-INF/paginas/departamento/agregarDepartamento.jsp"/>