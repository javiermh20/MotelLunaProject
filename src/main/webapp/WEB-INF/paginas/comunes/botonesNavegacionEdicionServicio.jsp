<section id="actions" class="py-4 mb-4">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="servicio1.jsp" class="btn btn-dark btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fas fa-check"></i> Guardar 
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControladorServicio?accion=eliminar&idServicio=${servicio.idServicio}"
                   class="btn btn-danger btn-block">
                    <i class="fas fa-trash"></i> Eliminar 
                </a>
            </div>
        </div>
    </div>
</section>
