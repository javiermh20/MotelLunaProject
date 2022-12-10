<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Viviendas</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID Departamento</th>
                                <th>ID Inquilino</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de viviendas -->
                            <c:forEach var="vivienda" items="${viviendas}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td> <fmt:formatNumber value="${vivienda.idDepartamento}"/> </td>
                                    <td> <fmt:formatNumber value="${vivienda.idInquilino}"/> </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladroVivienda?accion=editar&idVivienda=${vivienda.idVivienda}"
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

<!-- Agregar empleado MODAL -->
<jsp:include page="/WEB-INF/paginas/vivienda/agregarVivienda.jsp"/>