package org.skyhigh.msauthmrmadapter.rest.controller;

import org.skyhigh.msauthmrmadapter.model.dto.AuthorityDTO;
import org.skyhigh.msauthmrmadapter.service.AuthorityService;
import org.skyhigh.msauthmrmadapter.validation.springAspect.annotationsApi.ValidParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/auth-mrm-adapter/api")
public class RestController {
    private static final Logger log = Logger.getLogger(RestController.class.getName());

    private AuthorityService authorityService;

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping(value = "/{login}")
    @ValidParams
    ResponseEntity<?> getAuthorities(@PathVariable("login") String login) {
        log.info("initiated getAuthorities: " + login);
        AuthorityDTO result = authorityService.getAuthorities(login);
        if (result.getAuthorities() == null || result.getAuthorities().isEmpty())
            log.info("getAuthorities: no authorities found for login: " + login);
        log.info("getAuthorities: found " + result.getAuthorities().size() + " authorities for login: " + login);
        return ResponseEntity.ok(result);
    }
}
