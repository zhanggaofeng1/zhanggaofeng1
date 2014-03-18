/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.action;

import com.num.common.Student;
import com.num.service.TestService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class IndexController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/showInfo.do")
    public String lvInfo(HttpServletRequest request, ModelMap modelMap) {
        List<Student> lvInfo = testService.showLvInfo();
        modelMap.addAttribute("info", lvInfo);
        return "index";
    }

    @RequestMapping("/login.do")
    public ModelAndView login(HttpServletRequest request, ModelMap modelMap) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(610, "Guangzhou", "Ping");
        dataset.addValue(220, "Guangzhou", "Beef");
        dataset.addValue(530, "Guangzhou", "Chicken");
        dataset.addValue(340, "Guangzhou", "Fish");
        JFreeChart chart = ChartFactory.createBarChart("肉类销售统计", "type", "amount", dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);
        
        String img = null;
        try {
            img = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, request.getSession());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        String graphURL = request.getContextPath() + "/DisplayChart?img=" + img;
        
        request.getSession().setAttribute("url", graphURL);
        request.getSession().setAttribute("name", img);
        
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
}
