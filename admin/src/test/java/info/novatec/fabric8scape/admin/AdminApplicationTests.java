package info.novatec.fabric8scape.admin;

import info.novatec.fabric8scape.admin.repository.DataPoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AdminApplicationTests {

	@MockBean
	DataPoolRepository repository;

	@Test
	void contextLoads() {
	}

}
