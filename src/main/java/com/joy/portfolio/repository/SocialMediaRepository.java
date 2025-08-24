package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.joy.portfolio.entity.SocialMedia;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, String> {

	@Modifying
	@Query("DELETE from SocialMedia socialMedia where socialMedia.id=:id")
	void deleteById(@NonNull() String id);

	@Query(value = "SELECT socialMedia.position FROM social_media socialMedia WHERE socialMedia.user_id = :userId ORDER BY socialMedia.position DESC LIMIT 1", nativeQuery = true)
	Optional<Integer> getLastOrderNumber(String userId);

	@Modifying
	@Query("UPDATE SocialMedia socialMedia SET socialMedia.position = socialMedia.position-1 WHERE socialMedia.position >= :socialMediaOrder AND socialMedia.user.id = :userId")
	void updateAllProjectOrderAfterRemoval(int socialMediaOrder, String userId);
}