
package com.GlobalSumi.Internal.UserFile.File.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity (name="UserFolder")
public class Folder {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	//@PrimaryKeyJoinColumn
    private String userEmail;
	
    private String parentFolderName;
    private String parentSubFolderName;
    private String parentSubChildFolderName;
    private String parentSubFinalChildFolderName;
    private LocalDateTime creationTime;
    
	public Folder() {
		super();
	}

	public Folder(Long id, String userEmail, String parentFolderName, String parentSubFolderName,
			String parentSubChildFolderName, String parentSubFinalChildFolderName, LocalDateTime creationTime) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.parentFolderName = parentFolderName;
		this.parentSubFolderName = parentSubFolderName;
		this.parentSubChildFolderName = parentSubChildFolderName;
		this.parentSubFinalChildFolderName = parentSubFinalChildFolderName;
		this.creationTime = creationTime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getParentFolderName() {
		return parentFolderName;
	}

	public void setParentFolderName(String parentFolderName) {
		this.parentFolderName = parentFolderName;
	}

	public String getParentSubFolderName() {
		return parentSubFolderName;
	}

	public void setParentSubFolderName(String parentSubFolderName) {
		this.parentSubFolderName = parentSubFolderName;
	}

	public String getParentSubChildFolderName() {
		return parentSubChildFolderName;
	}

	public void setParentSubChildFolderName(String parentSubChildFolderName) {
		this.parentSubChildFolderName = parentSubChildFolderName;
	}

	public String getParentSubFinalChildFolderName() {
		return parentSubFinalChildFolderName;
	}

	public void setParentSubFinalChildFolderName(String parentSubFinalChildFolderName) {
		this.parentSubFinalChildFolderName = parentSubFinalChildFolderName;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
}
