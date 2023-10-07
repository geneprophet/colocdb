package cn.ac.cncb.ngdc.colocdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "gwas")
public class Gwas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String dataset;

    @Column
    private String datasetid;

    @Column
    private String pmid;

    @Column
    private String trait;

    @Column
    private String short_name;

    @Column
    private String description;

    @Column
    private String population;

    @Column
    private String trait_type;

    @Column
    private String build;

    @Column
    private String threshold;

    @Column
    private Double n_control;

    @Column
    private Double n_case;

    @Column
    private Double sample_size;

    @Column
    private String author;

    @Column
    private String year;

    @Column
    private String journal;

    @Column
    private String title;

    @Column
    private String source;

    @Column
    private String resource;

    @Column
    private String coloc;

    @Column
    private String smr;
}
