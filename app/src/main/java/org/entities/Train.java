package org.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {

    private String train_id;

    private String train_name;

    private String train_no;

    private List<List<Integer>>train_seats;

    private Map<String, String>station_time;

    private List<String>train_stations;

    private String train_info;

    public String getTrain_info(){
        return  String.format("Train ID: %s Train no: %s ",train_id,train_no);
    }

    public Train() {
    }

    public Train(String train_id, String train_name, String train_no, List<List<Integer>> train_seats, Map<String, String> station_time, List<String> train_stations) {
        this.train_id = train_id;
        this.train_name = train_name;
        this.train_no = train_no;
        this.train_seats = train_seats;
        this.station_time = station_time;
        this.train_stations = train_stations;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public List<List<Integer>> getTrain_seats() {
        return train_seats;
    }

    public void setTrain_seats(List<List<Integer>> train_seats) {
        this.train_seats = train_seats;
    }

    public Map<String, String> getStation_time() {
        return station_time;
    }

    public void setStation_time(Map<String, String> station_time) {
        this.station_time = station_time;
    }

    public List<String> getTrain_stations() {
        return train_stations;
    }

    public void setTrain_stations(List<String> train_stations) {
        this.train_stations = train_stations;
    }
}
