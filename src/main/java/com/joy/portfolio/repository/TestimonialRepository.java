package com.joy.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.joy.portfolio.entity.Testimonial;

public interface TestimonialRepository extends JpaRepository<Testimonial, String> {

	@Modifying
	@Query("DELETE from Testimonial testimonial where testimonial.id=:id")
	void deleteById(@NonNull() String id);

	@Query(value = "SELECT testimonial.position FROM testimonial WHERE testimonial.user_id = :userId ORDER BY testimonial.position DESC LIMIT 1", nativeQuery = true)
	Optional<Integer> getLastOrderNumber(String userId);

	@Modifying
	@Query("UPDATE Testimonial testimonial SET testimonial.position = testimonial.position-1 WHERE testimonial.position >= :testimonialOrder AND testimonial.user.id = :userId")
	void updateAllProjectOrderAfterRemoval(int testimonialOrder, String userId);
}