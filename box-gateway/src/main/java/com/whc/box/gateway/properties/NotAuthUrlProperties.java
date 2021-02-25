package com.whc.box.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;

@Data
@ConfigurationProperties("box.gateway")
public class NotAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}
