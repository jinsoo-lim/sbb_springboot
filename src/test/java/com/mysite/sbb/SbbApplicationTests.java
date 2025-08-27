package com.mysite.sbb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp(){
        questionRepository.deleteAll();
    }

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("what is sbb?");
        q1.setContent("I want to know about sbb");
        q1.setCreatDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("Question of springboot model");
        q2.setContent("Is it id that makeing auto?");
        q2.setCreatDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("what is sbb?", q.getSubject());


    }

}