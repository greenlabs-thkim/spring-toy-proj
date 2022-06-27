package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.History;
import com.greenlabs.day.domain.QEntry;
import com.greenlabs.day.domain.QHistory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.stringPath;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class HistoryQueryDslRepository extends QuerydslRepositorySupport {

    public HistoryQueryDslRepository() {
        super(History.class);
    }

    public List<History> findByLastHistory(Long userId) {
        QEntry entry = QEntry.entry;
        QHistory history = QHistory.history;

        Long maxHistory = from(history)
                .where(history.entry.user.id.eq(userId))
                .select(history.id.max())
                .fetchOne();

        Entry targetEntry = from(history)
                .where(history.id.eq(maxHistory))
                .select(history.entry)
                .fetchOne();

        List<History> resultList = from(history)
                .where(history.entry.id.eq(targetEntry.getId()))
                .select(history)
                .fetch();
        return resultList;
//
//        String s = """
//
//    select start_time, end_time
//    from history
//    join (
//        select entry_id
//        from history
//        join (
//            select max(history_id) history_id
//            from history
//            join entry
//            join member
//            where member.id = #
//        )as m on m.history_id = history_id
//    ) as sub_result on history.entry_id = sub_result.entry_id
//""";

    }
}
