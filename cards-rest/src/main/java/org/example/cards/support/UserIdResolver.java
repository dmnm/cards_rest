package org.example.cards.support;

import org.example.cards.annotation.UserId;
import org.example.cards.auth.UserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserIdResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.hasParameterAnnotation(UserId.class) && param.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter param, ModelAndViewContainer container, NativeWebRequest request,
            WebDataBinderFactory binder) {
        if (supportsParameter(param) && request.getUserPrincipal() instanceof Authentication
                && ((Authentication) request.getUserPrincipal()).getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) ((Authentication) request.getUserPrincipal()).getPrincipal();
            return user.id;
        }
        return null;
    }
}
