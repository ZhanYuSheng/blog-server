package com.eatshit.bolg.component;

import com.eatshit.bolg.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class HttpComponent {
    @Autowired
    private StringRedisTemplate redis;

    /**
     * 限制调用频率
     *
     * @param request
     * @param key 调用的功能
     * @param time 时间间隔，如：time = 1 为每秒调用一次。
     */
    public void limitTime(HttpServletRequest request, String key, Long time) {
        String ip = getIpAddress(request);
        if ("0:0:0:0:0:0:0:1".equals(ip))
            return;
        String hasCell = redis.opsForValue().get(key + ip);
        if (hasCell == null)
            redis.opsForValue().set(key + ip, "1", time, TimeUnit.SECONDS);
        else
            throw ServiceException.OPERATION_FREQUENTLY;
    }

    /**
     * 获取IP
     *
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
