
package com.entreprise.boris.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.entreprise.boris.models.User;

public interface UserRepos extends JpaRepository<User, Integer> {

	User findByusername(String username);

}
