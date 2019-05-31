package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM Question WHERE favorite_id IS NULL",
            nativeQuery = true)
    List<Question> listAllUnsolved();

    @Query(value = "SELECT * FROM Question WHERE NOT EXISTS (SELECT * FROM Question_Answers WHERE Question.id=Question_Answers.question_id)",
            nativeQuery = true)
    List<Question> listAllUnanswered();

    @Query(value = "SELECT DISTINCT q.* FROM Answer an, Question_Answers qa, Question q WHERE qa.question_id=q.id AND qa.answers_id=an.id AND an.author_id=:accid",
            nativeQuery = true)
    List<Question> listAllAnsweredByUser(@Param("accid") Long accid);
}
