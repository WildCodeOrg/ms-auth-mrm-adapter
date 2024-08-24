package org.skyhigh.msauthmrmadapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:config/application-flk-config.yml")
@ConfigurationProperties("flk.settings")
public class FlkConfiguration {
    @Value("${flk.settings.active-flk}")
    private List<String> activeFlkNames;

    @Bean("ActiveFlkList")
    public List<String> getActiveFlkList() {
        return activeFlkNames;
    }
}