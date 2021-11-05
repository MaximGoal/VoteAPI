package com.example.votingsystem.service;

import com.example.votingsystem.testData.UserTestData;
import com.example.votingsystem.testData.VoteTestData;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Vote;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static com.example.votingsystem.testData.VoteTestData.*;

//@SpringBootTest
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
//        "classpath:spring/spring-cache.xml"
})
//@ExtendWith(SpringExtension.class) // @SpringJUnitConfig already has inside @ExtendWith(SpringExtension.class) annotation
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    protected VoteDAO service;

    @Test
    public void getAllUsers() {
        List<User> expected = new ArrayList<>(UserTestData.users);
        List<User> actual = service.getAllUsers();
        Assertions
                .assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    public void getAllVotes() {
        List<Vote> expected = VoteTestData.votes;
        List<Vote> actual = service.getAllVotes();
        Assertions.assertThat(actual).usingElementComparatorIgnoringFields("menu", "dateTime").isEqualTo(expected);
    }

    @Test
    public void getVoteById() {
        Vote actual = service.getVoteById(120005);
        Assertions.assertThat(actual).isNotNull();
        actual.setDateTime(vote06.getDateTime());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("user.votes")
                .isEqualTo(vote06);
    }

    @Test
    public void deleteVoteById() {
        service.deleteVoteById(vote01.getId());

        Assertions.assertThat(service.getVoteById(vote01.getId())).isNull();
    }

    @Test
    public void updateVoteById() {
        Vote actual = service.getVoteById(120001);
        actual.setMenu(vote03.getMenu());
        service.saveVoteById(120001, actual);
        vote02Upd.setDateTime(actual.getDateTime());

        Assertions.assertThat(service.getVoteById(120001))
                .usingRecursiveComparison()
                .ignoringFields("menu.dateTime", "user.votes")
                .isEqualTo(vote02Upd);
    }
}