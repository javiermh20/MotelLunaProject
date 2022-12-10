<form action="${pageContext.request.contextPath}/ServletControladorLogin?accion=validar"
                  method="POST" class="was-validated form-inicio">
                <div class="modal-body">
                    <div class="form-group ">
                        <label for="usuario">Usuario</label>
                        <input type="text" class="form-control" name="usuario" required>
                    </div>
                    <div class="form-group">
                        <label for="pass">Contraseña</label>
                        <input type="password" class="form-control" name="pass" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Iniciar Sesión</button>
                </div>    
</form>