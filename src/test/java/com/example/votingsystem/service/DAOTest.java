package com.example.votingsystem.service;

import com.example.votingsystem.testData.UserTestData;
import com.example.votingsystem.testData.VoteTestData;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Vote;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static com.example.votingsystem.testData.VoteTestData.*;

@SpringBootTest
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
//        "classpath:spring/spring-cache.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
// also go to string 19 of "spring-db.xml" to disable "init.sql" ??
//@ExtendWith(SpringExtension.class) // @SpringJUnitConfig already has inside @ExtendWith(SpringExtension.class) annotation
public class DAOTest {

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
        Vote actual = service.getVoteById(1000005);
        Assertions.assertThat(actual).isNotNull();
        actual.setDate(vote06.getDate());
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
        Vote actual = service.getVoteById(1000002);
        actual.setMenu(vote03.getMenu());
        service.saveVoteById(1000002, actual);
        vote02Upd.setDate(actual.getDate());

        Assertions.assertThat(service.getVoteById(1000002))
                .usingRecursiveComparison()
                .ignoringFields("menu.dateTime", "user.votes")
                .isEqualTo(vote02Upd);
    }
}