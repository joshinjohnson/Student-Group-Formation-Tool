package dal.twentythree.gft.repository.impl;

import dal.twentythree.gft.config.DBConfig;
import dal.twentythree.gft.dao.Course;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;
import dal.twentythree.gft.repository.AdminCourseDatabaseRepository;
import dal.twentythree.gft.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dal.twentythree.gft.util.Constants.*;

public class AdminCourseDatabaseRepositoryImpl implements AdminCourseDatabaseRepository {
    Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());

    @Override
    public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException {
        List<Course> courseList = new ArrayList<Course>();
        Connection con = null;
        try {
            con = DBConfig.getDBConfigInstance().getConnectionInstance();
            String selectSql = selectCourses;
            PreparedStatement insertionps = con.prepareStatement(selectSql);
            ResultSet rs = insertionps.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCourseId(rs.getString(COURSEID));
                course.setCourseName(rs.getString(COURSENAME));
                courseList.add(course);
            }

        }	 catch (SQLException e) {
            myLogger.info("An exception occurred while retrieving Courses from Database ", e);
            throw new CourseGroupFormationException("There was an error while fetching the list of courses.");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    myLogger.info("Failed to close database connection ", e);
                }
            }
        }
        return courseList;
    }
}
