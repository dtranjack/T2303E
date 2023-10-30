package lab1;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userAccount {
    private long ID;
    private String cardType;
    private String name;
    private long cardNumber;
    private long IDCard;
    private long MSISDN;
    private String address;
    private Date DoB;

    public static userAccount convert(DateFormat dateFormat, String header, String info) throws ParseException, AgeRestrictionException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(info)) return null;
        if (info.trim().equalsIgnoreCase(header)) return null; //remove white spaces
        String[] charsList = info.split("\\|"); // the \\ is to separate | from regular expression

        //Validate CardType "VISA", "JCB", "HYBRID"
        String cardType = charsList[1].trim();
        if (!checkCardType(cardType)) {
            System.err.println("Invalid CARD TYPE "+ cardType +",please make sure the CARD TYPE is valid within the following options \"VISA\", \"JCB\", \"HYBRID\".");
            return null;
        }

        //Validate CardNumber (16 digits)
        String cardNumber = charsList[3].trim();
        if (!checkCardNumber(cardNumber)) {
            System.err.println("Invalid CARD NUMBER "+ cardNumber + ",please make sure the CARD NUMBER is valid and have 16 digits.");
            return null;
        }

        //Validate ID Card (12 digits)
        String idCard = charsList[4].trim();
        if (!checkIDCard(idCard)) {
            System.err.println("Invalid ID CARD " + idCard +",please make sure the ID CARD is valid and have 12 digits.");
            return null;
        }

        //Validate MSISDN (7 digits)
        String msisdn = charsList[5].trim();
        if (!checkMSISDN(msisdn)) {
            System.err.println("Invalid MSISDN " + msisdn +", please make sure the MSISDN is valid and have 7 digits");
            return null;
        }

        //Validate DateOfBirth (Can only make bank account if above 15 years old)
        String dobString = charsList[7].trim();
        Date dob = dateFormat.parse(dobString);
        Calendar BirthYear = Calendar.getInstance();
        BirthYear.setTime(dob);
        Calendar CurrentYear = Calendar.getInstance();
        int age = CurrentYear.get(Calendar.YEAR) - BirthYear.get(Calendar.YEAR);
        if (age < 15) {
            throw new AgeRestrictionException("The customer is not old enough to make a bank account.");
        }

        return userAccount.builder()
                //ID| CARDTYPE | NAME | CARDNUMBER | IDCARD | MSISDN | ADDRESS | DATEOFBIRTH
                .ID(Long.valueOf(charsList[0]))
                .cardType(cardType)
                .name(charsList[2])
                .cardNumber(Long.valueOf(cardNumber))
                .IDCard(Long.valueOf(idCard))
                .MSISDN(Long.valueOf(msisdn))
                .address(charsList[6])
                .DoB(dob)
                .build();
    }

    public static boolean checkMSISDN(String msisdn) {
        return msisdn != null && msisdn.length() == 7 && msisdn.matches("[0-9]+");
    }

    public static boolean checkCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.length() == 16 && cardNumber.matches("[0-9]+");
    }

    public static boolean checkIDCard(String idCard) {
        return idCard != null && idCard.length() == 12 && idCard.matches("[0-9]+");
    }

    public static boolean checkCardType(String cardType){
        List<String> validCardType = Arrays.asList("VISA", "JCB", "HYBRID");
        return validCardType.contains(cardType);
    }


}
