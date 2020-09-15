package dal.twentythree.gft.controller;

import dal.twentythree.gft.config.DBConfig;
import dal.twentythree.gft.config.SecurityConfig;
import dal.twentythree.gft.dao.Course;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;
import dal.twentythree.gft.repository.AdminCourseDatabaseRepository;
import dal.twentythree.gft.repository.impl.CourseDatabaseRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
@Controller
public class AdminController {
//    private final String EMAIL = "email";

    @GetMapping("/admin")
    public String showAdminDashboard() { return "admin/dashboard"; }

    @GetMapping("/admin/courses" )
    public String showCourses(Model model) {
        List<Course> courseList=new ArrayList<Course>();
        CourseDatabaseRepositoryImpl courseDB = new CourseDatabaseRepositoryImpl();
        try {
            courseList=courseDB.getAllCourses();
            System.out.println(courseList);
        } catch (CopyCatMeDBConfigException e) {
            e.printStackTrace();
        } catch (CourseGroupFormationException e) {
            e.printStackTrace();
        }

        model.addAttribute("courses",courseList);
        return "admin/courses";
    }

    @GetMapping("/admin/instructors")
    public String showInstructors() { return "admin/instructors"; }

    @GetMapping("/admin/addCourse")
    public String addCourse() {
        return "admin/addCourse";
    }

    @GetMapping("/admin/addInstructor")
    public String addInstructor() { return "admin/addInstructor"; }

}
