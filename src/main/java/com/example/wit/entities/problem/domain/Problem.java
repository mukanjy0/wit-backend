package com.example.wit.entities.problem.domain;

import com.example.wit.entities.challenge.domain.quest.Quest;
import com.example.wit.entities.contest.domain.Contest;
import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String statement;
    @Column(nullable = false)
    private Integer memoryLimit;
    @Column(nullable = false)
    private Short timeLimit;
    @Column(length = 255, nullable = false)
    private String sourceUrl;
    @ManyToMany(mappedBy = "problems")
    Set<Contest> contests;
    @ManyToMany
    Set<Tag> tags;
    @OneToMany(mappedBy = "problem")
    Set<Submission> submissions;
    @ManyToMany(mappedBy = "problems")
    Set<Quest> quests;
}
