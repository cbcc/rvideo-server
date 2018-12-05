package cn.scau.rvideo.server.auth;

/**
 * http状态
 */
public class AuthStatus {
    public static final int SUCCESS = 200;     // 成功
    public static final int FAIL = 300;    // 失败
    static final int AUTH_REQUIRED = 400;    // 需要认证
    static final int AUTH_EXPIRED = 401;     // 过期凭证
    static final int AUTH_UNAUTHORIZED = 402;    // 没有权限
    static final int AUTH_ILLEGAL = 403;  // 不信任凭证

    public AuthStatus() {
    }
}
