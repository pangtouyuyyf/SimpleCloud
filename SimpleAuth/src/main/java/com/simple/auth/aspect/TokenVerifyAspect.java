package com.simple.auth.aspect;

import com.simple.auth.client.BaseClient;
import com.simple.common.config.SysParams;
import com.simple.common.domain.LoginInfo;
import com.simple.common.domain.Result;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.holder.RequestLoginContextHolder;
import com.simple.common.util.JwtUtil;
import com.simple.common.util.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description token操作切面
 * Author chen
 * CreateTime 2020-04-27 20:40
 **/
@Aspect
@Order(0)
@Component
public class TokenVerifyAspect {
    @Resource
    private BaseClient baseClient;

    /**
     * 业务controller类层面的Token验证(对所有TokenController实现类进行token验证),地毯式验证
     */
    @Pointcut("execution(* com.simple.common.controller.TokenController+.*(..))")
    public void carpetTokenVerify() {
    }

    /**
     * 业务controller类方法自定义注解Token验证(对Controller类中方法用此注解进行token验证),精确式验证
     */
    @Pointcut("@annotation(com.simple.common.annotation.TokenAnnotation)")
    public void exactTokenVerify() {
    }

    /**
     * 验证处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("carpetTokenVerify() || exactTokenVerify()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader(SysParams.Sys.TOKEN);

        Map<String, String> jwtMap = JwtUtil.parseJWT(token);

        /** 验证令牌合法性 **/
        if (jwtMap == null || jwtMap.isEmpty()) {
            LogUtil.error(TokenVerifyAspect.class, LocalDateTime.now() + " 令牌验证失败");
            return Result.error(SysExpEnum.NEED_LOGIN);
        }

        /** 获取令牌中的用户、角色和登录渠道 **/
        String userId = jwtMap.get(SysParams.Sys.USER_ID);
        String channel = jwtMap.get(SysParams.Sys.CHANNEL);

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(channel)){
            LogUtil.error(TokenVerifyAspect.class, LocalDateTime.now() + " 令牌验证失败");
            return Result.error(SysExpEnum.NEED_LOGIN);
        }

        /** 生成个人信息缓存主键 **/
        List<String> loginInfoKeyParts = Arrays.asList(
                SysParams.Redis.LOGIN_INFO_PREFIX, userId, channel);
        String loginInfoKey = String.join(SysParams.Common.UNDERLINE, loginInfoKeyParts);

        /** 将登录数据写入ThreadLocal **/
        Result<LoginInfo> r = baseClient.getLoginInfo(loginInfoKey);
        if (!r.done()) {
            LogUtil.error(TokenVerifyAspect.class, LocalDateTime.now() + " 没有登录信息缓存");
            return Result.error(SysExpEnum.NO_LOGIN_INFO);
        }

        RequestLoginContextHolder.setRequestLoginInfo(r.getData());

        /** 执行目标 **/
        return joinPoint.proceed();
    }

    /**
     * 清除
     */
    @After("carpetTokenVerify() || exactTokenVerify()")
    public void doAfter() {
        RequestLoginContextHolder.destroy();
    }

    /**
     * 异常处理
     *
     * @param throwable
     * @return
     */
    @AfterThrowing(pointcut = "carpetTokenVerify() || exactTokenVerify()", throwing = "throwable")
    public Object doAfterThrowing(Throwable throwable) {
        LogUtil.error(TokenVerifyAspect.class, throwable.toString());
        return Result.error(SysExpEnum.COMMON_ERROR);
    }
}