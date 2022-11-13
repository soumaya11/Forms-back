package com.example.pfe.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    private String question;

    @NotBlank
    private String type ;

    @NotBlank
    private boolean status ;


    @ManyToOne
    @JoinColumn(
            name = "enquette_id",
            nullable = false,
            referencedColumnName = "id", // this `id` is the Course.id
            foreignKey = @ForeignKey(name = "enquette_question_fk")
    )
    private Enquette enquette;


    @OneToMany(mappedBy = "question")
    private Set<Response> responses = new HashSet<>();


    public boolean getStatus() {
        return  this.status;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Enquette getEnquette() {
		return enquette;
	}


	public void setEnquette(Enquette enquette) {
		this.enquette = enquette;
	}


	public Set<Response> getResponses() {
		return responses;
	}


	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	
}
