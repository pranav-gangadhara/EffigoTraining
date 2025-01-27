package com.example.one_one.repository;

import com.example.one_one.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // You can add custom query methods if necessary, for example:
    // Optional<Profile> findByBio(String bio);
}
