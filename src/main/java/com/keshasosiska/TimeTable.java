package com.keshasosiska;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

public class TimeTable {
    private final List<DayOfWeek> dayOfWeekList;

    private int currentDayOfWeek;
    private Multimap<DayOfWeek, String> sessions = HashMultimap.create();

    public TimeTable(final List<DayOfWeek> dayOfWeekList) {
        checkArgument(dayOfWeekList != null);
        checkArgument(dayOfWeekList.size() == 7);

        this.dayOfWeekList = dayOfWeekList;
        this.currentDayOfWeek = 0;
    }

    public void fillDayWithSessions(final List<String> sessions) {
        checkArgument(sessions != null);
        checkArgument(currentDayOfWeek < 7);

        DayOfWeek currentDay = dayOfWeekList.get(currentDayOfWeek);
        for (String session : sessions) {
            this.sessions.put(currentDay, session);
        }

        currentDayOfWeek++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (DayOfWeek dayOfWeek : dayOfWeekList) {
            if (sessions.containsKey(dayOfWeek)) {
                builder.append(dayOfWeek).append(":");
            }
            for (String session : sessions.get(dayOfWeek)) {
                builder.append(session).append(" ");
            }
        }

        return builder.toString();
    }
}
