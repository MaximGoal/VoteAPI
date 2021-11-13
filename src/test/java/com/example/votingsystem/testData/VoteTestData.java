package com.example.votingsystem.testData;

import com.example.votingsystem.model.Vote;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static com.example.votingsystem.testData.MenuTestData.*;
import static com.example.votingsystem.testData.UserTestData.*;
import static com.example.votingsystem.model.BaseEntity.START_SEQ;
import static com.example.votingsystem.model.BaseEntity.START_VOTE_SEQ;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Component
public class VoteTestData {

//    public static LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    public static LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    public static LocalDate date = LocalDate.now();

    public final static Vote vote01 = new Vote (START_VOTE_SEQ + 1, admin, menu01, date);
    public final static Vote vote02 = new Vote (START_VOTE_SEQ + 2, user1, menu01, date);
    public final static Vote vote03 = new Vote (START_VOTE_SEQ + 3, user2, menu02, date);
    public final static Vote vote04 = new Vote (START_VOTE_SEQ + 4, admin, menu06, date);
    public final static Vote vote05 = new Vote (START_VOTE_SEQ + 5, user1, menu05, date);
    public final static Vote vote06 = new Vote (START_VOTE_SEQ + 6, user2, menu06, date);
    public final static Vote vote07 = new Vote (START_VOTE_SEQ + 7, admin, menu12, date);
    public final static Vote vote08 = new Vote (START_VOTE_SEQ + 8, user1, menu11, date);
    public final static Vote vote09 = new Vote (START_VOTE_SEQ + 9, user2, menu11, date);
    public final static Vote vote10 = new Vote (START_VOTE_SEQ + 10, admin, menu16, date);
    public final static Vote vote11 = new Vote (START_VOTE_SEQ + 11, user1, menu16, date);
    public final static Vote vote12 = new Vote (START_VOTE_SEQ + 12, user2, menu16, date);

    public final static Vote vote02Upd = new Vote (START_VOTE_SEQ + 2, user1, menu02, date.plusDays(1));

    public final static List<Vote>  votes = List.of(vote01, vote02, vote03, vote04, vote05, vote06, vote07, vote08, vote09, vote10, vote11, vote12);

    static {
        admin.getVotes().addAll(Arrays.asList(vote01, vote04, vote07, vote10));
        user1.getVotes().addAll(Arrays.asList(vote02, vote05, vote08, vote11));
        user2.getVotes().addAll(Arrays.asList(vote03, vote06, vote09, vote12));
    }
}