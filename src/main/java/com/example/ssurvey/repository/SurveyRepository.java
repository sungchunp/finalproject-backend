package com.example.ssurvey.repository;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> df3527166da03aceebff902fd79c989299142f51
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
<<<<<<< HEAD
	
	public List<Survey> findAll();

//	public List<Survey> findBySurveyCategory(String category);
	
	
=======

	public Survey findTopByOrderBySurveyNoDesc();
>>>>>>> df3527166da03aceebff902fd79c989299142f51
	
}
