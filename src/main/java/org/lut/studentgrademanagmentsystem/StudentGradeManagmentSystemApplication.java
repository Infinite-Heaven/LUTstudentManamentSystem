package org.lut.studentgrademanagmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.lut.studentgrademanagmentsystem",
        "Controller",
        "Service",
        "Repository",
        "Entity"
})
@EntityScan(basePackages = {"Entity"})
@EnableJpaRepositories(basePackages = {"Repository"})
public class StudentGradeManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentGradeManagmentSystemApplication.class, args);
    }

}
