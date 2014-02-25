/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.action;

import com.num.service.SchoolService;
import com.num.tab.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
public class SchoolAction {
    
    @Autowired
    private SchoolService schoolService;
    
    @RequestMapping(value = "/showStudentInfo.do")
    public String showStudentInfo(ModelMap map) {
        List<Student> stus = schoolService.showStudentInfo();
        map.addAttribute("stus", stus);
        return "show_student_info";
    }
    
    @RequestMapping(value = "/addStudentInfo.do?", params = {"method=setInfo"})
    public String setStudentInfo() {
        return "add_student_info";
    }
    
    @RequestMapping(value = "/addStudentInfo.do?", params = {"method=commit"})
    public String commitStudentInfo() {
        
        return "show_student_info";
    }
    
}
