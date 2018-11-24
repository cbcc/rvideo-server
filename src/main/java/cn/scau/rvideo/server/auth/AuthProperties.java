package cn.scau.rvideo.server.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(
        prefix = "rvideo.auth"
)
public class AuthProperties {
    private String headerName = "Authorization";
    private String tokenPrefix = "Bearer ";
    private final Token token = new Token();

    public AuthProperties() {
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Token getToken() {
        return token;
    }

    public static class Token {
        private Duration expiration = Duration.ofMinutes(60);

        public Duration getExpiration() {
            return expiration;
        }

        public void setExpiration(Duration expiration) {
            this.expiration = expiration;
        }
    }
}
