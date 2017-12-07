package plmedia.intranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.repository.EmployeeRepo;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;

@SpringBootApplication
public class IntranetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);

		DBread dbr = new DBread();

		for (Group g : dbr.readAllGroups()) {
			System.out.println(g.getGroupName());
		}



  }
}
