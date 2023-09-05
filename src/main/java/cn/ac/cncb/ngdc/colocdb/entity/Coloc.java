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
@Table(name = "coloc")
public class Coloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String trait;

    @Column
    private String trait_description;

    @Column
    private String trait_short;

    @Column
    private String qtl;

    @Column
    private String molecule;

    @Column
    private String source;

    @Column
    private String tissue;

    @Column
    private String probe;

    @Column
    private String gene_id;

    @Column
    private Double chr;

    @Column
    private Double nsnps;

    @Column
    private Double pp_h0_abf;

    @Column
    private Double pp_h1_abf;

    @Column
    private Double pp_h2_abf;

    @Column
    private Double pp_h3_abf;

    @Column
    private Double pp_h4_abf;

    @Column
    private Double pp4_pp3;

    @Column
    private String coloc_snp;

    @Column
    private Double snp_pp_h4;

    @Column
    private Double gwas_p;

    @Column
    private Double p1_threshold;

    @Column
    private Double p2_threshold;

    @Column
    private Double qtl_p;

    @Column
    private String top_snp;

    @Column
    private String top_snp_gene;

    @Column
    private String oldtissue;

    @Column
    private String locus;
}
