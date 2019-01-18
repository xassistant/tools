package bigdata.anomaly.detection.datastructure;

/**
 * Created by Administrator on 2017/7/10.
 */
public class AucttionEvent {

    public String timestamp;         // 时间戳（UTC毫秒数）
    public String platformUserId;    // 用户在平台的ID
    public String impId;             // 展示Id
    public String ua;                // User-agent
    public String ip;                // IP地址
    public String country;           // 国家编码
    public String province;          // 省编码
    public String city;              // 市编码
    public String mediaId;           // App的ID
    public String pageUrl;           // 当前页的url
    public int groupId;              // 组Id
    public int campaignId;           // 活动ID
    public int creativeId;           // 创意Id
    public double bidPrice;          // 出价价格
    public double winPrice;          // 中标价格
    public double originalWinPrice;
    public double originalPayPrice;
    public int adSlotId;             // 广告位Id
    public int adSlotWidth;          // 广告位宽
    public int adSlotHeight;         // 广告位高
    public int adSlotType;           // 广告位类型(1:非视频和原生(banner等) 3:视频 4:原生
    public String userTag;           // 受众标签
    public String os;                // 操作系统
    public String deviceType;        // 设备类型(1:手机, 2:平板, 3:Pc)
    public String osv;               // 操作系统版本
    public int exchangeId;           // 交易市场Id(adview:1013,zplay:1011,凤凰:1009)
    public String bidId;             // 竞价ID
    public String model;             // 机型
    public int carrier;              // 运营商(我们平台的编码,1:移动,2:联通,3电信)
    public int connectionType;       // 网络类型(1:wifi,2:2g,3:3g,4:4g)
    public int payPrice;             // 客户采买价格
    public int memberId;             // 我们系统中的用户Id
    public int priceType;            // 和媒体的结算方式/ 竞价方式(1:cpm,2:cpc)
    public int deviceId;             // 设备Id
    public String deviceIdType;      // deviceId类型
    public int eventType;            // 事件类型,1:点击 2:展示 3:胜出
    public int click;
    public int impression;
    public int win;
    public int duplicate;

    public AucttionEvent() {

    }
}
