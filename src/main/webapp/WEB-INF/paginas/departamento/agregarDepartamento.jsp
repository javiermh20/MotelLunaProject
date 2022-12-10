<div class="modal fade" id="agregarDepartamentoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Departamento</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladorDepartamento?accion=insertar"
                  method="POST" class="form-reg2 was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="numeroDepartamento">Numero de departamento</label>
                        <input type="number" class="form-control" name="numeroDepartamento" required>
                    </div>
                    <div class="form-group">
                        <label for="habitaciones">Habitaciones</label>
                        <input type="text" class="form-control" name="habitaciones" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
    