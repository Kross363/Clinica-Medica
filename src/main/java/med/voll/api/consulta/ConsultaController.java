package med.voll.api.consulta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ReservaDeConsultas reservaDeConsultas;
    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DTOConsultaReserva datos){
        var detalleConsulta=reservaDeConsultas.reservar(datos);
        reservaDeConsultas.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DTOCancelamientoConsulta datos) {
        reservaDeConsultas.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
