package com.example.wit.problem.domain;

import com.example.wit.challenge.domain.quest.Quest;
import com.example.wit.contest.domain.Contest;
import com.example.wit.submission.domain.Submission;
import com.example.wit.tag.domain.Tag;
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
    private Integer memoryLimit;
    private Short timeLimit;
    @Column(length = 255)
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
