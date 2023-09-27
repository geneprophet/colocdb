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
@Table(name = "smr")
public class Smr {
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
    private String trait_population;

    @Column
    private String qtl_population;

    @Column
    private String qtl;

    @Column
    private String molecule;

    @Column
    private String qtl_type;

    @Column
    private String tissue;

    @Column
    private String probeid;

    @Column
    private String probechr;

    @Column
    private String gene;

    @Column
    private String probe_bp;

    @Column
    private String topsnp;

    @Column
    private String topsnp_chr;

    @Column
    private String topsnp_bp;

    @Column
    private String a1;

    @Column
    private String a2;

    @Column
    private Double freq;

    @Column
    private Double b_gwas;

    @Column
    private Double se_gwas;

    @Column
    private Double p_gwas;

    @Column
    private Double b_xqtl;

    @Column
    private Double se_xqtl;

    @Column
    private Double p_xqtl;

    @Column
    private Double b_smr;

    @Column
    private Double se_smr;

    @Column
    private Double p_smr;

    @Column
    private Double p_heidi;

    @Column
    private Double nsnp_heidi;

    @Column
    private Double fdr_smr;
}
