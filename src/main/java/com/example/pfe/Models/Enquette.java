package com.example.pfe.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Data
@Entity
public class Enquette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titre;
    @NotBlank
    private String description ;


    @ManyToOne(cascade = CascadeType.MERGE
            ,targetEntity = User.class,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user" , referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JsonIgnore
    @OneToMany(
            mappedBy = "enquette", // we map the connection field in Subject entity
            orphanRemoval = true
    )
    private List<Question> questions  ;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enquette_themes",
            joinColumns = @JoinColumn(name = "enquette_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private Set<Theme> themes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	public Set<Theme> getThemes() {
		return themes;
	}


	public void setThemes(Set<Theme> themes) {
		this.themes = themes;
	}


	


}
