package com.example.acceptedassesment;

import com.example.acceptedassesment.entities.MatchGame;
import com.example.acceptedassesment.entities.Sport;
import com.example.acceptedassesment.repositories.MatchGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
public class AcceptedAssesmentApplication implements CommandLineRunner {

	@Autowired
	MatchGameRepository matchGameRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcceptedAssesmentApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	/*	LocalDate ld = LocalDate.of(2021,11,05);
		LocalTime lt = LocalTime.of(06,10);*/
		/*MatchGame mg1 = new MatchGame("Footbal",ld,lt,"pao","ofi", Sport.Soccer);
		MatchGame mg3 = new MatchGame("Baseball",ld,lt,"pao","ofi", Sport.Baseball);
		MatchGame mg2 = new MatchGame("Tennis",ld,lt,"pao","ofi", Sport.Tennis);*/
		/*matchGameRepository.save(mg1);
		matchGameRepository.save(mg3);
		matchGameRepository.save(mg2);*/
		/*Optional<MatchGame> gm4 = matchGameRepository.findById(7);
		System.out.println(gm4.get());*/
	}
}
