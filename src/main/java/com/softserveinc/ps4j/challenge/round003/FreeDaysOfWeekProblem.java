package com.softserveinc.ps4j.challenge.round003;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Given a schedule consisting of entries represented as {@link TimeWindow} objects,
 * find all the days of week that are free (as in, no time is taken on these days by any of the entries).
 *
 * <p>P.S. Try solving this using the Stream API!
 *
 * <p>Input restrictions:
 * <li>schedule != null
 */
class FreeDaysOfWeekProblem {

    Set<DayOfWeek> solve(List<TimeWindow> schedule) {

        return schedule.stream()
                .flatMap(window -> window.start().toLocalDate()
                        .datesUntil(window.start().plus(window.duration()).toLocalDate().plusDays(1))
                        .limit(7))
                .map(LocalDate::getDayOfWeek)
                .collect(collectingAndThen(toCollection(() -> EnumSet.noneOf(DayOfWeek.class)), EnumSet::complementOf));
    }

}

/**
 * A time window with the start time and duration
 */
record TimeWindow(ZonedDateTime start, Duration duration) {
}