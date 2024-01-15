package com.doooogh.freedom.auth.biz.point;

import com.doooogh.freedom.auth.biz.enums.AuthorizationEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/08
 * @description 自定义认证错误
 */
@Component
public class CusWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity response = null;
        if (e instanceof BadClientCredentialsException) {
            response = new ResponseEntity(AuthorizationEnum.PASSWORK_ERROR.getMessage(), null, AuthorizationEnum.PASSWORK_ERROR.getCode());
        }else if (e instanceof InvalidGrantException) {
            response = new ResponseEntity(AuthorizationEnum.PASSWORK_ERROR.getMessage(), null, AuthorizationEnum.PASSWORK_ERROR.getCode());
        }else if (e instanceof UnsupportedGrantTypeException) {
            response = new ResponseEntity(AuthorizationEnum.UNSUPPORT_GRANT_TYPE.getMessage(), null, AuthorizationEnum.UNSUPPORT_GRANT_TYPE.getCode());
        } else if (e instanceof OAuth2Exception) {
            OAuth2Exception ex = (OAuth2Exception) e;
            response = new ResponseEntity(ex.getMessage(), null, ex.getHttpErrorCode());
        } else {
            response = new ResponseEntity("认证错误", HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
