/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package echarts.samples.bar;

import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.google.gson.Gson;
import echarts.util.EnhancedOption;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
/**
 * @author liuzh
 */
public class BarTest2 {

    @Test
    public void test() {
        //地址：http://echarts.baidu.com/doc/example/bar4.html
        EnhancedOption option = new EnhancedOption();
        option.calculable(true);
        CategoryAxis data = new CategoryAxis().data("一");
        data.data("日aaaaaaaaaaaaaaaaa");
        data.data("的");
        data.data("发");

        option.yAxis(new ValueAxis());
        option.xAxis(data);
        Bar bar = new Bar();
        bar.itemStyle().normal().label().show(true).position("insideRight");
        bar.data(320, 302, 301, 334);
        bar.barWidth(24);
        AxisLabel axisLabel = new AxisLabel();
        axisLabel.interval(0);
        axisLabel.rotate(-10);

        data.axisLabel(axisLabel);
        option.series(bar);
        System.out.println(new Gson().toJson(option));
        option.exportToHtml("bar4.html");
        option.view();
    }
}
