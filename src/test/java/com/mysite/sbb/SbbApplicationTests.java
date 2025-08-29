package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("what is sbb?");
        this.questionRepository.save(q1);

        List<Question> qList = this.questionRepository.findBySubjectLike("%sbb");
        Question q = qList.get(0);
        assertEquals("what is sbb", q.getSubject());

    }
}
