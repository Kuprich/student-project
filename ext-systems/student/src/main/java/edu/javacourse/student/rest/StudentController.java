package edu.javacourse.student.rest;

import edu.javacourse.student.service.StudentService;
import edu.javacourse.student.view.StudentRequest;
import edu.javacourse.student.view.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public List<StudentResponse> getStudentInfo(@RequestBody StudentRequest request) {
        return studentService.getStudentInfo(request);
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") int id, @RequestParam("value") String value) {
        return "Rest service is working. Id: " + id + " value: " + value;
    }

    @PostMapping(path = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPhoto(@RequestParam("comment") String comment, @RequestParam("photoFile") MultipartFile photoFile){
        try (InputStream is = photoFile.getInputStream()) {
            return "Comment: " + comment + ", Name: " + photoFile.getName()
                    + " File Name: " + photoFile.getOriginalFilename() + ", Size: " + is.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
