package com.pixels.Nexum.controller;

import com.pixels.Nexum.model.FileModel;
import com.pixels.Nexum.model.User;
import com.pixels.Nexum.repository.UserRepository;
import com.pixels.Nexum.service.FileService;
import com.pixels.Nexum.service.UserDetailsServiceImp;
import com.pixels.Nexum.utils.APIReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Vector;

@RestController
@RequestMapping("/api/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepository userService;

    private APIReturnModel apiReturnModel;
    private Vector<FileModel> fileVec;


    @GetMapping("/getAllFiles")
    public ResponseEntity<?> getAllFiles(){
        apiReturnModel = new APIReturnModel();
        fileVec = new Vector<>();

        try {
            fileVec = this.fileService.getAllFiles();
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("All Files");
            apiReturnModel.setCount(fileVec.size());
            apiReturnModel.setData(fileVec);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @DeleteMapping("/deleteFile/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable int fileId){
        apiReturnModel = new APIReturnModel();
        fileVec = new Vector<>();

        try {
            this.fileService.deleteFile(fileId);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("File Deleted Successfully !");
            apiReturnModel.setCount(fileVec.size());
            apiReturnModel.setData(fileVec);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam int userId){
        apiReturnModel = new APIReturnModel();
        fileVec = new Vector<>();

        try {
            User user = this.userService.getUserById(userId);
            FileModel fileModel = new FileModel();
            fileModel.setFileName(file.getOriginalFilename());
            fileModel.setFileType(file.getContentType());
            fileModel.setFileData(file.getBytes());
            fileModel.setUser(user);
            FileModel fileModel1 = this.fileService.uploadFile(fileModel);
            fileVec.add(fileModel1);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("File Uploaded Successfully !");
            apiReturnModel.setCount(fileVec.size());
            apiReturnModel.setData(fileVec);
        } catch (IOException e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }


}

