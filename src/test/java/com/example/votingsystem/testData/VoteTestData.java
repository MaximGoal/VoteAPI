package com.example.votingsystem.testData;

import com.example.votingsystem.model.Vote;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static com.example.votingsystem.testData.MenuTestData.*;
import static com.example.votingsystem.testData.UserTestData.*;
import static com.example.votingsystem.model.BaseEntity.START_SEQ;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Component
public class VoteTestData {

    public static LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    public final static Vote vote01 = new Vote (START_SEQ + 20000, admin, menu01, dateTime);
    public final static Vote vote02 = new Vote (START_SEQ + 20001, user1, menu01, dateTime.plusSeconds(1));
    public final static Vote vote03 = new Vote (START_SEQ + 20002, user2, menu02, dateTime.plusSeconds(2));
    public final static Vote vote04 = new Vote (START_SEQ + 20003, admin, menu06, dateTime.plusSeconds(3));
    public final static Vote vote05 = new Vote (START_SEQ + 20004, user1, menu05, dateTime.plusSeconds(4));
    public final static Vote vote06 = new Vote (START_SEQ + 20005, user2, menu06, dateTime.plusSeconds(15));
    public final static Vote vote07 = new Vote (START_SEQ + 20006, admin, menu12, dateTime.plusSeconds(16));
    public final static Vote vote08 = new Vote (START_SEQ + 20007, user1, menu11, dateTime.plusSeconds(17));
    public final static Vote vote09 = new Vote (START_SEQ + 20008, user2, menu11, dateTime.plusSeconds(18));
    public final static Vote vote10 = new Vote (START_SEQ + 20009, admin, menu16, dateTime.plusSeconds(170));
    public final static Vote vote11 = new Vote (START_SEQ + 20010, user1, menu16, dateTime.plusSeconds(171));
    public final static Vote vote12 = new Vote (START_SEQ + 20011, user2, menu16, dateTime.plusSeconds(172));

    public final static Vote vote02Upd = new Vote (START_SEQ + 20001, user1, menu02, dateTime.plusSeconds(1));

    public final static List<Vote>  votes = List.of(vote01, vote02, vote03, vote04, vote05, vote06, vote07, vote08, vote09, vote10, vote11, vote12);

    static {
        admin.getVotes().addAll(Arrays.asList(vote01, vote04, vote07, vote10));
        user1.getVotes().addAll(Arrays.asList(vote02, vote05, vote08, vote11));
        user2.getVotes().addAll(Arrays.asList(vote03, vote06, vote09, vote12));
    }
}