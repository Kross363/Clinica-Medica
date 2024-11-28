package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.Especialidad;
import med.voll.api.model.Direccion;

@Table(name="medico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String dni;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    //CONSTRUCTOR
    public Medico(DTORegistroMedico json) {
        this.activo=true;
        this.nombre=json.nombre();
        this.email= json.email();
        this.telefono= json.telefono();
        this.dni= json.dni();
        this.especialidad=json.especialidad();
        this.direccion=new Direccion(json.direccion());
    }
    //METODO PARA LA ACTUALIZACION DE DATOS
    public void actualizarDatos(DTOActualizarMedico dtoActualizarMedico) {
        //SE PREGUNTA SI ES DISTINTO DE NULL POR SI QUEREMOS CAMBIAR SOLO UN PARAMETRO DE LOS TRES
        //EN INSOMNIA TENDREMOS QUE COLOCAR UN BODY ASI
        //{"id":"5",
        //"lo que queremos actualizar (nombre,dni,direccion)":"nombre,dni o direccion"}
        if(dtoActualizarMedico.nombre()!=null){
            this.nombre= dtoActualizarMedico.nombre();
        }
        if(dtoActualizarMedico.dni()!=null){
            this.dni= dtoActualizarMedico.dni();
        }
        if(dtoActualizarMedico.direccion()!=null){
            this.direccion=direccion.actualizarDatos(dtoActualizarMedico.direccion());
        }


    }

    public void desactivarMedico() {
        this.activo=false;
    }
}
