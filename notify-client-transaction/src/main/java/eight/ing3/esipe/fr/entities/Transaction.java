package eight.ing3.esipe.fr.entities;


import lombok.*;

import java.sql.Date;
 
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String personne;
    private String ibansender;
    private String intitule;
    private Double montant;
    private String detail;
    private String ibanrecipient;
    private String tpe;
    private Date date;




    @Override
    public String toString() {
        return "Transaction{" +
                ", ibansender='" + ibansender + '\'' +
                ", intitule='" + intitule + '\'' +
                ", montant='" + montant + '\'' +
                ", date=" + date +
                '}';
    }
}
