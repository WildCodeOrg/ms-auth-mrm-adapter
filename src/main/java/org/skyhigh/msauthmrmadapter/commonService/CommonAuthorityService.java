package org.skyhigh.msauthmrmadapter.commonService;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CommonAuthorityService {
    /**
     * Метод получения доступов (authority) пользователя из БД МРМ
     * @param userLogin - логин пользователя, доступы которого необходимо получить
     * @return Список доступов пользователя в формате String
     */
    List<String> getAuthorities(@NotNull String userLogin);
}
