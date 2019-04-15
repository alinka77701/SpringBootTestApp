package com.example.demo;

import com.example.demo.domain.Building;
import com.example.demo.domain.Sensor;
import com.example.demo.domain.Value;
import com.example.demo.repos.BuildingRepo;
import com.example.demo.repos.SensorRepo;
import com.example.demo.repos.TemperatureRepo;
import com.example.demo.repos.ValueProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class TemperatureController {
    @Autowired
    private TemperatureRepo temperatureRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private SensorRepo sensorRepo;

    @GetMapping("/temperatures")
    public String main(Model model) {
        Iterable<Value> temperatures = temperatureRepo.findAll();
        Iterable<Building> buildings = buildingRepo.findAll();
        Iterable<Sensor> sensors = sensorRepo.findAll();

        model.addAttribute("temperatures", temperatures);
        model.addAttribute("sensors", sensors);
        model.addAttribute("buildings", buildings);

        return "temperatures";
    }

    @PostMapping("/add/temperature")
    public String add( @RequestParam Integer temperature,
                       @RequestParam Building building,
                       @RequestParam Sensor sensor,
                       @RequestParam String dateInString,
                       Map<String, Object> model) {
        Date date = new Date();
        dateInString= dateInString+" "+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Value value = new Value(temperature, building, sensor, date);

        temperatureRepo.save(value);

        return "redirect:/temperatures";
    }

    @PostMapping("filter/temperature")
    public String filter(@RequestParam Integer buildingId,
                         @RequestParam Integer sensorId,
                         @RequestParam (required = false, defaultValue = "") String fromDate,
                         @RequestParam (required = false, defaultValue = "") String toDate,
                         Map<String, Object> model)
    {
        Iterable<Value> temperatures;
        Iterable<Building> buildings = buildingRepo.findAll();
        Iterable<Sensor> sensors = sensorRepo.findAll();

        Date fromDateVal = new Date();
        Date toDateVal = new Date();
        if(!fromDate.isEmpty() && !toDate.isEmpty()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

            fromDate= fromDate+" "+time;
            toDate= toDate+" "+time;

            try {
                fromDateVal = formatter.parse(fromDate);
                toDateVal = formatter.parse(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (sensorId != -1 && buildingId != -1 && fromDate.isEmpty() && toDate.isEmpty()) {
            temperatures = temperatureRepo.findByBuilding_idAndSensor_id(buildingId,sensorId);
        } else if (sensorId != -1 && buildingId != -1 && !fromDate.isEmpty() && !toDate.isEmpty()) {
            temperatures = temperatureRepo.findByBuilding_idAndSensor_idAndTimeAfterAndTimeBefore(buildingId,sensorId,fromDateVal,toDateVal);
        } else if (sensorId == -1 && buildingId != -1 && !fromDate.isEmpty() && !toDate.isEmpty()) {
            temperatures=temperatureRepo.findByBuilding_idAndTimeAfterAndTimeBefore(buildingId,fromDateVal,toDateVal);
        } else if (sensorId != -1 && buildingId == -1 && !fromDate.isEmpty() && !toDate.isEmpty()) {
            temperatures=temperatureRepo.findBySensor_idAndTimeAfterAndTimeBefore(sensorId,fromDateVal,toDateVal);
        } else if (buildingId != -1 && sensorId == -1){
            temperatures = temperatureRepo.findByBuilding_id(buildingId);
        } else if (buildingId == -1 && sensorId != -1){
            temperatures = temperatureRepo.findBySensor_id(sensorId);
        }
        else {
            temperatures = temperatureRepo.findAll();
        }

        model.put("temperatures", temperatures);
        model.put("sensors", sensors);
        model.put("buildings", buildings);

        return "temperatures";
    }

    @PostMapping("/calculate_average")
    public String calculateAvg(Map<String, Object> model)
    {
        List<ValueProjection> temperatures=temperatureRepo.findAverage();
        Iterable<Building> buildings = buildingRepo.findAll();
        Iterable<Sensor> sensors = sensorRepo.findAll();

        model.put("temperatures", temperatures);
        model.put("sensors", sensors);
        model.put("buildings", buildings);

        return "temperatures";
    }
}