<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Servicios</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>IDEmpleado</th>
                                <th>IDDepartamento</th>
                                <th>Servicio</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de servicios -->
                            <c:forEach var="servicio" items="${servicios}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td> <fmt:formatNumber value="${servicio.idEmpleado}"/> </td>
                                    <td> <fmt:formatNumber value="${servicio.idDepartamentos}"/> </td>
                                    <td>${servicio.servicio} </td>
                                    <td>${servicio.status} </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorServicio?accion=editar&idServicio=${servicio.idServicio}"
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

<!-- Agregar servicio MODAL -->
<jsp:include page="/WEB-INF/paginas/servicio/agregarServicio.jsp"/>
                        
