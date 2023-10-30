package lab1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String header = "ID| CARDTYPE | NAME | CARDNUMBER | IDCARD | MSISDN | ADDRESS | DATEOFBIRTH";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws AgeRestrictionException {
        readFile();
    }

    public static void readFile() throws AgeRestrictionException {
        List<userAccount> userAccountList = new ArrayList<>();
        String path = "./src/main/java/lab1/bankAccounts.txt";
        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            inputStream = new FileInputStream(path);
            scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String userInfo = scanner.nextLine();
                userAccount userAccount = lab1.userAccount.convert(dateFormat, header, userInfo);
                if (!Objects.isNull(userAccount)) userAccountList.add(userAccount);

            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found" + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error parsing user's bank information " + e.getMessage());
        } finally {
            assert scanner != null;
            scanner.close();
            try {
                inputStream.close();
            } catch (IOException e) {
                System.err.println("IOE exception " + e.getMessage());
            }
        }
        userAccountList.forEach(System.out::println);
    }
}
