package com.GlobalSumi.Internal.UserFile.File.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name = "UserFile")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String userEmail;
    private LocalDateTime uploadTime;
    private String parentFolderName;
    private String parentSubFolderName;
    private String parentSubChildFolderName;
    private String parentSubFinalChildFolderName;

    @Column(name = "file_size", columnDefinition = "BIGINT", nullable = false)
    private long size;

    @Lob
    private byte[] data;


    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
