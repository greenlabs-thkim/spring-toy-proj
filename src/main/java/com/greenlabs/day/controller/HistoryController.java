package com.greenlabs.day.controller;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {
    private final EntryService entryService;

    @Autowired
    public HistoryController(EntryService entryService) {
        this.entryService = entryService;
    }


}
