package ru.ama0.book.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "chapter")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String title;
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Book book;
}
