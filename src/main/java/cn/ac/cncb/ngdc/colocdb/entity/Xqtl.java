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
@Table(name = "xqtl")
public class Xqtl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String dataset;

    @Column
    private String pmid;

    @Column
    private String year;

    @Column
    private String author;

    @Column
    private String description;

    @Column
    private String tissue_celltype;

    @Column
    private Double sample_size;

    @Column
    private String qtl_type;

    @Column
    private String population;

    @Column
    private String source;

    @Column
    private Double qtl_n;

    @Column
    private Double gene_n;
}
