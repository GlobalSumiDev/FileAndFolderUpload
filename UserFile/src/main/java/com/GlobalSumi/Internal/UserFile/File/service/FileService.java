package com.GlobalSumi.Internal.UserFile.File.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.GlobalSumi.Internal.UserFile.File.model.File;
import com.GlobalSumi.Internal.UserFile.File.repository.FileRepository;

import jakarta.transaction.Transactional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public String uploadFileInParentFolder(String parentFolderName, String userEmail, MultipartFile file) {
        try {
            // Check if file already exists
            File existingFile = fileRepository.findByParentFolderNameAndUserEmailAndFileName(parentFolderName, userEmail, file.getOriginalFilename());

            if (existingFile == null) {
                // Create a new file entity
                File newFile = new File();
                newFile.setUserEmail(userEmail);
                newFile.setParentFolderName(parentFolderName);
                newFile.setFileName(file.getOriginalFilename());
                newFile.setUploadTime(LocalDateTime.now());
                newFile.setSize(file.getSize());
                newFile.setData(file.getBytes()); // Save file content as byte array or handle accordingly

                // Save file entity to the repository
                fileRepository.save(newFile);

                return "File uploaded successfully";
            } else {
                return "File already exists";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }

	public List<File> getFileInParentFolder(String parentFolderName, String userEmail) {
		// TODO Auto-generated method stub
		return fileRepository.findByParentFolderNameAndUserEmail(parentFolderName,userEmail);
	}

	public File getFileByName(String parentFolderName, String userEmail, String fileName) {
		// TODO Auto-generated method stub
		return fileRepository.findByParentFolderNameAndUserEmailAndFileName(parentFolderName, userEmail, fileName);
	}

	@Transactional
	public String deleteFileByFileNameUserEmailParentFolder(String parentFolderName, String userEmail, String fileName) {
	    try {
	        // Log the inputs for debugging
	        System.out.println("Attempting to delete file: " + fileName + " in folder: " + parentFolderName + " for user: " + userEmail);

	        File existingFile = fileRepository.findByParentFolderNameAndUserEmailAndFileName(parentFolderName, userEmail, fileName);
	        if (existingFile != null) {
	            fileRepository.deleteByParentFolderNameAndUserEmailAndFileName(parentFolderName, userEmail, fileName);
	            return "File Deleted Successfully";
	        } else {
	            return "File Not Found";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred while deleting the file.";
	    }
	}

	public String uploadFileInParentSubFolder(String parentFolderName, String parentSubFolderName, MultipartFile file,
			String userEmail) {
		 try {
	            // Check if file already exists
	            File existingFile = fileRepository.findByParentFolderNameAndParentSubFolderNameAndFileNameAndUserEmail(parentFolderName,parentSubFolderName,file.getOriginalFilename(), userEmail );

	            if (existingFile == null) {
	                // Create a new file entity
	                File newFile = new File();
	                newFile.setUserEmail(userEmail);
	                newFile.setParentFolderName(parentFolderName);
	                newFile.setParentSubFolderName(parentSubFolderName);
	                newFile.setFileName(file.getOriginalFilename());
	                newFile.setUploadTime(LocalDateTime.now());
	                newFile.setSize(file.getSize());
	                newFile.setData(file.getBytes()); // Save file content as byte array or handle accordingly

	                // Save file entity to the repository
	                fileRepository.save(newFile);

	                return "File uploaded successfully";
	            } else {
	                return "File already exists";
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Failed to upload file";
	        }
	}

	public List<File> getFileInParentSubFolder(String parentFolderName, String parentSubFolderName, String userEmail) {
		// TODO Auto-generated method stub
		return fileRepository.findByParentFolderNameAndParentSubFolderNameAndUserEmail(parentFolderName,parentSubFolderName,userEmail);
	}

	public String uploadFileInParentSubChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFoldername, MultipartFile file, String userEmail) {
		 try {
	            // Check if file already exists
	            File existingFile = fileRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndFileNameAndUserEmail(parentFolderName,parentSubFolderName,parentSubChildFoldername,file.getOriginalFilename(), userEmail );

	            if (existingFile == null) {
	                // Create a new file entity
	                File newFile = new File();
	                newFile.setUserEmail(userEmail);
	                newFile.setParentFolderName(parentFolderName);
	                newFile.setParentSubFolderName(parentSubFolderName);
	                newFile.setParentSubChildFolderName(parentSubChildFoldername);
	                newFile.setFileName(file.getOriginalFilename());
	                newFile.setUploadTime(LocalDateTime.now());
	                newFile.setSize(file.getSize());
	                newFile.setData(file.getBytes()); // Save file content as byte array or handle accordingly

	                // Save file entity to the repository
	                fileRepository.save(newFile);

	                return "File uploaded successfully";
	            } else {
	                return "File already exists";
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Failed to upload file";
	        }
	}

	public List<File> getFileInParentSubChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFoldername, String userEmail) {
		return fileRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(parentFolderName,parentSubFolderName,parentSubChildFoldername,userEmail);
	}

	public String uploadFileInParentSubFinalChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFoldername, String parentSubFinalChildFoldername, MultipartFile file,
			String userEmail) {
		try {
            // Check if file already exists
            File existingFile = fileRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndFileNameAndUserEmail(parentFolderName,parentSubFolderName,parentSubChildFoldername,parentSubFinalChildFoldername,file.getOriginalFilename(), userEmail );

            if (existingFile == null) {
                // Create a new file entity
                File newFile = new File();
                newFile.setUserEmail(userEmail);
                newFile.setParentFolderName(parentFolderName);
                newFile.setParentSubFolderName(parentSubFolderName);
                newFile.setParentSubChildFolderName(parentSubChildFoldername);
                newFile.setParentSubFinalChildFolderName(parentSubFinalChildFoldername);
                newFile.setFileName(file.getOriginalFilename());
                newFile.setUploadTime(LocalDateTime.now());
                newFile.setSize(file.getSize());
                newFile.setData(file.getBytes()); // Save file content as byte array or handle accordingly

                // Save file entity to the repository
                fileRepository.save(newFile);

                return "File uploaded successfully";
            } else {
                return "File already exists";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
	}

	public List<File> getFileInParentFinalSubChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFoldername, String parentSubFinalChildFoldername, String userEmail) {
		// TODO Auto-generated method stub
		return fileRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail(parentFolderName,parentSubFolderName,parentSubChildFoldername,parentSubFinalChildFoldername,userEmail);
		}

}

