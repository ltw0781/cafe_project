package com.cafe.back_end.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.back_end.board.dto.Board;
import com.cafe.back_end.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;




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
    public void list(Model model) throws Exception {
        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
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

        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "boards/read";

    }
    

    
    
    
    
}
