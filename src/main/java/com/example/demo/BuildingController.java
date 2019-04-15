package com.example.demo;

import com.example.demo.domain.Building;
import com.example.demo.domain.Sensor;
import com.example.demo.repos.BuildingRepo;
import com.example.demo.repos.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class BuildingController {
    @Autowired
    private BuildingRepo buildingRepo;

    @GetMapping("/")
    public String greeting() {
        return "home";
    }

    @GetMapping("/buildings")
    public String main(Model model) {
        Iterable<Building> buildings = buildingRepo.findAll();

        model.addAttribute("buildings", buildings);

        return "buildings";
    }


    @PostMapping("/add/building")
    public String add( @RequestParam String name,
                       @RequestParam String address,
                       Map<String, Object> model) {
        Building building = new Building(name, address);

        buildingRepo.save(building);

        Iterable<Building> buildings = buildingRepo.findAll();

        model.put("buildings", buildings);

        return "buildings";
    }

    @PostMapping("filter/buildings")
    public String filter(@RequestParam String filterBuilding, Map<String, Object> model) {
        Iterable<Building> buildings;

        if (filterBuilding != null && !filterBuilding.isEmpty()) {
            buildings = buildingRepo.findByName(filterBuilding);
        } else {
            buildings = buildingRepo.findAll();
        }

        model.put("buildings", buildings);

        return "buildings";
    }

}