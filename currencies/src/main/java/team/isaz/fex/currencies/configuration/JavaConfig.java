package team.isaz.fex.currencies.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import team.isaz.fex.shared.configuration.BasicSecurityConfiguration;
import team.isaz.fex.shared.configuration.EncryptionConfiguration;
import team.isaz.fex.shared.configuration.PasswordEncoderConfiguration;
import team.isaz.fex.shared.configuration.SecurityBeansConfiguration;
import team.isaz.fex.shared.properties.SecureProperties;

@Configuration
@Import({
        EncryptionConfiguration.class,
        PasswordEncoderConfiguration.class,
        BasicSecurityConfiguration.class,
        SecurityBeansConfiguration.class,
        SecureProperties.class
})
public class JavaConfig {

}
