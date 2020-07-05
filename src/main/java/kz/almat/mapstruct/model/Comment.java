package kz.almat.mapstruct.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id",
            foreignKey = @ForeignKey(name = "article_id_fk"),
            nullable = false)
    private Article article;

    @Column(name = "text", nullable = false)
    private String text;

}
