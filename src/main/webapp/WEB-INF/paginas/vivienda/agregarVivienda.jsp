<div class="modal fade" id="agregarViviendaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Vivienda</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladroVivienda?accion=insertar"
                  method="POST" class="form-reg2 was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="idDepartamento">ID Departamento</label>
                        <input type="number" class="form-control" name="idDepartamento" required>
                    </div>
                    <div class="form-group">
                        <label for="idInquilino">ID Inquilino</label>
                        <input type="number" class="form-control" name="idInquilino" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
