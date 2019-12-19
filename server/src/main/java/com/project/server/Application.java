package com.project.server;

import com.project.server.model.*;
import com.project.server.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.qos.logback.core.html.NOPThrowableRenderer;


import java.sql.Date;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner demo(UserRepository users, PermissionRepository permissions,
                                  ProjectRepository projectRepo, ToolRepository toolRepo, ScanRepository scanRepo) {
        return (args) -> {
//            Users

        //TrivyHandler.startTestcmd();
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


//            Projects
            Project project1 = new Project("project1");

//            Tools
            ArrayList<Tool> tools = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                tools.add(new Tool("tool" + i, "info " + i));
                tools.get(i).getProject().add(project1);
                project1.getTools().add(tools.get(i));
            }

//            Scans
            Scan testScan = new Scan();
            testScan.setDate(new Date(0));
            testScan.setStatus("ok");
            testScan.setEmail("admin");
            testScan.setToolName(tools.get(0).getName());
            testScan.setProject(project1);
            project1.getScans().add(testScan);

            Scan scan2 = new Scan();
            scan2.setDate(new Date(0));
            scan2.setStatus("ok");
            scan2.setEmail("admin");
            scan2.setToolName(tools.get(0).getName());
            scan2.setProject(project1);
            project1.getScans().add(scan2);

            for (Tool t : tools) {
                toolRepo.save(t);
            }

            projectRepo.save(project1);
            scanRepo.save(testScan);

            for (User u : testUsers) {
                users.save(u);
            }

            for (User u : teamUsers) {
                users.save(u);
            }

        };
    }
}
