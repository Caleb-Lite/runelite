package net.runelite.client.plugins.pluginhub.net.clanhalls.plugin.beans;

import lombok.Data;

@Data
public class TokenData {
    private String accessToken;
    private String refreshToken;
    private long accessTokenExpiresAt;
    private long refreshTokenExpiresAt;
}
