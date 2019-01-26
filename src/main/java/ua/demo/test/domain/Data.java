package ua.demo.test.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@lombok.Data
@Entity
public class Data {

    @JsonInclude(content= JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idObject")
    private Long idObject;

    private String dateModified;

    private String title;

    private String documentType;

    private String hash;

    private String datePublished;

    private String documentOf;

    private String format;

    private String id;

    private String url;

}
