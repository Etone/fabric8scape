package info.novatec.fabric8scape.landscaper;

import info.novatec.fabric8scape.landscaper.repository.DataPoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class LandscaperApplicationTests {

	@MockBean
	DataPoolRepository repository;

	@Test
	void contextLoads() {
	}

}
