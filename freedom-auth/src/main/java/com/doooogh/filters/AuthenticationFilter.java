package com.doooogh.filters;

import cn.hutool.core.util.StrUtil;
import com.doooogh.servers.redis.utils.RedisUtil;
import com.doooogh.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @description: 授权过滤器
 * @date 2022/9/2
 */
@Slf4j
public class AuthenticationFilter extends BasicAuthenticationFilter {


    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisService;

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager=authenticationManager;


    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String authHeader = request.getHeader(this.tokenHeader);
        if (StrUtil.isNotEmpty(authHeader) && authHeader.startsWith(this.tokenHead)) {
            // The part after "Bearer "
            String authToken = authHeader.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 从redis从获取
                String redisToken = (String) this.redisService.get(username);
                if (StrUtil.isNotEmpty(redisToken) && jwtTokenUtil.validateToken(authToken, redisToken)) {
                    List<String> userAuthorities = jwtTokenUtil.getUserAuthorities(redisToken);
                    List<GrantedAuthority> authority = new ArrayList<>();
                    for (String permissionValue : userAuthorities) {
                        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(permissionValue);
                        authority.add(auth);
                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authority);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticate success user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
