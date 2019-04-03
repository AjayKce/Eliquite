package student.kce.Eliquite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan("student.kce.erp.attachment")
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("student.kce.erp")
public class EliquiteApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EliquiteApplication.class, args);
	}

}