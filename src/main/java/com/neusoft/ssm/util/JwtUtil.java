package com.neusoft.ssm.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.ssm.bean.User;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {
    private static final String SECRET = "HIS#$##^%^!!@<>:{{}$&&%#441125896ADDGGBS%*^)(&)$#%^&#+_+0DYYTR";
    private static final String EXP = "exp";
    private static final String PAYLOAD = "payload";

    //加密，传入一个对象和有效期
    public static <T> String sign(T object, long maxAge) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static<T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //获得request中的id和departmentId
    private static User getObjectFromRequestHeader(HttpServletRequest request){
        String token=request.getHeader("Authorization");//Bearer token
        if(!(null == token||!token.startsWith("Bearer "))){
            token = token.substring(7);
            return JwtUtil.unsign(token, User.class);
        }else return null;
    }
    public static int getHeaderDoctorId(HttpServletRequest request){
        return Objects.requireNonNull(Objects.requireNonNull(JwtUtil.getObjectFromRequestHeader(request)).getId());
    }
    public static int getHeaderDepartmentId(HttpServletRequest request){
        return Objects.requireNonNull(Objects.requireNonNull(JwtUtil.getObjectFromRequestHeader(request)).getDepartmentId());
    }

}
