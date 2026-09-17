package exam.web.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private final Key key;
    private final long expiration;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {

        this.key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));

        this.expiration = expiration;
    }
    
    //토큰생성
    public String createToken(String loginId) {

        Date now = new Date();
        Date expirationDate =
                new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(loginId)//로그인 아이디
                .setIssuedAt(now) //발급시간
                .setExpiration(expirationDate) //만료시간
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    //JWT 검증
    public boolean validateToken(String token) {
    	try {
    		Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token); //여기서 토큰의 서명과 만료시간 등을 검증함.
    		
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }

    //JWT에서 subject를 읽어옴(여기서는 loginId)
    public String getLoginId(String token) {
    	return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
 
    }
}
