package bigdata.anomaly.detection.datastructure;

/**
 * Created by Administrator on 2017/6/12.
 */
public class AttrInfo {

    private String attrName;
    private int counts;
    private double percentage;

    public AttrInfo(String attrName, int counts, double percentage) {
        this.attrName = attrName;
        this.counts = counts;
        this.percentage = percentage;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
