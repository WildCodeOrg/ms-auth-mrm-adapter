package org.skyhigh.msauthmrmadapter.rest.service;

import jakarta.validation.constraints.NotNull;
import org.skyhigh.msauthmrmadapter.commonService.CommonAuthorityService;
import org.skyhigh.msauthmrmadapter.model.dto.AuthorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RestAuthorityServiceImpl implements RestAuthorityService {
    private static final Logger log = Logger.getLogger(RestAuthorityServiceImpl.class.getName());

    private final CommonAuthorityService commonAuthorityService;

    @Autowired
    public RestAuthorityServiceImpl(CommonAuthorityService commonAuthorityService) {
        this.commonAuthorityService = commonAuthorityService;
    }

    @Override
    public AuthorityDTO getAuthorities(@NotNull String userLogin) {
        return new AuthorityDTO(commonAuthorityService.getAuthorities(userLogin));
    }
}
