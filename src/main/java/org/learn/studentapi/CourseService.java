package org.learn.studentapi;

import org.learn.studentapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> searchCoursesByTitle(String keyword) {
        return courseRepository.findByTitleContaining(keyword);
    }

    public Course createCourse(Course course, Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()) {
            course.setTeacher(teacher.get());
            return courseRepository.save(course);
        } else {
            throw new ResourceNotFoundException("Teacher not found with id: " + teacherId);
        }
    }

    public Course updateCourse(Long id, Course newCourseData) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setTitle(newCourseData.getTitle());
            course.setDescription(newCourseData.getDescription());
            course.setDurationWeeks(newCourseData.getDurationWeeks());
            return courseRepository.save(course);
        }

        throw new ResourceNotFoundException("Course not found with id: " + id);
    }

    public Course assignTeacher(Long courseId, Long teacherId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);

        if (courseOpt.isPresent() && teacherOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setTeacher(teacherOpt.get());
            return courseRepository.save(course);
        }

        throw new ResourceNotFoundException("Course or Teacher not found");
    }

    // Enroll a student in a course
    public Course enrollStudent(Long courseId, Long studentId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (courseOpt.isPresent() && studentOpt.isPresent()) {
            Course course = courseOpt.get();
            Student student = studentOpt.get();

            // Bidirectional: Add course to student's list and student to course's list
            course.addStudent(student);

            return courseRepository.save(course);
        } else {
            throw new ResourceNotFoundException("Course or Student not found");
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
