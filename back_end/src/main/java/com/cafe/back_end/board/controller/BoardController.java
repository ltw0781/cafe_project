package com.cafe.back_end.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe.back_end.board.dto.Board;
import com.cafe.back_end.board.service.BoardService;
import com.cafe.back_end.common.Page;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록
     * @param model
     * @throws Exception
     */
    @GetMapping("/list")
    public void list(Model model, Page page) throws Exception {

        log.info("page : " + page);

        List<Board> boardList = boardService.list(page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);
        
    }
    
    /**
     * 게시판 상세
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model) throws Exception {

        boardService.updateViewCount(no); // 조회수 증가

        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "boards/read";

    }
    
    /**
     * 게시글 등록
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        
        return "/boards/insert";

    }
    
    /**
     * 게시글 등록 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insert(Board board) throws Exception {

        int result = boardService.insert(board);

        if(result > 0) {
            return "redirect:/boards/list";
        }
        return "redirect:/boards/insert";
    }
    
    /**
     * 게시글 수정
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {

        Board board = boardService.select(no);

        model.addAttribute("board", board);

        return "/boards/update";
    }

    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception{
        int result = boardService.update(board);
        if( result > 0) {
            return "redirect:/boards/list";
        }
        int no = board.getNo();
        return "redirect:/boards/update?no=" + no + "&error";
    }

    /**
     * 게시글 삭제
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no, Model model) throws Exception{
        
        int result = boardService.delete(no);


        if(result > 0) {
            return "redirect:/boards/list";
        }

        return "redirect/boards/update";
    }
    
    
}
