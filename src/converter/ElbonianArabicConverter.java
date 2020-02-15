package converter;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     * <p>
     * @param number A string that represents either a Elbonian or Arabic number.
     *
     * @throws MalformedNumberException  Thrown if the value is an Elbonian number that does not conform
     *                                   to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     *                                   in the Elbonian number system.
     */

    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string
        try {
            NumisValid(number);
        }
        catch (NumberFormatException e){
            ElbonianisValid(number);
        }
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        try{
             return Integer.parseInt(this.number);
        }
        catch (NumberFormatException e){
            int no = 0;
            for (int i = 0; i < this.number.length(); ++i){
                char numberChar = this.number.charAt(i);
                switch (numberChar){
                    case 'M':
                        no = no + 1000;
                        break;
                    case 'E':
                        no = no + 500;
                        break;
                    case 'D':
                        no = no + 300;
                        break;
                    case 'C':
                        no = no + 100;
                        break;
                    case 'Z':
                        no = no + 50;
                        break;
                    case 'Y':
                        no = no + 30;
                        break;
                    case 'X':
                        no = no + 10;
                        break;
                    case 'K':
                        no =  no + 5;
                        break;
                    case 'J':
                        no = no + 3;
                        break;
                    case 'I':
                        no = no + 1;
                        break;
                }
            }
            return no;
        }
    }

    private void NumisValid(String str) throws ValueOutOfBoundsException{
        int newNo = Integer.parseInt(str);
        if ((newNo <= 0) || (newNo >= 4998)){
            throw new ValueOutOfBoundsException("The number" + newNo + "is not Valid. Please use a Valid number between 0 and 4998");
        }
    }

    private void ElbonianisValid(String string) throws MalformedNumberException{
        char[] newcharArray = string.toCharArray();
        for (Character S : newcharArray){
            if(!(S == 'M' || S  == 'E' || S == 'D' || S == 'C' || S == 'Z' || S == 'Y' || S == 'X' || S == 'K' || S == 'J' || S == 'I')){
                throw new MalformedNumberException("The Elbonian Number " + S + "is not valid !");
            }
        }
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */

}
