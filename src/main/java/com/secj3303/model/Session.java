package com.secj3303.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Person trainer; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private FitnessPlan fitnessPlan;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Person getTrainer() { return trainer; }
    public void setTrainer(Person trainer) { this.trainer = trainer; }
    public FitnessPlan getFitnessPlan() { return fitnessPlan; }
    public void setFitnessPlan(FitnessPlan fitnessPlan) { this.fitnessPlan = fitnessPlan; }
    
}
