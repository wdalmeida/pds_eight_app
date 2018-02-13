package entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="transferdetails")
public class TransferDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtransferdetails;

    @JoinColumn(name = "idtransfer")
    @OneToOne
    private TransferEntity transferEntity;

    @Column(name = "step")
    private int step;

    @Column(name = "oldbalanceorg")
    private double oldBalanceOrg;

    @Column(name = "newbalanceorig")
    private double newbalanceorig;

    @Column(name = "isfraud")
    private int isFraud;

}