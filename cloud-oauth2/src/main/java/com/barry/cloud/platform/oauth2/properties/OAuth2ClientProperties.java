package com.barry.cloud.platform.oauth2.properties;

import lombok.Data;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/13 17:23
 */
@Data
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds = 7200;

}
