package com.GlobalSumi.Internal.UserFile.File.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GlobalSumi.Internal.UserFile.File.model.Folder;

import jakarta.transaction.Transactional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findByParentFolderNameAndUserEmail(String parentFolderName, String userEmail);

    @Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_folder_name IS NULL AND f.user_email = :userEmail", nativeQuery = true)
    List<Folder> findByUserEmail(String userEmail);

	void deleteByParentFolderNameAndUserEmail(String parentFolderName, String userEmail);
	
	@Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_child_folder_name IS NULL AND f.parent_sub_folder_name is NOT Null AND  f.user_email = :userEmail AND f.parent_folder_name =:parentFolderName", nativeQuery = true)
	List<Folder> findByUserEmailAndParentFolderName(String userEmail, String parentFolderName);

	@Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_child_folder_name IS NULL AND f.parent_folder_name = :parentFolderName AND f.parent_sub_folder_name = :parentSubFolderName AND f.user_email = :userEmail", nativeQuery = true)
    Folder findByParentFolderNameAndParentSubFolderNameAndUserEmail(@Param("parentFolderName") String parentFolderName,
                                                                      @Param("parentSubFolderName") String parentSubFolderName,
                                                                      @Param("userEmail") String userEmail);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM user_folder f WHERE f.parent_sub_folder_name = :parentSubFolderName AND f.parent_folder_name = :parentFolderName AND f.user_email = :userEmail", nativeQuery = true)
    void deleteByParentSubFolderNameAndParentFolderNameAndUserEmail(@Param("parentSubFolderName") String parentSubFolderName,
                                                                     @Param("parentFolderName") String parentFolderName,
                                                                     @Param("userEmail") String userEmail);

	
	@Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_child_folder_name IS NOT NULL AND f.parent_sub_final_child_folder_name IS NULL AND f.user_email = :userEmail AND f.parent_folder_name = :parentFolderName AND f.parent_sub_folder_name = :parentSubFolderName", nativeQuery = true)
	List<Folder> findByUserEmailAndParentFolderNameAndParentSubFolderName(String userEmail, String parentFolderName, String parentSubFolderName);

	
//	@Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_final_child_folder_name IS NULL AND f.parent_folder_name = :parentFolderName AND f.parent_sub_folder_name = :parentSubFolderName AND f.parent_sub_final_child_folder_name =:f.parentSubFinalChildFolderName AND f.user_email = :userEmail", nativeQuery = true)
	Folder findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(
		    String parentFolderName, String parentSubFolderName, String parentSubChildFolderName, String userEmail);

	void deleteByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndUserEmail(
			String parentSubFolderName, String parentFolderName, String parentSubChildFolderName, String userEmail);

	@Query(value = "SELECT * FROM user_folder f WHERE f.parent_sub_final_child_folder_name IS NOT NULL AND f.user_email = :userEmail AND f.parent_folder_name = :parentFolderName AND f.parent_sub_folder_name = :parentSubFolderName AND f.parent_sub_child_folder_name = :parentSubChildFolderName", nativeQuery = true)
	List<Folder> findByUserEmailAndParentFolderNameAndParentSubFolderNameAndParentSubChildFolderName(
	    String userEmail,
	    String parentFolderName,
	    String parentSubFolderName,
	    String parentSubChildFolderName
	);
	
	Folder findByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail(
	    String parentFolderName,
	    String parentSubFolderName,
	    String parentSubChildFolderName,
	    String parentSubFinalChildFolderName,
	    String userEmail
	);

	void deleteByParentFolderNameAndParentSubFolderNameAndParentSubChildFolderNameAndParentSubFinalChildFolderNameAndUserEmail(
		    String parentFolderName, 
		    String parentSubFolderName, 
		    String parentSubChildFolderName, 
		    String parentSubFinalChildFolderName, 
		    String userEmail
		);
}