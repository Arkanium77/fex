package team.isaz.fex.currencies.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "currency-service")
public class AppProperties {
    @NestedConfigurationProperty
    private String mainCurrencyName;

}
