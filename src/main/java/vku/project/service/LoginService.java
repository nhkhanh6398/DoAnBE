package vku.project.service;

import vku.project.dto.AccountResponse;

public interface LoginService {
    AccountResponse doLogin(String userName, String password);
}
