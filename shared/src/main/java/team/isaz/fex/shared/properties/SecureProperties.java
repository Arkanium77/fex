package team.isaz.fex.shared.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {
    @NestedConfigurationProperty
    private BasicProperties basicProperties;

    @Data
    public static class BasicProperties {
        private String username;
        private String password;
    }
}
