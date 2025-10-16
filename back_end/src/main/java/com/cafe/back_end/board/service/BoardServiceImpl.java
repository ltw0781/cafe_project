package com.cafe.back_end.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.back_end.board.dto.Board;
import com.cafe.back_end.board.mapper.BoardMapper;
import com.cafe.back_end.common.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> list(Page page) throws Exception {

        int total = boardMapper.count(page);
        page.setTotal(total);

        List<Board> boardList = boardMapper.list(page);

        return boardList;

    }

    @Override
    public Board select(int no) throws Exception {

        Board board = boardMapper.select(no);
        return board;

    }

    @Override
    public int insert(Board board) throws Exception {

        int result = boardMapper.insert(board);
        return result;

    }

    @Override
    public int update(Board board) throws Exception {

        int result = boardMapper.update(board);
        return result;

    }

    @Override
    public int delete(int no) throws Exception {

        int result = boardMapper.delete(no);
        return result;

    }

    @Override
    public int updateViewCount(int no) throws Exception {
        return boardMapper.updateViewCount(no);
    }

}
