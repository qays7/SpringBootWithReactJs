package com.balsam.oasis.nursingservices;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Queries
 */
@Slf4j
@Service
public class Queries {

    @Getter
    private Map<String, String> queries = new HashMap<>();

    public Queries() {
        log.debug("Attemp to loading queries");
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("queries.yml");
        queries = yaml.load(inputStream);
    }
}