package com.example.votingsystem.util;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtil {
//    private VoteService service;
//
//    protected LocalDateTime dateTimeNow ;
//
//    @Autowired
//    public DateTimeUtil(VoteService service) {
//        this.service = service;
//    }
//
//    @Autowired
//    public void setDateTimeNow() {
//        dateTimeNow = service.getVoteById(120000).getDateTime().truncatedTo(ChronoUnit.SECONDS);
//    }
//
//    public LocalDateTime getDateTimeNow() {
//        return dateTimeNow;
//    }
    public static final String patternDateTime = "yyyy-MM-dd HH:mm:ss";
    public static final String patternDate = "yyyy-MM-dd";

    public static DateTimeFormatter formatterYMDHM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//    LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
//    String formattedDateTime = dateTime.format(formatterYMDHM); // "1986-04-08 12:30"
}
