
import Lesson_02.Lesson_02.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Domain.User;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

	public static void main(String[] args) throws DAOEXCEPTION {
		Logger log = Logger.getLogger(Main.class);
		PropertyConfigurator.configure("LoggerConfig");
		log.trace("Starting application...");
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("Иван", "Петренко", "petrenko@gmail.com", "123456", "USER"));
		userList.add(new User("Василий", "Иванов", "vas_ivanov@gmail.com", "123456", "USER"));

		DAOUSER userDAO = new DAOUSER();
		userList.forEach(user -> {
			try {
				System.out.println(userDAO.insert(user.getFirstName(), user.getLastName(), user.getEmail(),
						user.getPassword(), user.getAccessLevel()));
			} catch (DAOEXCEPTION e) {
				log.error("Error occured!", e);
				e.printStackTrace();
			}
		});
		
		System.out.println(userDAO.readByID(2));
		System.out.println(userDAO.readByEmail("petrenko@gmail.com"));
		userDAO.updateByID(1, "Джон", "Питерс", "petrenko@gmail.com", "123456", "АDMIN");
		userDAO.updateByEmail("Вася", "Иванов", "vas_ivanov@gmail.com", "123456", "USER");
		userDAO.delete(1);
		userDAO.readAll().forEach(System.out::println);

		DAOMAGAZINE magazineDAO = new DAOMAGAZINE();
		System.out.println(
				magazineDAO.insert("Times", "Times is the.....!",
						LocalDate.parse("2019-04-01"), 6005));
		magazineDAO.readAll().forEach(System.out::println);

		DAOSUBSCRIBE subscribeDAO = new DAOSUBSCRIBE();
		System.out.println(subscribeDAO.insert(2, 1, true, LocalDate.parse("2019-04-26"), 12));
		subscribeDAO.readAll().forEach(System.out::println);
	
	}
}
