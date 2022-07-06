package com.example.test1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bs;

    @GetMapping("/board/save-form")
    public String saveForm(){
        return "boardPages/save";
    }

    @PostMapping("board/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        bs.save(boardDTO);
        return "boardPages/save";
    }
}
