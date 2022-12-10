<section id="actions" class="py-4 mb-4">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="vivienda1.jsp" class="btn btn-dark btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fas fa-check"></i> Guardar 
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControladroVivienda?accion=eliminar&idVivienda=${vivienda.idVivienda}"
                   class="btn btn-danger btn-block">
                    <i class="fas fa-trash"></i> Eliminar 
                </a>
            </div>
        </div>
    </div>
</section>