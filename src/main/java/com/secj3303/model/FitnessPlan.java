package com.secj3303.model;

import javax.persistence.*;

@Entity
@Table(name = "fitness_plan")
public class FitnessPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Person trainer; 
    // Assuming Person entity has person_id and a role


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Person getTrainer() { return trainer; }
    public void setTrainer(Person trainer) { this.trainer = trainer; }

    // Optional: toString(), Constructors
}