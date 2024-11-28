package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.usuario.DTOAutenticacionUsuario;
import med.voll.api.dto.DTOJWTtoken;
import med.voll.api.infra.security.TokenService;
import med.voll.api.usuario.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DTOAutenticacionUsuario dtoAutenticacionUsuario){
        Authentication authToken=new UsernamePasswordAuthenticationToken(dtoAutenticacionUsuario.login(),dtoAutenticacionUsuario.clave());
        var usuarioAutenticado=authenticationManager.authenticate(authToken);
        var jwtToken=tokenService.generarToken((Usuarios) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DTOJWTtoken(jwtToken));
    }
}
