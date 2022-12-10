<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="inquilinos">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Empleados</h4>
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
                            <!-- Iteramos cada elemento de la lista de empleados -->
                            <c:forEach var="empleado" items="${empleados}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${empleado.nombre} ${empleado.apellido} </td>
                                    <td>${empleado.edad}</td>
                                    <td>+52 ${empleado.telefono}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorEmpleado?accion=editar&idEmpleado=${empleado.idEmpleado}"
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
<jsp:include page="/WEB-INF/paginas/empleado/agregarEmpleado.jsp"/>