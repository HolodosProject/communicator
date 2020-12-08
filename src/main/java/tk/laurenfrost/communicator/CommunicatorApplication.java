package tk.laurenfrost.communicator;

import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.speech.v1.SpeechClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.laurenfrost.communicator.service.SpeechToFoodService;

import java.io.IOException;

@SpringBootApplication
public class CommunicatorApplication {
	static Logger logger = LoggerFactory.getLogger(CommunicatorApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
		logger.info("Holodos launched successfully");
	}

	@Bean
	public SpeechClient getSpeechClient() throws IOException {
		return SpeechClient.create();
	}

	@Bean
	public LanguageServiceClient getNlpClient() throws IOException {
		return LanguageServiceClient.create();
	}
}
