package com.GlobalSumi.Internal.UserFile.File.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GlobalSumi.Internal.UserFile.File.model.Folder;
import com.GlobalSumi.Internal.UserFile.File.service.FolderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin(origins = {"http://localhost:4200", "https://globalsumi.com"})
public class FolderController {

    private static final Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;
    
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Application is running");
    }
  
    @PostMapping("/createParentFolder")
    public ResponseEntity<?> createParentFolder(@RequestParam String parentFolderName, @RequestParam String userEmail) {
        // Your logic to create the folder
    	String result = folderService.createParentFolder(parentFolderName, userEmail);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        if ("Folder Created Successfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

    }
    
    @GetMapping("/byUserEmail")
    public ResponseEntity<List<Folder>> getAllFolderByUsername(
            @RequestParam("userEmail") String userEmail) {
        List<Folder> folders = folderService.getAllFolderByUsername(userEmail);
        if (folders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(folders);
    }
    
 
    
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteFolderbyUserNameAndUserEmail(
            @RequestParam("ParentFolderName") String parentFolderName,
            @RequestParam("userEmail") String userEmail) {

        String result = folderService.deleteFolderbyUserNameAndUserEmail(parentFolderName, userEmail);
        Map<String, String> response = new HashMap<>();
        
        if ("Folder Deleted Successfully".equals(result)) {
            response.put("message", "Folder Deleted Successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please contact Support! Folder Deletion Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    
    @GetMapping("/{ParentFolderName}/files")
    public ResponseEntity<List<Folder>> getAllFolderByUsernameAndFolderName(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@RequestParam("userEmail") String userEmail) {
        List<Folder> folders = folderService.getAllFolderByUsernameAndFolderName(parentFolderName,userEmail);
        if (folders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(folders);
    }
    
    @PostMapping("/{ParentFolderName}/createParentSubFolder")
    public ResponseEntity<?> createParentSubFolder(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@RequestParam String parentSubFolderName,
    		@RequestParam String userEmail) {
        // Your logic to create the folder
    	String result = folderService.createParentSubFolder(parentFolderName,parentSubFolderName, userEmail);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        if ("Folder Created Successfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    
    @DeleteMapping("/{ParentFolderName}/deleteParentSubFolder")
    public ResponseEntity<Map<String, String>> deleteParentSubFolder(
            @RequestParam("ParentSubFolderName") String parentSubFolderName,
            @PathVariable("ParentFolderName") String parentFolderName,
            @RequestParam("userEmail") String userEmail) {

        String result = folderService.deleteParentSubFolder(parentSubFolderName, parentFolderName, userEmail);
        Map<String, String> response = new HashMap<>();

        if ("Folder deleted successfully!".equals(result)) {
            response.put("message", "Folder deleted successfully!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please contact Support! Folder Deletion Failed. Reason: " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    
    @GetMapping("/{ParentFolderName}/{ParentSubFolderName}/ParentSubFolders")
    public ResponseEntity<List<Folder>> getAllParentSubFolderByUsernameAndFolderName(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@PathVariable("ParentSubFolderName") String parentSubFolderName, 
    		@RequestParam("userEmail") String userEmail) {
        List<Folder> folders = folderService.getAllParentSubFolderByUsernameAndFolderName(parentFolderName,parentSubFolderName,userEmail);
        if (folders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(folders);
    }
    
    @PostMapping("/{ParentFolderName}/{ParentSubFolderName}/parentSubChildFolder")
    public ResponseEntity<?> createParentSubFolder(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@PathVariable("ParentSubFolderName") String parentSubFolderName, 
    		@RequestParam String parentSubChildFolderName,
    		@RequestParam String userEmail) {
        // Your logic to create the folder
    	String result = folderService.createParentSubChildFolder(parentFolderName,parentSubFolderName,parentSubChildFolderName,userEmail);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        if ("Folder Created Successfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    
    @DeleteMapping("/{ParentFolderName}/{ParentSubFolderName}/deleteParentSubChildFolder")
    public ResponseEntity<Map<String, String>> deleteParentSubChildFolder(
    		@PathVariable("ParentFolderName") String parentFolderName,
            @PathVariable("ParentSubFolderName") String parentSubFolderName, 
            @RequestParam String parentSubChildFolderName,
            @RequestParam("userEmail") String userEmail) {

        String result = folderService.deleteParentSubChildFolder(parentFolderName,parentSubFolderName,parentSubChildFolderName, userEmail);
        Map<String, String> response = new HashMap<>();

        if ("Folder deleted successfully!".equals(result)) {
            response.put("message", "Folder deleted successfully!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please contact Support! Folder Deletion Failed. Reason: " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/{ParentFolderName}/{ParentSubFolderName}/{parentSubChildFolderName}/ParentSubChildFolders")
    public ResponseEntity<List<Folder>> getAllParentSubChildFolderByUsernameAndFolderName(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@PathVariable("ParentSubFolderName") String parentSubFolderName, 
    		@PathVariable("parentSubChildFolderName") String parentSubChildFolderName,
    		@RequestParam("userEmail") String userEmail) {
        List<Folder> folders = folderService.getAllParentSubChildFolderByUsernameAndFolderName(parentFolderName,parentSubFolderName,parentSubChildFolderName,userEmail);
        if (folders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(folders);
    }
    
    @PostMapping("/{ParentFolderName}/{ParentSubFolderName}/{parentSubChildFolderName}/createParentSubFinalChildFolder")
    public ResponseEntity<?> createParentSubFinalChildFolder(
    		@PathVariable("ParentFolderName") String parentFolderName, 
    		@PathVariable("ParentSubFolderName") String parentSubFolderName, 
    		@PathVariable("parentSubChildFolderName") String parentSubChildFolderName,
    		@RequestParam String parentSubFinalChildFolderName,
    		@RequestParam String userEmail) {
        // Your logic to create the folder
    	String result = folderService.createParentSubFinalChildFolder(parentFolderName,parentSubFolderName,parentSubChildFolderName,parentSubFinalChildFolderName,userEmail);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        if ("Folder Created Successfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    
    @DeleteMapping("/{ParentFolderName}/{ParentSubFolderName}/{parentSubChildFolderName}/deleteParentSubFinalChildFolder")
    public ResponseEntity<Map<String, String>> deleteParentSubFinalChildFolder(
    		@PathVariable("ParentFolderName") String parentFolderName,
            @PathVariable("ParentSubFolderName") String parentSubFolderName, 
            @PathVariable("parentSubChildFolderName") String parentSubChildFolderName,
            @RequestParam String parentSubFinalChildFolderName,
            @RequestParam("userEmail") String userEmail) {

        String result = folderService.deleteParentSubFinalChildFolder(parentFolderName,parentSubFolderName,parentSubChildFolderName,parentSubFinalChildFolderName,userEmail);
        Map<String, String> response = new HashMap<>();

        if ("Folder deleted successfully!".equals(result)) {
            response.put("message", "Folder deleted successfully!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please contact Support! Folder Deletion Failed. Reason: " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}