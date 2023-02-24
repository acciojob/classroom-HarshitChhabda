package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentMap;
    HashMap<String, Teacher> teacherMAp;
    HashMap<String, List<String>> pairMap;

    public StudentRepository() {
        this.studentMap = new HashMap<>();
        this.teacherMAp = new HashMap<>();
        this.pairMap = new HashMap<>();
    }

    public void addStudent(Student student) {
        if (!studentMap.containsKey(student.getName())) {
            studentMap.put(student.getName(), student);
        }
    }

    public void addTeacher(Teacher teacher) {
        if (!teacherMAp.containsKey(teacher.getName())) {
            teacherMAp.put(teacher.getName(), teacher);
        }
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (!studentMap.containsKey(student) || !teacherMAp.containsKey(teacher)) return;

        if (pairMap.containsKey(teacher)) {
            pairMap.get(teacher).add(student);
        } else {
            List<String> ans = new ArrayList<>();
            ans.add(student);
            pairMap.put(teacher, ans);
        }
    }

    public Student getStudentByName(String name) {
        if (studentMap.containsKey(name)) {
            return studentMap.get(name);
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if (teacherMAp.containsKey(name)) {
            return teacherMAp.get(name);
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return pairMap.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> ans = new ArrayList<>();
        for (String name : studentMap.keySet()) {
            ans.add(name);
        }
        return ans;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> ans = pairMap.get(teacher);
        for (int i = 0; i < ans.size(); i++) {
            if (studentMap.containsKey(ans.get(i))) {
                studentMap.remove(ans.get(i));
            }
        }
        pairMap.remove(teacher);
        if (teacherMAp.containsKey(teacher)) {
            teacherMAp.remove(teacher);
        }
    }

    public void deleteAllTeachers() {

        for (String name : pairMap.keySet()) {
            List<String> ans = pairMap.get(name);
            for (String names : ans) {
                if (studentMap.containsKey(names)) {
                    studentMap.remove(names);
                }
            }
            teacherMAp.remove(name);
        }
        for (String name : teacherMAp.keySet()) {
            teacherMAp.remove(name);
        }
    }

}
