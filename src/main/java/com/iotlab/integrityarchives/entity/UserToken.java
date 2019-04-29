package com.iotlab.integrityarchives.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
@NoArgsConstructor
public class UserToken extends UsernamePasswordToken {
    private String loginType;

    public UserToken(final String username, final String password, final String loginType){
            super(username, password);
            this.loginType = loginType;
    }
}
