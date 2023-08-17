package com.jrp.pma.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.DTO.ChartData;
import com.jrp.pma.DTO.TimeChartData;
import com.jrp.pma.entities.Project;


public interface ProjectRepository extends PagingAndSortingRepository<Project,Long>{

	@Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as stageCount "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getprojectStage();

	public Project findByProjectID(long id);
	
	@Query(nativeQuery = true, value = "SELECT name as Project, start_date as StartDate, end_date as endDate "
			+ "FROM project WHERE start_date is NOT NULL")
	public List<TimeChartData> getProjectTimeline();
	
	
}
