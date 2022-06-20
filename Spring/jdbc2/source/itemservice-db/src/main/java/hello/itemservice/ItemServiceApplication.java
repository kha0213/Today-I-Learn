package hello.itemservice;

import hello.itemservice.config.V2Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


//@Import(MemoryConfig.class)
//@Import(JdbcTemplateConfigV1.class)
//@Import(JdbcTemplateConfigV2.class)
//@Import(JdbcTemplateConfigV3.class)
//@Import(MybatisConfig.class)
//@Import(JpaConfig.class)
//@Import(SpringDataJpaConfig.class)
//@Import(QueryDslConfig.class)
@Import(V2Config.class)

@Slf4j
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

//	@Bean
//	@Profile("local")
//	public TestDataInit testDataInit(ItemRepository itemRepository) {
//		return new TestDataInit(itemRepository);
//	}
}
