package com.GlobalSumi.Internal.UserFile.File.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GlobalSumi.Internal.UserFile.File.model.File;

public interface FileRepository extends JpaRepository<File, Long> {

	File findByParentFolderNameAndUserEmailAndFileName(String parentFolderName, String userEmail,
			String originalFilename);

	@Query(value = "SELECT * FROM user_file f WHERE f.parent_sub_folder_name IS NULL AND f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName", nativeQuery = true)
	List<File> findByParentFolderNameAndUserEmail(String parentFolderName, String userEmail);

	void deleteByParentFolderNameAndUserEmailAndFileName(String parentFolderName, String userEmail, String fileName);

	File findByParentFolderNameAndParentSubFolderNameAndFileNameAndUserEmail(String parentFolderName,
			String parentSubFolderName, String fileName, String userEmail);

	@Query(value = "SELECT * FROM user_file f WHERE f.parent_sub_child_folder_name IS NULL AND f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName AND f.parent_sub_folder_name =:parentSubFolderName", nativeQuery = true)
	List<File> findByParentFolderNameAndParentSubFolderNameAndUserEmail(String parentFolderName,
			String parentSubFolderName, String userEmail);

	File findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndFileNameAndUserEmail(
			String parentFolderName, String parentSubFolderName, String parentSubChildFoldername,
			String originalFilename, String userEmail);

//	@Query(value = "SELECT * FROM user_file f WHERE f.parentSubFinalChildFolderName IS NULL AND f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName AND f.parent_sub_folder_name =:parentSubFolderName AND f.parent_sub_child_folder_name =:parentSubChildFolderName", nativeQuery = true)
//	List<File> findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(
//			String parentFolderName, String parentSubFolderName, String parentSubChildFoldername, String userEmail);

	@Query(value = "SELECT * FROM user_file f WHERE f.parent_sub_final_child_folder_name IS NULL AND f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName AND f.parent_sub_folder_name =:parentSubFolderName AND f.parent_sub_child_folder_name =:parentSubChildFolderName", nativeQuery = true)
	List<File> findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(
	        @Param("parentFolderName") String parentFolderName,
	        @Param("parentSubFolderName") String parentSubFolderName,
	        @Param("parentSubChildFolderName") String parentSubChildFolderName, // Corrected naming
	        @Param("userEmail") String userEmail);
	
	File findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndFileNameAndUserEmail(
			String parentFolderName, String parentSubFolderName, String parentSubChildFoldername,
			String parentSubFinalChildFoldername, String originalFilename, String userEmail);

	@Query(value = "SELECT * FROM user_file f WHERE f.parent_sub_final_child_folder_name IS NOT NULL AND f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName AND f.parent_sub_folder_name =:parentSubFolderName AND f.parent_sub_child_folder_name =:parentSubChildFolderName AND f.parent_sub_final_child_folder_name =:parentSubFinalChildFoldername", nativeQuery = true)
	List<File> findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail(
	        @Param("parentFolderName") String parentFolderName,
	        @Param("parentSubFolderName") String parentSubFolderName,
	        @Param("parentSubChildFolderName") String parentSubChildFolderName,
	        @Param("parentSubFinalChildFoldername") String parentSubFinalChildFoldername,
	        @Param("userEmail") String userEmail);
}
