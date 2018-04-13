package service;

import entity.ProductionTransactionEntity;
import enumeration.AccountProduct;
import enumeration.AccountType;
import enumeration.TransactionTypeCredit;
import enumeration.TransactionTypeSavingPlus;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repository.ProductionTransactionRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;
import static org.hibernate.event.spi.EventType.values;

@Service
public class MockProductionTransaction {

    @Autowired
    private ProductionTransactionRepository productionTransactionRepository;



    ArrayList<String> sign = new ArrayList<String>();


    //@Scheduled(fixedRate = 100)
    public void scheduleFixedRateTask() throws IOException {

        String firstNameFile = "contract-underwriting-learning/resources/Prenoms.csv";
        String lastNameFile = "contract-underwriting-learning/resources/Noms.csv";
        String postalCodeFile = "contract-underwriting-learning/resources/laposte_hexasmal.csv";
        //first name
        BufferedReader brFirstName = null;
        String lineFirstName = null;

        //last name
        BufferedReader brLastName = null;
        String lineLastName = null;

        //postal code
        BufferedReader brPostalCode = null;
        String linePostalCode = null;

        brFirstName = new BufferedReader(new FileReader(firstNameFile));
        brLastName = new BufferedReader(new FileReader(lastNameFile));
        brPostalCode = new BufferedReader(new FileReader(postalCodeFile));

        ArrayList listFirstName = new ArrayList();
        ArrayList listLastName = new ArrayList();
        ArrayList listPostalCode = new ArrayList();
        Scanner scannerFirstName = null;
        Scanner scannerLastName = null;
        Scanner scannerPostalCode = null;
        int index = 0;

        try {
            while ((lineLastName = brLastName.readLine()) != null) {
                scannerLastName = new Scanner(lineLastName);
                scannerLastName.useDelimiter(";");
                while (scannerLastName.hasNext()) {
                    String dataLastName = scannerLastName.next().toUpperCase();
                    listLastName.add(dataLastName);
                }
                index++;
            }
            index = 0;
            while ((lineFirstName = brFirstName.readLine()) != null) {
                scannerFirstName = new Scanner(lineFirstName);
                scannerFirstName.useDelimiter(";");
                while (scannerFirstName.hasNext()) {
                    String dataFirstName = scannerFirstName.next().toUpperCase();
                    listFirstName.add(dataFirstName);
                }
                index++;
            }
            index = 0;
            while ((linePostalCode = brPostalCode.readLine()) != null) {
                scannerPostalCode = new Scanner(linePostalCode);
                scannerPostalCode.useDelimiter(";");
                while (scannerPostalCode.hasNext()) {
                    String dataPostalCode = scannerPostalCode.next().toUpperCase();
                    listPostalCode.add(dataPostalCode);
                }
                index++;

                Random r = new Random();

                GregorianCalendar gc = new GregorianCalendar();
                int birthYear = randBetween(1920, 1960);
                gc.set(Calendar.YEAR, birthYear);
                int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
                gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
                int yearFinal = (gc.get(Calendar.YEAR));
                int monthFinal = (gc.get(Calendar.MONTH) + 1);
                int dayFinal = gc.get(Calendar.DAY_OF_MONTH);

                ProductionTransactionEntity productionTransactionEntity = new ProductionTransactionEntity();
                productionTransactionEntity.setDate(new java.sql.Date(System.currentTimeMillis()));
                //System.out.println(productionTransactionDto.getDate());
                productionTransactionEntity.setId_client(r.nextInt(99999) + 100);
                //System.out.println(productionTransactionDto.getId_client());
                productionTransactionEntity.setId_account(r.nextInt(20000) + 1000);
                //System.out.println(productionTransactionDto.getId_account());
                productionTransactionEntity.setClient_first_name(valueOf(listFirstName.get(r.nextInt(11626) + 1)));
                //System.out.println(productionTransactionDto.getClient_first_name());
                productionTransactionEntity.setClient_last_name(valueOf(listLastName.get(r.nextInt(399) + 1)));
                //System.out.println(productionTransactionDto.getClient_last_name());
                productionTransactionEntity.setClient_birthday(new java.sql.Date(yearFinal, monthFinal, dayFinal));
                //System.out.println(productionTransactionDto.getClient_birthday());
                productionTransactionEntity.setClient_adress(r.nextInt(1) + 500 + " rue " +
                        valueOf(listFirstName.get(r.nextInt(11626) + 1)) + " " +
                        valueOf(listLastName.get(r.nextInt(399) + 1)) + " , " +
                        r.nextInt(999) + 100);
                //System.out.println(productionTransactionDto.getClient_adress());
                String accountTypeString = String.valueOf(AccountType.values()[new Random().nextInt(AccountType.values().length)]);
                productionTransactionEntity.setAccount_type(accountTypeString);
                //name = ((city.getName() == null) ? "N/A" : city.getName());
                String accountProductString = ((accountTypeString == "COMPTE_COURANT") ? "TRANSACTION" : String.valueOf(AccountProduct.values()[new Random().nextInt(AccountProduct.values().length)]));
                //String accountTypeString = String.valueOf(AccountType.values()[new Random().nextInt(AccountType.values().length)]);
                //String accountTypeString = String.valueOf(AccountType.values()[new Random().nextInt(AccountType.values().length)]);
                productionTransactionEntity.setAccount_product(accountProductString);
                int account_balance_before_transaction = r.nextInt(10000) + 1;
                productionTransactionEntity.setAccount_balance_before_transaction(account_balance_before_transaction);
                int transaction_amount = r.nextInt(100) + 10;
                productionTransactionEntity.setTransaction_amount(transaction_amount);
                productionTransactionEntity.setAccount_balance_after_transaction(account_balance_before_transaction
                        + transaction_amount);
                sign.add("+");
                sign.add("-");
                String signString = sign.get(r.nextInt(1) + 0);
                productionTransactionEntity.setTransaction_sign(signString);
                String transactionTypeString = ((accountTypeString == "COMPTE_EPARGNE" && signString == "+") ? String.valueOf(TransactionTypeSavingPlus.values()[new Random().nextInt(TransactionTypeSavingPlus.values().length)]) : String.valueOf(TransactionTypeCredit.values()[new Random().nextInt(TransactionTypeCredit.values().length)]));
                productionTransactionEntity.setTransaction_type(transactionTypeString);
                productionTransactionEntity.setTransaction_label(" ");
                productionTransactionEntity.setId_account_beneficiary(r.nextInt(20000) + 1000);

                System.out.println(productionTransactionEntity);

                //ProductionTransactionEntity productionTransactionEntity = new ProductionTransactionEntity();
                System.out.println("Not saved" + productionTransactionRepository);
                productionTransactionRepository.save(productionTransactionEntity);
                System.out.println("Saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int randBetween(int start, int end) {

        return start + (int) Math.round(Math.random() * (end - start));

    }

    /*public static void main(String[] args) throws IOException {
        MockProductionTransaction m = new MockProductionTransaction();
        m.scheduleFixedRateTask();
    }*/
}
