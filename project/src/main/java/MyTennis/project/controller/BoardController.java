package MyTennis.project.controller;

import MyTennis.project.domain.Board;
import MyTennis.project.domain.Member;
import MyTennis.project.dto.BoardAddForm;
import MyTennis.project.dto.BoardList;
import MyTennis.project.dto.BoardUpdateForm;
import MyTennis.project.service.BoardService;
import MyTennis.project.web.SessionConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller @Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/Add")
    public String BoardAdd(Model model){
        model.addAttribute("Addform", new BoardAddForm());

        return "/board/AddForm";
    }

    @PostMapping("/SaveForm")
    public String BoardSave(@Validated @ModelAttribute BoardAddForm form,
                            BindingResult bindingResult, HttpServletRequest request){
        Member member = (Member) request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER);

        // 검증
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "/board/AddForm";
        }

        Long boardId = boardService.BoardAdd(form, member);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String BoardList(Model model){
        List<Board> list = boardService.getBoardList();
        model.addAttribute("list", list);
        return "/board/list";
    }

    @GetMapping("/detail/{boardId}")
    public String detail(@PathVariable("boardId") Long id, Model model){
        // @PathVariable은 요청에 오는  id값을 받아 getPost로 전달
        // getPost는 각 게시글의 정보를 가져오는 기능! service에서 구현 예정
            Board board = boardService.findOne(id);
            model.addAttribute("board",board);

        return "/board/detail";
    }

    @GetMapping("/update/{id}")
    public String BoardUpdate(@PathVariable("id")Long id, Model model ){
        Board board = boardService.findOne(id);
//        // 수정 후 게시글과 제목 담을 준비완료!
//        model.addAttribute("update", new BoardUpdateForm());

        BoardUpdateForm form  = new BoardUpdateForm();
        form.setTitle(board.getTitle());
        form.setContent(board.getContent());

        model.addAttribute("update",form);

        return "/board/updateForm";
    }

    @PostMapping("/update")
    public String BoardUpdate2(@Validated @ModelAttribute BoardUpdateForm form,
                               BindingResult bindingResult, HttpServletRequest request){

        Member member = (Member)request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER);

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "/board/updateForm";
        }

        boardService.BoardUpdate(form, member);
       
        return "redirect:/";
    }

//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable("no")Long id){
//        boardService.BoardDelete(id);
//
//        return "redirect:/";
//    }

}
