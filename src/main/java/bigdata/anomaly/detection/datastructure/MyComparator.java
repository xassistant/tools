package bigdata.anomaly.detection.datastructure;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/7/12.
 */
public class MyComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        AttrInfo info1 = (AttrInfo) o1;
        AttrInfo info2 = (AttrInfo) o2;

        int cnt1 = info1.getCounts();
        int cnt2 = info2.getCounts();

        if (cnt1 < cnt2) {
            return 1;
        } else if (cnt1 == cnt2) {
            return 0;
        } else {
            return -1;
        }
    }
}
