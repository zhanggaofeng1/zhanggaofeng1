/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.action;

import com.num.common.CdLv;
import com.num.service.TestService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class IndexController {

    @Autowired
    private TestService testService;

    @RequestMapping("/showLvInfo.do")
    public String lvInfo(HttpServletRequest request, ModelMap modelMap) {
        String lv = request.getParameter("lv");
        List<CdLv> lvInfo = testService.showLvInfo(lv);
        modelMap.addAttribute("info", lvInfo);
        return "login";
    }

    @RequestMapping("/login.do")
    public ModelAndView login(HttpServletRequest request, ModelMap modelMap) {
        request.getSession().setAttribute("uname", "zhang");
        ModelAndView mv = new ModelAndView("login");
        mv.getModelMap().addAttribute("test", "test");
        return mv;
    }
}
