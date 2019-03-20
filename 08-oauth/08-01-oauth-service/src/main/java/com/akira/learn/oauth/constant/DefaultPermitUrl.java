package com.akira.learn.oauth.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum DefaultPermitUrl {
    ACTUATOR_HEALTH("/actuator/health"),
    ACTUATOR_ENV("/actuator/env"),
    ACTUATOR_METRICS_ALL("/actuator/metrics/**"),
    ACTUATOR_TRACE("/actuator/trace"),
    ACTUATOR_DUMP("/actuator/dump"),
    ACTUATOR_JOLOKIA("/actuator/jolokia"),
    ACTUATOR_INFO("/actuator/info"),
    ACTUATOR_LOGFILE("/actuator/logfile"),
    ACTUATOR_REFRESH("/actuator/refresh"),
    ACTUATOR_FLYWAY("/actuator/flyway"),
    ACTUATOR_LIQUIBASE("/actuator/liquibase"),
    ACTUATOR_HEAPDUMP("/actuator/heapdump"),
    ACTUATOR_LOGGERS("/actuator/loggers"),
    ACTUATOR_AUDITEVENTS("/actuator/auditevents"),
    ACTUATOR_ENV_PID("/actuator/env/PID"),
    ACTUATOR_JOLOKIA_ALL("/actuator/jolokia/**"),

    V2_API_DOCS_ALL("/v2/api-docs/**"),
    SWAGGER_UI_HTML("/swagger-ui.html"),
    SWAGGER_RESOURCES_ALL("/swagger-resources/**"),
    WEBJARS_ALL("/webjars/**");

    public final String url;

    DefaultPermitUrl(String url) {
        this.url = url;
    }

    public static String[] all(String... urls) {
        String[] defaults = Arrays.stream(values()).map(permitUrl -> permitUrl.url).toArray(String[]::new);
        if (urls == null || urls.length == 0) {
            return defaults;
        }
        Set<String> merge = new HashSet<>();
        Collections.addAll(merge, defaults);
        Collections.addAll(merge, urls);
        return merge.toArray(new String[0]);

    }
}
