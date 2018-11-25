package cn.scau.rvideo.server.auth;

/**
 * http错误状态
 */
class AuthStatus {
    static final int AUTH_REQUIRED = 400;    // 需要认证
    static final int AUTH_EXPIRED = 401;     // token过期
    static final int AUTH_UNAUTHORIZED = 402;    // 没有权限
    static final int AUTH_ILLEGAL = 403;  // 非法凭证
}
