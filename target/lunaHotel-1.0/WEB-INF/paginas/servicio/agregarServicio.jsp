<div class="modal fade" id="agregarServicioModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Servicio</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladorServicio?accion=insertar"
                  method="POST" class="form-reg2 was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="idEmpleado">ID Empleado</label>
                        <input type="number" class="form-control" name="idEmpleado" required>
                    </div>
                    <div class="form-group">
                        <label for="idDepartamentos">ID Departamento</label>
                        <input type="number" class="form-control" name="idDepartamentos" required>
                    </div>
                    <div class="form-group">
                        <label for="servicio">Servicio a realizar</label>
                        <input type="text" class="form-control" name="servicio" required>
                    </div>
                    <div class="form-group">
                        <label for="status">Status del servicio</label>
                        <input type="text" class="form-control" name="status" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>