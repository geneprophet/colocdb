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
@Table(name = "gwas2gwas")
public class Gwas2gwas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String gwas1;

    @Column
    private String gwas2;

    @Column
    private String locus;

    @Column
    private String cytoband;

    @Column
    private String coloc_snp;

    @Column
    private String gene;

    @Column
    private Double pp_h4_abf;

    @Column
    private Double pp4_pp3;

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
    private Double snp_pp_h4;

    @Column
    private Double p1_threshold;

    @Column
    private Double p2_threshold;

    @Column
    private String chr;

    @Column
    private String bp;

//    @Column
//    private String a1;
//
//    @Column
//    private String a2;
//
//    @Column
//    private String probe;

}
