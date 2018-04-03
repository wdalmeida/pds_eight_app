package service;

import dto.ProductionTransactionDto;
import entity.ProductionTransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import repository.ProductionTransactionRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static java.lang.String.valueOf;

public class MockProductionTransaction {

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() throws IOException {

        System.out.println("START");

        String firstNameFile = "contract-underwriting-learning/resource/Prenoms.csv";
        String lastNameFile = "contract-underwriting-learning/resource/Noms.csv";
        String postalCodeFile = "contract-underwriting-learning/resource/laposte_hexasmal.csv";
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

                System.out.println("MIDDLE");


                ProductionTransactionDto productionTransactionDto = new ProductionTransactionDto();
                    productionTransactionDto.setDate(new Timestamp(System.currentTimeMillis()));
                    productionTransactionDto.setId_client(r.nextInt(100) + 99999999);
                    productionTransactionDto.setId_account(r.nextInt(1000) + 2000000000);
                    productionTransactionDto.setClient_first_name(valueOf(listFirstName.get(r.nextInt(11626) + 1)));
                    productionTransactionDto.setClient_last_name(valueOf(listLastName.get(r.nextInt(399) + 1)));
                    productionTransactionDto.setClient_birthday((java.sql.Date) new Date(yearFinal, monthFinal, dayFinal));
                    productionTransactionDto.setClient_adress(r.nextInt() + " rue " +
                            valueOf(listFirstName.get(r.nextInt(11626) + 1)) + " " +
                            valueOf(listLastName.get(r.nextInt(399) + 1)) + " , " +
                            String.valueOf(listPostalCode.get(r.nextInt(39200) + 1)));
                    productionTransactionDto.setAccount_type("compte épargne");
                    productionTransactionDto.setAccount_product("PEL");
                    int account_balance_before_transaction= r.nextInt(1) + 100000000;
                    productionTransactionDto.setAccount_balance_before_transaction(account_balance_before_transaction);
                    int transaction_amount = r.nextInt(10) + 100000;
                    productionTransactionDto.setTransaction_amount(transaction_amount);
                    productionTransactionDto.setAccount_balance_after_transaction(account_balance_before_transaction
                            + transaction_amount);
                    productionTransactionDto.setTransaction_type("Versement libre");
                    productionTransactionDto.setTransaction_sign("+");
                    productionTransactionDto.setTransaction_label(" ");
                    productionTransactionDto.setId_account_beneficiary(r.nextInt(1000) + 2000000000);

                    //problème non résolu dans set...
                System.out.println("BEFORE FINISH");

                System.out.println(productionTransactionDto);


            }
        }
            catch(Exception e){

            }
    }

    public static int randBetween(int start, int end) {

        return start + (int)Math.round(Math.random() * (end - start));

    }

   /* @Autowired
    private ProductionTransactionRepository productionTransactionRepository;
    public boolean createProductionTransaction(ProductionTransactionDto productionTransactionDto) {

        ProductionTransactionEntity productionTransactionEntity = new ProductionTransactionEntity();



        productionTransactionRepository.save(productionTransactionEntity);
        return true;
    }

    public void setProductionTransactionRepository(ProductionTransactionRepository productionTransactionRepository) {
        this.productionTransactionRepository = productionTransactionRepository;
    }*/

    public static void main(String[] args) throws IOException {
        MockProductionTransaction m = new MockProductionTransaction();
        m.scheduleFixedRateTask();
    }
}
