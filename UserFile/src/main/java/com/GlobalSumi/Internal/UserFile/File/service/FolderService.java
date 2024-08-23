package com.GlobalSumi.Internal.UserFile.File.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GlobalSumi.Internal.UserFile.File.model.Folder;
import com.GlobalSumi.Internal.UserFile.File.repository.FolderRepository;

import jakarta.transaction.Transactional;

@Service
public class FolderService {

	@Autowired
	FolderRepository folderRepository;

	public String createParentFolder(String parentFolderName, String userEmail) {
		Folder existingFolder = folderRepository.findByParentFolderNameAndUserEmail(parentFolderName, userEmail);
		if (existingFolder == null) {
			Folder folder = new Folder();
			folder.setUserEmail(userEmail);
			folder.setParentFolderName(parentFolderName);
			folder.setCreationTime(LocalDateTime.now());
			folderRepository.save(folder);
			return "Folder Created Successfully";
		} else {
			return "Folder already exists";
		}

	}

    public List<Folder> getAllFolderByUsername(String userEmail) {
        return folderRepository.findByUserEmail(userEmail);
    }

	@Transactional
	public String deleteFolderbyUserNameAndUserEmail(String parentFolderName, String userEmail) {
		Folder existingFolder = folderRepository.findByParentFolderNameAndUserEmail(parentFolderName, userEmail);
		if (existingFolder == null) {
			return "Folder does not exists!!!";
		} else {
			folderRepository.deleteByParentFolderNameAndUserEmail(parentFolderName, userEmail);
			return "Folder Deleted Successfully";
		}

	}

	public List<Folder> getAllFolderByUsernameAndFolderName(String parentFolderName, String userEmail) {
		// TODO Auto-generated method stub
		return folderRepository.findByUserEmailAndParentFolderName(userEmail, parentFolderName);
	}

	public String createParentSubFolder(String parentFolderName, String parentSubFolderName, String userEmail) {

		Folder existingFolder = folderRepository.findByParentFolderNameAndParentSubFolderNameAndUserEmail(
				parentFolderName, parentSubFolderName, userEmail);
		if (existingFolder == null) {
			Folder folder = new Folder();
			folder.setUserEmail(userEmail);
			folder.setParentFolderName(parentFolderName);
			folder.setParentSubFolderName(parentSubFolderName);
			folder.setCreationTime(LocalDateTime.now());
			folderRepository.save(folder);
			return "Folder Created Successfully";
		} else {
			return "Folder already exists";
		}
	}

	@Transactional
	public String deleteParentSubFolder(String parentSubFolderName, String parentFolderName, String userEmail) {
	    try {
	        Folder existingFolder = folderRepository.findByParentFolderNameAndParentSubFolderNameAndUserEmail(parentFolderName, parentSubFolderName, userEmail);
	        if (existingFolder == null) {
	            return "Folder does not exist!";
	        } else {
	            folderRepository.deleteByParentSubFolderNameAndParentFolderNameAndUserEmail(parentSubFolderName, parentFolderName, userEmail);
	            return "Folder deleted successfully!";
	        }
	    } catch (Exception e) {
	        // Log the exception
	        e.printStackTrace();
	        return "An error occurred while deleting the folder.";
	    }
	}

	public List<Folder> getAllParentSubFolderByUsernameAndFolderName(String parentFolderName,
	        String parentSubFolderName, String userEmail) {
	    System.out.println("userEmail: " + userEmail);
	    System.out.println("parentFolderName: " + parentFolderName);
	    System.out.println("parentSubFolderName: " + parentSubFolderName);
	    List<Folder> folders = folderRepository.findByUserEmailAndParentFolderNameAndParentSubFolderName(userEmail,parentFolderName, parentSubFolderName);
	    System.out.println("Folders found: " + folders.size());
	    return folders;
	}

	public String createParentSubChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFolderName, String userEmail) {

		 Folder existingFolder = folderRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(
		            parentFolderName, parentSubFolderName, parentSubChildFolderName, userEmail);

			if (existingFolder == null) {
				Folder folder = new Folder();
				folder.setUserEmail(userEmail);
				folder.setParentFolderName(parentFolderName);
				folder.setParentSubFolderName(parentSubFolderName);
				folder.setParentSubChildFolderName(parentSubChildFolderName);
				folder.setCreationTime(LocalDateTime.now());
				folderRepository.save(folder);
				return "Folder Created Successfully";
			} else {
				return "Folder already exists";
			}
	}

	@Transactional
	public String deleteParentSubChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFolderName, String userEmail) {
		try {
	            folderRepository.deleteByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(parentFolderName,parentSubFolderName, parentSubChildFolderName,userEmail);
	            return "Folder deleted successfully!";
	        
	    } catch (Exception e) {
	        // Log the exception
	        e.printStackTrace();
	        return "An error occurred while deleting the folder.";
	    }
	}

	public List<Folder> getAllParentSubChildFolderByUsernameAndFolderName(String parentFolderName,
			String parentSubFolderName, String parentSubChildFolderName, String userEmail) {
		System.out.println("userEmail: " + userEmail);
	    System.out.println("parentFolderName: " + parentFolderName);
	    System.out.println("parentSubFolderName: " + parentSubFolderName);
	    System.out.println("parentSubChildFolderName: " + parentSubChildFolderName);
	    List<Folder> folders = folderRepository.findByUserEmailAndParentFolderNameAndParentSubFolderNameAndParentSubChildFolderName(userEmail, parentFolderName, parentSubFolderName, parentSubChildFolderName);
	    System.out.println("Folders found: " + folders.size());
	    return folders;
	}

	public String createParentSubFinalChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFolderName, String parentSubFinalChildFolderName, String userEmail) {
		Folder existingFolder = folderRepository.findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail(
	            parentFolderName, parentSubFolderName, parentSubChildFolderName,parentSubFinalChildFolderName, userEmail);

		if (existingFolder == null) {
			Folder folder = new Folder();
			folder.setUserEmail(userEmail);
			folder.setParentFolderName(parentFolderName);
			folder.setParentSubFolderName(parentSubFolderName);
			folder.setParentSubChildFolderName(parentSubChildFolderName);
			folder.setParentSubFinalChildFolderName(parentSubFinalChildFolderName);
			folder.setCreationTime(LocalDateTime.now());
			folderRepository.save(folder);
			return "Folder Created Successfully";
		} else {
			return "Folder already exists";
		}
	}

	@Transactional
	public String deleteParentSubFinalChildFolder(String parentFolderName, String parentSubFolderName,
			String parentSubChildFolderName, String parentSubFinalChildFolderName, String userEmail) {
		try {
            folderRepository.deleteByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail
            (parentFolderName,parentSubFolderName, parentSubChildFolderName,parentSubFinalChildFolderName,userEmail);
            return "Folder deleted successfully!";
        
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
        return "An error occurred while deleting the folder.";
    }
	}

	
}
