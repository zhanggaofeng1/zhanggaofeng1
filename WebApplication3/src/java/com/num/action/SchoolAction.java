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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
public class SchoolAction {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/getJson.do", method = RequestMethod.GET)
    @ResponseBody
    public Student getJson() {
        Student stu = new Student();
        stu.setId(1);
        stu.setStuId(2);
        stu.setStuName("jj");
        stu.setStuTel("12345");
        stu.setTeaId(4);
        return stu;
    }

    @RequestMapping(value = "/showStudentInfo.do", method = RequestMethod.GET)
    public String showStudentInfo(ModelMap map) {
        List<Student> stus = schoolService.showStudentInfo();
        map.addAttribute("stus", stus);
        return "show_student_info";
    }

    @RequestMapping(value = "/addStudentInfo.do?", params = {"method=setInfo"}, method = RequestMethod.GET)
    public String setStudentInfo() {
        return "add_student_info";
    }

    @RequestMapping(value = "/addStudentInfo.do?", params = {"method=commit"}, method = RequestMethod.GET)
    public String commitStudentInfo() {

        return "show_student_info";
    }
}
