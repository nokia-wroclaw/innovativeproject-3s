package com.project.server;

import com.project.server.model.Permission;
import com.project.server.model.Team;
import com.project.server.model.User;
import com.project.server.repository.PermissionRepository;
import com.project.server.repository.TeamRepository;
import com.project.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner demo(UserRepository users, PermissionRepository permissions, TeamRepository teamsRepo) {
        return (args) -> {
//            Users
            ArrayList<User> testUsers = new ArrayList<>();
            testUsers.add(new User("admin", "admin"));
            testUsers.add(new User("user", "user"));

            ArrayList<User> teamUsers = new ArrayList<>();
            for (int i = 0; i < 5; ++i) {
                teamUsers.add(new User("user" + i, "user" + i));
            }

//            Permissions
            Permission adminPermission = new Permission("ADMIN");
            Permission userPermission = new Permission("USER");

            testUsers.get(0).getPermissions().add(adminPermission);
            adminPermission.getUser().add(testUsers.get(0));
            for (User u : testUsers) {
                u.getPermissions().add(userPermission);
                userPermission.getUser().add(u);
            }

            for (User u : teamUsers) {
                u.getPermissions().add(userPermission);
                userPermission.getUser().add(u);
            }

            permissions.save(adminPermission);
            permissions.save(userPermission);

//            Teams
            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < 2; ++i) {
                teams.add(new Team("team" + i));
            }

            for (int i = 0; i < 5; i += 2) {
                teams.get(0).getUser().add(teamUsers.get(i));
                teamUsers.get(i).getTeams().add(teams.get(0));
            }

            for (int i = 1; i < 4; ++i) {
                teams.get(1).getUser().add(teamUsers.get(i));
                teamUsers.get(i).getTeams().add(teams.get(1));
            }

            for (Team t : teams) {
                teamsRepo.save(t);
            }

            for (User u : testUsers) {
                users.save(u);
            }

            for (User u : teamUsers) {
                users.save(u);
            }

//            Save users to db

//            log.info("User found with findAll():");
//            log.info("-------------------------------");
//            for (User user : repository.findAll()) {
//                log.info(user.toString());
//            }
//            log.info("");
        };
    }
}
