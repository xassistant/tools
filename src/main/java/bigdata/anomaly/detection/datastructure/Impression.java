package bigdata.anomaly.detection.datastructure;

/**
 * Created by Administrator on 2017/5/31.
 */
public class Impression {

    private String eventType;      // 事件类型
    private String requestId;      // 请求id
    private String mediaName;      // 媒体名称
    private String materialId;     // 素材id
    private String impressionId;   // 曝光id
    private String impressTime;    // 曝光时间
    private String operator;       // 运营商
    private String networkType;    // 网络类型
    private String deviceType;     // 设备类型
    private String osType;         // 操作系统
    private String areaCode;       // 地域编码
    private String planId;         // 计划id
    private String advertiserId;   // 广告主id
    private String mediaPrice;     // 媒体价格
    private String customerPrice;  // 客户价格
    private String userId;         // userid
    private String appName;        // app名称

    public Impression() {

    }

    public String getAttrByName(String attrName)throws Exception {
        String ans;
        switch (attrName) {
            case "appName":
                ans = getAppName();
                break;
            case "areaCode":
                ans = getAreaCode();
                break;
            case "deviceType":
                ans = getDeviceType();
                break;
            case "mediaName":
                ans = getMediaName();
                break;
            case "osType":
                ans = getOsType();
                break;
            case "userId":
                ans = getUserId();
                break;
            default:
                throw new Exception("Wrong attrName!");
        }
        return ans;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getImpressionId() {
        return impressionId;
    }

    public void setImpressionId(String impressionId) {
        this.impressionId = impressionId;
    }

    public String getImpressTime() {
        return impressTime;
    }

    public void setImpressTime(String impressTime) {
        this.impressTime = impressTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getMediaPrice() {
        return mediaPrice;
    }

    public void setMediaPrice(String mediaPrice) {
        this.mediaPrice = mediaPrice;
    }

    public String getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(String customerPrice) {
        this.customerPrice = customerPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}