package com.alibaba.cola.adapter.ext.config;

import java.io.Serializable;
import java.util.Objects;

/**
 * Describe: 用于定义基本拦截的基本类
 * @author qi.wei
 */
public class RequestContext implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 业务产品编码
     */
    private Integer applicationCode;

    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 语言
     */
    private String language;

    /**
     * app版本号
     */
    private String appVersion;

    /**
     * 客户端系统类型
     */
    private Integer osType;

    /**
     * 屏幕尺寸宽度，单位像素，正整数
     */
    private int screenWidth;

    /**
     * 屏幕尺寸高度，单位像素, 正整数
     */
    private int screenHeight;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区域
     */
    private String area;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * 请求令牌
     */
    private String accessToken;

    //////////////////////////////////以下为运行时数据////

    /**
     * 用户ID
     */
    private Integer loginId;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 登录过期时间戳
     */
    private Long expireAt;

    /**
     * 请求时间
     */
    private long requestTime = System.currentTimeMillis();

    /**
     * 进度信息
     */
    private ProgressDto progressDto = new ProgressDto();


    public static String getEnv() {
        return System.getProperty(KeyDict.REGISTRY_NAMESPACE_KEY, KeyDict.ENV_DEV);
    }

    public static boolean isProd() {
        return Objects.equals(KeyDict.ENV_PROD, getEnv().toLowerCase());
    }

    private static final ThreadLocal<RequestContext> LOCAL = ThreadLocal.withInitial(RequestContext::new);

    private RequestContext() {
        super();
    }

    private static ThreadLocal<RequestContext> getLocal() {
        return LOCAL;
    }

    public static RequestContext get() {
        return getLocal().get();
    }

    public static void clear() {
        getLocal().remove();
    }

    public static class KeyDict {
        public static final String REQUEST_ID_KEY = "requestId";
        public static final String APPLICATION_CODE_KEY = "applicationCode";
        public static final String APP_VERSION_KEY = "appVersion";
        public static final String USER_ID_KEY = "userId";
        public static final String LOGIN_ID_KEY = "loginId";
        public static final String CLIENT_ID_KEY = "clientId";
        public static final String OS_TYPE_KEY = "osType";
        public static final String COUNTRY_KEY = "country";
        public static final String PROVINCE_KEY = "province";
        public static final String CITY_KEY = "city";
        public static final String AREA_KEY = "area";
        public static final String LANGUAGE_KEY = "language";
        public static final String SCREEN_WIDTH_KEY = "screenWidth";
        public static final String SCREEN_HEIGHT_KEY = "screenHeight";
        public static final String LOGIN_TYPE_KEY = "loginType";
        public static final String ACCESS_TOKEN_KEY = "accessToken";

        public static final String REQUEST_URL_KEY = "requestUrl";

        public static final String REGISTRY_NAMESPACE_KEY = "spring.cloud.nacos.discovery.namespace";
        public static final String ENV_PROD = "prod";
        public static final String ENV_DEV = "dev";

    }

    public Integer getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(Integer applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public ProgressDto getProgressDto() {
        return progressDto;
    }

    public void setProgressDto(ProgressDto progressDto) {
        this.progressDto = progressDto;
    }
}
