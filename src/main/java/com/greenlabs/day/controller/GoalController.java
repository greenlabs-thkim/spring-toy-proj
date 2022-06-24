package com.greenlabs.day.controller;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.service.GoalService;
import com.greenlabs.day.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GoalController {
    private final GoalService goalService;
    private final EntryService entryService;

    @Autowired
    public GoalController(GoalService goalService, EntryService entryService) {
        this.goalService = goalService;
        this.entryService = entryService;
    }

    @GetMapping("goal/regist")
    public String registForm() {
        return "goal/registGoalForm";
    }

    @PostMapping("goal/regist")
    public String regist(Goal goal) {
        goalService.regist(goal);
        return "redirect:/";
    }

    @GetMapping("goal/list")
    public  String list(Model model) {
        List<Goal> goalList = goalService.search(new Goal());
        model.addAttribute("goals", goalList);
        return "goal/listGoal";
    }

    @PostMapping("goal/entry")
    public String entry(Goal goal) {
        entryService.entry(goal.getId(), 888L);
        return "redirect:/";
    }

    @GetMapping("goal/list/user")
    public String listGoalByUser(Model model) {

//        List<Goal> goalList = goalService.search(new Goal());
//        model.addAttribute("goals", goalList);
        return "redirect:/";
    }
}
