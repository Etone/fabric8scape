package info.novatec.fabric8scape.registry;

import info.novatec.fabric8scape.registry.repository.DataPoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RegistryApplicationTests {

	@MockBean
	DataPoolRepository repository;

	@Test
	void contextLoads() {
		assert(true);
	}

}
