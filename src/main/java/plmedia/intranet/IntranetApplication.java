package plmedia.intranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.repository.EmployeeRepo;
import plmedia.intranet.model.Employee;

@SpringBootApplication
public class IntranetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);

		EmployeeRepo empRepo = new EmployeeRepo();

		for (Employee e :
				empRepo.readAllEmployeesByGroup(1)) {
			System.out.println(e);
		}


  }
}
