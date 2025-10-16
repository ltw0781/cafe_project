package com.cafe.back_end.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cafe.back_end.board.dto.Board;
import com.cafe.back_end.common.Page;


public interface BoardService {

    // 게시글 목록
    public List<Board> list(@Param("page") Page page) throws Exception;

    // 게시글 조회
    public Board select(int no) throws Exception;

    // 게시글 등록
    public int insert(Board board) throws Exception;

    // 게시글 수정
    public int update(Board board) throws Exception;

    // 게시글 삭제
    public int delete(int no) throws Exception;

    // 조회수 증가
    public int updateViewCount(int no) throws Exception;

    
}
