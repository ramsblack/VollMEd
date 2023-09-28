package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import med.voll.api.domain.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;
	
	
	public String generarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = 
		    		Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("voll med")
		        .withSubject(usuario.getLogin())
		        .withClaim("id", usuario.getId())
		        .withExpiresAt(generarFechaexpiracion())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException();
		}
		
		
	}
	
	public String getSubject(String token) {
		if(token == null) {
			throw new RuntimeException();
		}		
		DecodedJWT verifier = null;
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
			verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            verifier.getSubject();
			
		}catch (JWTVerificationException exception) {
			System.out.println(exception.toString());
		}
		if (verifier.getSubject() == null) {
			System.out.println(verifier);
			throw new RuntimeException("ayuda Verifier Invalido");
		}
		return verifier.getSubject();
	}
	
	private Instant generarFechaexpiracion() {
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
	}
}