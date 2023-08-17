package com.jrp.pma.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.DAO.ProjectRepository;
import com.jrp.pma.entities.Project;

// Need to specify the classes that will be used in testing, needs to load spring context for Beans, autowiring, etc.
// SpringBootTest does this for us automatically, given that the naming convention matches
@SpringBootTest
@RunWith(SpringRunner.class)
// the SQL files we would like run before the testing initiates (for sample data)
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql","classpath:data.sql"}),
		   @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})
})
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project project = new Project("New TestProject","COMPELTE","Test description");
		proRepo.save(project);
		
		// Ensuring there are 5 records for projects
//		assertEquals(5, proRepo.findAll().size());
		
	}
}
