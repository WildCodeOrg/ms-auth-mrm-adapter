package org.skyhigh.msauthmrmadapter.service;

import jakarta.validation.constraints.NotNull;
import org.skyhigh.msauthmrmadapter.model.dto.AuthorityDTO;

public interface AuthorityService {
    /**
     * Метод получения доступов (authority) пользователя
     * @param userLogin - логин пользователя, доступы которого необходимо получить
     * @return AuthorityDTO - объект, содержащий список доступов пользователя в формате String
     */
    AuthorityDTO getAuthorities(@NotNull String userLogin);
}
