package med.voll.api.medico;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    public ResponseEntity<DTORespuestaMedico> RegistrarMedicos(@RequestBody @Valid DTORegistroMedico json,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Medico medico=medicoRepository.save(new Medico(json));
        DTORespuestaMedico dtoRespuestaMedico=new DTORespuestaMedico(medico.getId(), medico.getNombre(),medico.getEspecialidad().toString(),medico.getEmail(),medico.getTelefono(),medico.getDni(),
                new DTODireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()));
        URI url=uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(dtoRespuestaMedico);
    }
    @GetMapping
    public ResponseEntity<Page<DTOListadoMedico>> mostrarMedicos(@PageableDefault() Pageable paginacion){
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DTOListadoMedico::new));
    }
    //http://localhost:8080/medico
    @PutMapping
    @Transactional
    public ResponseEntity<DTORespuestaMedico> actualizarMedicos(@RequestBody @Valid DTOActualizarMedico dtoActualizarMedico){
        Medico medico=medicoRepository.getReferenceById(dtoActualizarMedico.id());
        medico.actualizarDatos(dtoActualizarMedico);
        return ResponseEntity.ok(new DTORespuestaMedico(medico.getId(), medico.getNombre(),medico.getEspecialidad().toString(),medico.getEmail(),medico.getTelefono(),medico.getDni(),
               new DTODireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                       medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento())));
    }

    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        var datosMedico = new DTORespuestaMedico(medico.getId(), medico.getNombre(),medico.getEspecialidad().toString(),medico.getEmail(),medico.getTelefono(),medico.getDni(),
                new DTODireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }
    //BORRAR DE LA BASE DE DATOS
    //    @DeleteMapping("/{id}")
    //    @Transactional//IMPORTANTE PARA LA ACTUALIZACION/CAMBIO DE DATOS
    //    public void eliminarMedico(@PathVariable Long id){
    //        Medico medico=medicoRepository.getReferenceById(id);
    //        medicoRepository.delete(medico);
    //    }
}
