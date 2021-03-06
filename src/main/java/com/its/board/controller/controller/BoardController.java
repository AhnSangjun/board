package com.its.board.controller.controller;

import com.its.board.controller.dto.BoardDTO;
import com.its.board.controller.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequestMapping
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 글쓰기 화면 요청
//   @GetMaping("/board/save") // RequestMapping 미적용
    @GetMapping("/board/save") // RequestMapping 적용
    public String saveForm() {
        return "board/save"; // => views/boarc/save.jsp
    }

    // 글쓰기 처리
    //  @PostMapping("/board/save") // RequestMapping 미적용

    @PostMapping("/save") // Request 적용
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boolean result = boardService.save(boardDTO);
        if (result) {
            return "redirect:/findAll"; // => /board/findAll 주소 요청
        } else {
            return "board/save-fail";
        }
    }

    // 목록 출력

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/list";
    }
}
