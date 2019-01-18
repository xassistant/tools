package bigdata.anomaly.detection.datastructure;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Click {

    private String eventType;      // 事件类型
    private String requestId;      // 请求id
    private String mediaName;      // 媒体名称
    private String materialId;     // 素材id
    private String impressionId;   // 曝光id
    private String clickTime;      // 点击时间
    private String operator;       // 运营商
    private String networkType;    // 网络类型
    private String deviceType;     // 设备类型
    private String os;             // 操作系统
    private String areaCode;       // 地域编码
    private String planId;         // 计划id
    private String advertiserId;   // 广告主id
    private String mediaPrice;     // 媒体价格
    private String customerPrice;  // 客户价格
    private String userId;         // userid

    public static void main(String[] args) {
        for (Field field : Click.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }

    public Click() {

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

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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
}