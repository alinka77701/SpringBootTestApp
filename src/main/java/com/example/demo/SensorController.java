package com.example.demo;

import com.example.demo.domain.Sensor;
import com.example.demo.domain.Value;
import com.example.demo.repos.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@Controller
public class SensorController {
    @Autowired
    private SensorRepo sensorRepo;


    @GetMapping("/sensors")
    public String main(Model model) {
        Iterable<Sensor> sensors = sensorRepo.findAll();

        model.addAttribute("sensors", sensors);

        return "sensors";
    }


    @PostMapping("/add/sensor")
    public String add( @RequestParam String name,
                       @RequestParam Integer serialNumber,
                       @RequestParam String modelSensor,
                       Map<String, Object> model) {
        Sensor sensor = new Sensor(name, modelSensor,serialNumber );

        sensorRepo.save(sensor);

        Iterable<Sensor> sensors = sensorRepo.findAll();

        model.put("sensors", sensors);

        return "sensors";
    }

    @PostMapping("filter/sensors")
    public String filter(@RequestParam String filterSensor, Map<String, Object> model) {
        Iterable<Sensor> sensors;

        if (filterSensor != null && !filterSensor.isEmpty()) {
            sensors = sensorRepo.findByName(filterSensor);
        } else {
            sensors = sensorRepo.findAll();
        }

        model.put("sensors", sensors);

        return "sensors";
    }

}