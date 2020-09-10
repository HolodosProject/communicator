package tk.laurenfrost.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
		System.out.println("Hello Holodos");
	}

}
