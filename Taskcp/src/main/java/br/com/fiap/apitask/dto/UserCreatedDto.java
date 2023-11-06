package br.com.fiap.apitask.dto;

import br.com.fiap.Taskcp.model.UserInfo;
import br.com.fiap.Tasckcp.model.UserRole;

public record UserCreatedDto (Long id, String username, UserRole roles){

    public UserCreatedDto (UserInfo user) {
        this (user.getId(), user.getUsername(), user.getRole());
    }
}
