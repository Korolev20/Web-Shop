package com.exampleqq.myWebProgr.models;

import com.exampleqq.myWebProgr.dto.AssortmentDTO;
import com.exampleqq.myWebProgr.dto.ProductDTO;
import jakarta.persistence.*;


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "PostDtos",
                query = "SELECT e.exbarBody, p.packName," +
                        " p.packQuant, p.packType, pp.packPrice, pp.packBonus, uq.unitName" +
                        " FROM exbarc e " +
                        "JOIN Pack p ON e.packId = p.packId" +
                        " JOIN PackPrc pp ON p.packId = pp.packId" +
                        " JOIN Unit uq ON p.unitid = uq.unit_Id",
                resultSetMapping = "PostDtoMapping"
        ),
        @NamedNativeQuery(
                name = "PostDtos1",
                query = "SELECT e.exbarBody, p.packName, :quantity1 as quantity1, " +
                        "CASE WHEN :isDiscountProvided = 'true' " +
                        "THEN ((pp.packPrice / 100.0) * :quantity1) - ((pp.packBonus / 100.0) * :quantity1) " +
                        "ELSE CAST((pp.packPrice / 100.0 * :quantity1) AS decimal (10, 2)) " +
                        "END as totalPrice, " +
                        "CASE WHEN :isDiscountProvided = 'true' " +
                        "THEN CAST((pp.packBonus / 100.0 * :quantity1) AS decimal(10, 2)) " +
                        "ELSE 0 " +
                        "END as bonusPrice, u.unitName, p.packType " +
                        "FROM Exbarc e " +
                        "JOIN Pack p ON e.packId = p.packId " +
                        "JOIN PackPrc pp ON p.packId = pp.packId " +
                        "JOIN Unit u ON p.unitid = u.unit_id " +
                        "WHERE e.exbarBody = :exbarBody1"
                ,
                resultSetMapping = "PostDtoMapping1"
        )
})
@SqlResultSetMapping(

        name = "PostDtoMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "exbarBody"),
                                @ColumnResult(name = "packName"),
                                @ColumnResult(name = "packQuant"),
                                @ColumnResult(name = "packType"),
                                @ColumnResult(name = "packPrice"),
                                @ColumnResult(name = "packBonus"),
                                @ColumnResult(name = "unitName")
                        },
                        targetClass = AssortmentDTO.class
                )}
)
@SqlResultSetMapping(

        name = "PostDtoMapping1",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "exbarBody"),
                                @ColumnResult(name = "packName"),
                                @ColumnResult(name = "quantity1"),
                                @ColumnResult(name = "totalPrice"),
                                @ColumnResult(name = "bonusPrice"),
                                @ColumnResult(name = "unitName"),
                                @ColumnResult(name = "packType"),
                        },
                        targetClass = ProductDTO.class
                )}
)

@Entity
@Table(name = "exbarc")
public class Exbarc {
    @Id
    @Column(name = "exbarcid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exbarcid;

    @Column(name = "exbarbody")
    private String exbarBody;

    @OneToOne()
    @JoinColumn(name = "packid",referencedColumnName = "packid")
    private Pack pack;

    public Exbarc(String exbarBody, Pack pack) {
        this.exbarBody = exbarBody;
        this.pack = pack;
    }
    public Exbarc() {

    }

    public int getExbarcid() {
        return exbarcid;
    }

    public void setExbarcid(int exbarcid) {
        this.exbarcid = exbarcid;
    }

    public String getExbarBody() {
        return exbarBody;
    }

    public void setExbarBody(String exbarBody) {
        this.exbarBody = exbarBody;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
}
