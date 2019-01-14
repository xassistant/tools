package echarts.samples.pie;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.LabelLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.google.gson.Gson;
import echarts.util.EnhancedOption;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
/**
 * @Author xlj
 * @Date 2018/12/8 16:39
 */
public class PieTest5 {
    @Test
    public void test() {

        EnhancedOption option = new EnhancedOption();
        option.title().text("你幸福吗？")
                .x(X.center);
//        option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.legend().orient(Orient.vertical).x(X.right).data("1","2","3");

        Pie pie = new Pie("1");
        pie.label().normal().show(true).formatter("{d}%");
        pie.data(new PieData("1", 68)).data(new PieData("3", 3)).data(new PieData("2", 29));
        pie.center("50%", "45%").radius("50%");
        option.series(pie);
        System.out.println(new Gson().toJson(option));

        option.exportToHtml("pie6.html");
        option.view();
    }
}
