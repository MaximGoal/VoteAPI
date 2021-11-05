package com.example.votingsystem;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.repository.JpaDishRepo;
import com.example.votingsystem.repository.JpaMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication(scanBasePackages = "com.example.votingsystem",
//        exclude = {
//        DataSourceAutoConfiguration.class,
//        H2ConsoleAutoConfiguration.class
//        }) // to avoid H2 database setup, because some spring-boot-starter need it : https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
@SpringBootApplication(scanBasePackages = "com.example.votingsystem")
@EntityScan(basePackages = "com.example.votingsystem.model")
@EnableWebMvc
@EnableJpaRepositories ("com.example.votingsystem.repository")
public class VotingSystemApplication implements CommandLineRunner {

    @Autowired
    private JpaMenuRepo menuRepo;

    @Autowired
    private JpaDishRepo dishRepo;

    static ApplicationContext context;

    public static void main(String[] args) {

        context =
                SpringApplication.run(VotingSystemApplication.class, args);

        JpaMenuRepo menuRepo = context.getBean(JpaMenuRepo.class);
        Menu menu1 = menuRepo.getById(100020);

    }

    @Override
    public void run(String... args) throws Exception {
        //        setting dishes to menus
//        JpaMenuRepo menuRepo = context.getBean(JpaMenuRepo.class);
//        JpaDishRepo dishRepo = context.getBean(JpaDishRepo.class);
//
//        Menu menu1 = menuRepo.getById(100020);
//        Set<Dish> dishes1 = menu1.getDishes();
//        Dish dish1 = dishRepo.getById(110000);
//        Set<Menu> menus1 = dish1.getMenus();
//        dishes1.add(dish1);
//        menus1.add(menu1);
//        menu1.addDishesToMenu(new HashSet<>(Collections.singletonList(dish1)));
//        dish1.addMenusToDish(new HashSet<>(Collections.singletonList(menu1)));
//        menuRepo.save(menu1);
//        dishRepo.save(dish1);

//        menuRepo.getById(100020).getDishes().add(dishRepo.getById(110000));
//        menuRepo.getById(100021).getDishes().add(dishRepo.getById(110001));
//        menuRepo.getById(100022).getDishes().add(dishRepo.getById(110002));
//        menuRepo.getById(100023).getDishes().add(dishRepo.getById(110003));
//        menuRepo.getById(100024).getDishes().add(dishRepo.getById(110004));
//        menuRepo.getById(100025).getDishes().add(dishRepo.getById(110005));
//        menuRepo.getById(100026).getDishes().add(dishRepo.getById(110006));
//        menuRepo.getById(100027).getDishes().add(dishRepo.getById(110007));
//        menuRepo.getById(100028).getDishes().add(dishRepo.getById(110008));
//        menuRepo.getById(100029).getDishes().add(dishRepo.getById(110009));
//        menuRepo.getById(100030).getDishes().add(dishRepo.getById(110010));
//        menuRepo.getById(100031).getDishes().add(dishRepo.getById(110011));
//        menuRepo.getById(100032).getDishes().add(dishRepo.getById(110012));
//        menuRepo.getById(100033).getDishes().add(dishRepo.getById(110013));
//        menuRepo.getById(100034).getDishes().add(dishRepo.getById(110014));
//        menuRepo.getById(100035).getDishes().add(dishRepo.getById(110015));
    }
}

//        System.out.println("Voting system was started.");
//          testing JpaVoteRepo.deleteVoteById()
//        JpaVoteRepo jpaVoteRepo = context.getBean(JpaVoteRepo.class);
//        int id = 120001;
//        jpaVoteRepo.deleteVoteById(id);
//        System.out.println("vote #" + id + " is deleted.");
//        System.out.println("Really is deleted? " + (jpaVoteRepo.findVotesById(id) == null));