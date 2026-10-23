package com.example.identityms.service;

import com.example.identityms.payload.request.AuthenticationRequest;
import com.example.identityms.payload.response.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    

}
