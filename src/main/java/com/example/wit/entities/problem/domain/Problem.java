package com.example.wit.entities.problem.domain;

import com.example.wit.entities.challenge.domain.quest.Quest;
import com.example.wit.entities.contest.domain.Contest;
import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.tag.domain.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String statement;
    @Column(nullable = false)
    @Value("256")
    private Integer memoryLimit;
    @Column(nullable = false)
    @Value("1")
    private Short timeLimit;
    @Column(nullable = false)
    private String sourceUrl;
    @ManyToMany
    @JoinTable(name = "problem_tag",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> tags;
    @ManyToMany(mappedBy = "problems")
    Set<Contest> contests;
    @OneToMany(mappedBy = "problem")
    Set<Submission> submissions;
    @ManyToMany(mappedBy = "problems")
    Set<Quest> quests;
}
