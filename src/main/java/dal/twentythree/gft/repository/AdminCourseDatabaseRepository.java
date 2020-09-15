package dal.twentythree.gft.repository;

import dal.twentythree.gft.dao.Course;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;

import java.util.List;

public interface AdminCourseDatabaseRepository {
    public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException;
}
