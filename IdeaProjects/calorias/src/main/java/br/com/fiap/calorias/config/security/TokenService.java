package br.com.fiap.calorias.config.security;

import br.com.fiap.calorias.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String chaveSecreta;

    public String gerarToken(Usuario usuario){
        try{
            // Definindo a variavel e definindo o algoritmo criptografico
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);

            // Criando o token
            String token = JWT
                    .create() // Cria o token
                    .withIssuer("contatos") // Emissor do token
                    .withSubject(usuario.getEmail()) // Identifica o username do usuário
                    .withExpiresAt(gerarDataDeExpiracao()) // Definir o tempo de expiração do token
                    .sign(algorithm); // Assinar o token

            return token;
        } catch (JWTCreationException err){
           throw new RuntimeException("Não foi possivel gerar o token!");
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);

            return JWT
                    .require(algorithm)  // Decifrar o token com o algoritmo
                    .withIssuer("contatos")
                    .build() // Criar o algoritmo
                    .verify(token) // Verificar se token é igual
                    .getSubject(); // Pega o username do usuario
        } catch (JWTVerificationException err){
            return "";
        }
    }

    public Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
