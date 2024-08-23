package com.GlobalSumi.Internal.UserFile.File.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.GlobalSumi.Internal.UserFile.File.model.File;
import com.GlobalSumi.Internal.UserFile.File.service.FileService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = {"http://localhost:4200", "https://globalsumi.com"})
public class FileController {

    @Autowired
    private FileService fileService;
    
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFileInParentFolder(
            @RequestParam String parentFolderName,
            @RequestParam String userEmail,
            @RequestParam("file") MultipartFile file) {
        
        // Call the service to handle the file upload
        String result = fileService.uploadFileInParentFolder(parentFolderName, userEmail, file);
        
        // Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", result);  // Include the message in the response

        if ("File already exists".equals(result)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 409 Conflict
        } else if ("Failed to upload file".equals(result)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error
        } else {
            response.put("message", "File uploaded successfully");
            return ResponseEntity.ok(response); // 200 OK
        }
    }
    
    @GetMapping("/parentFolder")
    public ResponseEntity<List<File>> getFileInParentFolder(
            @RequestParam String parentFolderName,
            @RequestParam String userEmail) {
        List<File> files = fileService.getFileInParentFolder(parentFolderName, userEmail);
        if (files.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }
    
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable String fileName,
            @RequestParam String parentFolderName,
            @RequestParam String userEmail) {
    	File file = fileService.getFileByName(parentFolderName, userEmail, fileName);
    	if (file == null) {
            return ResponseEntity.notFound().build();
        }
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(file.getData());
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteFileByFileNameUserEmailParentFolder(
            @RequestParam("fileName") String fileName,
            @RequestParam("parentFolderName") String parentFolderName,
            @RequestParam("userEmail") String userEmail) {

        // Decode the file name to handle URL-encoded characters
        String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

        String result = fileService.deleteFileByFileNameUserEmailParentFolder(parentFolderName, userEmail, decodedFileName);
        Map<String, String> response = new HashMap<>();

        if ("File Deleted Successfully".equals(result)) {
            response.put("message", "File Deleted Successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please contact Support! File Deletion Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/uploadParentSubFile")
    public ResponseEntity<?> uploadFileInParentSubFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam("file") MultipartFile file,
            @RequestParam String userEmail) {
        
        // Call the service to handle the file upload
        String result = fileService.uploadFileInParentSubFolder(parentFolderName, parentSubFolderName, file, userEmail);
        
        // Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response); // Ensure the response is a proper JSON format
    }
    
    @GetMapping("/parentSubFolder/Files")
    public ResponseEntity<List<File>> getFileInParentSubFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam String userEmail) {
        List<File> files = fileService.getFileInParentSubFolder(parentFolderName,parentSubFolderName, userEmail);
        if (files.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }
    
    @PostMapping("/uploadParentSubChildFile")
    public ResponseEntity<?> uploadFileInParentSubChildFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam String parentSubChildFoldername,
            @RequestParam("file") MultipartFile file,
            @RequestParam String userEmail) {
        
        // Call the service to handle the file upload
        String result = fileService.uploadFileInParentSubChildFolder(parentFolderName, parentSubFolderName, parentSubChildFoldername, file, userEmail);
        
        // Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response); // Ensure the response is a proper JSON format
    }
    
    @GetMapping("/parentSubChildFolder/Files")
    public ResponseEntity<List<File>> getFileInParentSubChildFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam String parentSubChildFoldername,
            @RequestParam String userEmail) {
        List<File> files = fileService.getFileInParentSubChildFolder(parentFolderName,parentSubFolderName,parentSubChildFoldername, userEmail);
        if (files.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }
    	
    @PostMapping("/uploadParentSubFinalChildFile")
    public ResponseEntity<?> uploadFileInParentSubFinalChildFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam String parentSubChildFoldername,
            @RequestParam String parentSubFinalChildFoldername,
            @RequestParam("file") MultipartFile file,
            @RequestParam String userEmail) {
        
        // Call the service to handle the file upload
        String result = fileService.uploadFileInParentSubFinalChildFolder(parentFolderName, parentSubFolderName, parentSubChildFoldername, parentSubFinalChildFoldername, file, userEmail);
        
        // Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response); // Ensure the response is a proper JSON format
    }
    
    @GetMapping("/parentFinalSubChildFolder/Files")
    public ResponseEntity<List<File>> getFileInParentSubChildFolder(
            @RequestParam String parentFolderName,
            @RequestParam String parentSubFolderName,
            @RequestParam String parentSubChildFoldername,
            @RequestParam String parentSubFinalChildFoldername,
            @RequestParam String userEmail) {
        List<File> files = fileService.getFileInParentFinalSubChildFolder(parentFolderName,parentSubFolderName,parentSubChildFoldername,parentSubFinalChildFoldername, userEmail);
        if (files.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }
}


