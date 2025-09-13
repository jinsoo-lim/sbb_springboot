package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    /*
    paging.isEmpty	페이지 존재 여부를 의미한다(게시물이 있으면 false, 없으면 true).
    paging.totalElements	전체 게시물 개수를 의미한다.
    paging.totalPages	전체 페이지 개수를 의미한다.
    paging.size	페이지당 보여 줄 게시물 개수를 의미한다.
    paging.number	현재 페이지 번호를 의미한다.
    paging.hasPrevious	이전 페이지의 존재 여부를 의미한다.
    paging.hasNext	다음 페이지의 존재 여부를 의미한다.
    */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue ="0") int page) {
//        List<Question> questionList = this.questionService.getList();
        Page<Question> paging = this.questionService.getList(page);
//        model.addAttribute("questionList", questionList);
        model.addAttribute("paging", paging);
        return "question_list";
    }


    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        this.questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list"; //질문 저장후 질문목록으로 이동!
    }
}
