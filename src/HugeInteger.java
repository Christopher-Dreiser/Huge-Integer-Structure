import static java.lang.Math.abs;
import java.util.Arrays; //for array.tostring

public class HugeInteger
{
    private static final int NUM_DIGITS = 40;
    private int digits[]=new int[NUM_DIGITS];
    private boolean positive = true;
    
    public static void main(String args[])
    {
        
        String [][] testInputs = {
                {"987654321", "234567890"},
                {"987654321", "-234567890"},
                {"-987654321", "234567890"},
                {"-987654321", "-234567890"},
                {"234567890", "987654321"},
                {"234567890", "-987654321"},
                {"-234567890", "987654321"},
                {"-234567890", "-987654321"}};
        
        for(String [] ints : testInputs)
        {
            HugeInteger h1 = new HugeInteger(ints[0]);
            HugeInteger h2 = new HugeInteger(ints[1]);
            
            System.out.println("h1="+h1);
            System.out.println("h2="+h2);
            if(h1.isEqualTo(h2))
            {
                System.out.println("h1 is equal to h2.");
            }
            if(h1.isNotEqualTo(h2))
            {
                System.out.println("h1 is not equal to h2.");
            }
            if(h1.isGreaterThan(h2))
            {
                System.out.println("h1 is greater than h2.");
            }
            if(h1.isLessThan(h2))
            {
                System.out.println("h1 is less than to h2.");
            }
            if(h1.isGreaterThanOrEqualTo(h2))
            {
                System.out.println("h1 is greater than or equal to h2.");
            }
            if(h1.isLessThanOrEqualTo(h2))
            {
                System.out.println("h1 is less than or equal to h2.");
            }
            
            
            h1.add(h2); // h1 += h2
            System.out.println("h1.add(h2);");
            System.out.printf("h1=%s\n",h1);
            
            h1.subtract(h2); // h1 -= h2
            System.out.println("h1.subtract(h2);");
            h1.subtract(h2); // h1 -= h2
            System.out.println("h1.subtract(h2);");
            System.out.printf("h1=%s\n",h1);
            
            h1.add(h2); // h1 += h2
            System.out.println("h1.add(h2);");
            h1.multiply(h2); // h1 *= h2
            System.out.println("h1.multiply(h2);");
            System.out.printf("h1=%s\n\n",h1);
            
        }
    }
    
    //Constructor,
    HugeInteger(String num)
    {
        //removes the negative and changes the sign.
        if(num.substring(0,1).equals("-"))
        {
            negate();
            num = num.replace("-", "");
        }

        int length = num.length();
        int difference = NUM_DIGITS - length;

        //The least significant digit will be at the last index in the array.
        for(int i = length; i > 0; i--)
        {
            digits[difference + i - 1] =
                    Integer.parseInt(num.substring(i-1,i));
        }
    }

    //Converts the number to a string.
    public String toString()
    {
        String accumulate = "";
        boolean flag = false;

        //Adds the sign to the beginning of the number if negative
        if(!positive)
        {
            accumulate += "-";
        }

        //Checks digit by digit for nonzeroes until it finds one. Then adds all
        // digits from that point forward.
        for(int i = 0; i < NUM_DIGITS; i++)
        {
            if(flag || digits[i] != 0)
            {
                flag = true;
                accumulate += Integer.toString(digits[i]);
            }
        }

        return accumulate;
    }
    
    public boolean isEqualTo(HugeInteger hi)
    {
        //If the sign is not the same, it can be understood that the numbers
        // are not equal.
        if(this.positive != hi.positive)
        {
            return false;
        }
        //Checks each digit of each number. If any digit is not the same,
        // the numbers cannot be equal.
        for(int i = 0; i < NUM_DIGITS; i++)
        {
            if(digits[i] != hi.digits[i])
                return false;
        }

        return true;
    }
    
    //Simple use of "not"
    public boolean isNotEqualTo(HugeInteger hi)
    {
        return !this.isEqualTo(hi);
    }
    
    //The comparison method with the most conditions.
    public boolean isGreaterThan(HugeInteger hi)
    {
        //If any two numbers are the same, they cannot be greater than each
        // other
        if(this.isEqualTo(hi))
        {
            return false;
        }
        
        //If this is negative and hi is positive, then this cannot be greater
        // than hi.
        if(!this.positive && hi.positive)
        {
            return false;
        }
        
        //If this is positive and hi is negative, then this must be greater
        // than hi.
        if(this.positive && !hi.positive)
        {
            return true;
        }
        
        //Checks until a different digit is found. Decides what to do based on
        // sign.
        if(this.positive)
        {
           for(int i = 0; i < NUM_DIGITS; i++)
           {
               if(this.digits[i] > hi.digits[i])
               {
                   return true;
               }
               else if(this.digits[i] < hi.digits[i])
               {
                   return false;
               }
           }
        }
        else
        {
            for(int i = 0; i < NUM_DIGITS; i++)
            {
                if(this.digits[i] < hi.digits[i])
                {
                    return true;
                }
                if(this.digits[i] > hi.digits[i])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    //Checks the other, existing comparison functions.
    public boolean isLessThan(HugeInteger hi)
    {
        return (!this.isGreaterThan(hi) && !this.isEqualTo(hi));
    }
    
    //Checks the other, existing comparison functions.
    public boolean isGreaterThanOrEqualTo(HugeInteger hi)
    {
        return (this.isGreaterThan(hi) || this.isEqualTo(hi));
    }
    
    //Checks the other, existing comparison functions.
    public boolean isLessThanOrEqualTo(HugeInteger hi)
    {
        return (this.isLessThan(hi) || this.isEqualTo(hi));
    }
    
    public boolean isZero()
    {
        for(int i = NUM_DIGITS - 1; i >= 0; i++)
        {
            if(digits[i] != 0)
            {
                return false;
            }
        }
        return true;
    }
    
    //A function to add all digits of an array. Calls refactor().
    private void addArrayDigits(HugeInteger hi)
    {
        for(int i = 0; i < NUM_DIGITS; i++)
        {
            this.digits[i] += hi.digits[i];
        }
        this.refactor();
    }
    
    //A function to subtract all digits of an array. Calls refactor() to do
    // renaming.
    private void subtractArrayDigits(HugeInteger hi)
    {
        for(int i = 0; i < NUM_DIGITS; i++)
        {
            this.digits[i] -= hi.digits[i];
        }
        this.refactor();
    }
    
    //A function to decide whether to add or subtract the digits of the arrays.
    public void add(HugeInteger hi)
    {
        if(this.positive != hi.positive)
        {
            this.subtractArrayDigits(hi);
        }
        else
        {
            this.addArrayDigits(hi);
        }
    }
    
    //Another function to decide whether to add or subtract the digits of the
    // arrays.
    public void subtract(HugeInteger hi)
    {
        if(this.positive != hi.positive)
        {
            this.addArrayDigits(hi);
        }
        else
        {
            this.subtractArrayDigits(hi);
        }
    }

    //Multiplies by another HugeInteger.
    public void multiply(HugeInteger hi)
    {
        int[] multiplied = new int[NUM_DIGITS];
        for(int i = NUM_DIGITS - 1; i >= 0; i--)
        {
            if(digits[i] != 0)
            {
               continue;
            }
            for(int j = NUM_DIGITS - 1; j >= 0; j--)
            {
                if(NUM_DIGITS - 1 - i - j < NUM_DIGITS &&
                        NUM_DIGITS - 1 - i - j >= 0)
                {
                    //Formula based on multiplication as done on paper.
                    multiplied[NUM_DIGITS - 1 - i - j] +=
                            this.digits[NUM_DIGITS - 1 - i] *
                                    hi.digits[NUM_DIGITS - 1 - j];
                }
                //Overflow trapping. Completely optional, but a fun problem to
                // solve.
                else if(this.digits[NUM_DIGITS - 1 - i] *
                        hi.digits[NUM_DIGITS - 1 - j] != 0)
                {
                    System.out.println("Overflow in multiplication, result not"
                            + " reliable. Cancelling...");
                    return;
                }
            }
        }

        //switches sign if other number
        if(!hi.positive)
        {
            negate();
        }

        for(int i = 0; i < NUM_DIGITS; i++)
        {
            this.digits[i] = multiplied[i];
        }
        refactor();
    }

    public void negate()
    {
        if(positive)
        {
            positive = false;
        }
        else
        {
            positive = true;
        }
    }

    //Changes sign variable, then changes the signs of all digits.
    private void negateDigits()
    {
        negate();

        for(int i = 0; i < NUM_DIGITS; i++)
        {
            digits[i] *= -1;
        }
    }
    //This method performs the carry operations required to ensure the digits
    //each have a spot in the array
    private void refactor()
    {

        //For loop to check whether the first non-zero digit is negative.
        boolean flag = false;
        for(int i = 0; i < NUM_DIGITS && !flag; i++)
        {
            if(digits[i] != 0)
            {
                flag = true;
            }
            //If the first nonzero digit is negative, runs the negateDigits
            //function.
            if(digits[i] < 0)
            {
                negateDigits();
            }
        }
        
        //If the digit is negative, takes one from the next digit up and add 10
        // to the current digit.
        for(int i = NUM_DIGITS - 1; i > 1; i--)
        {
            while(digits[i] < 0)
            {
                digits[i-1] -= 1;
                digits[i] += 10;
            }
            if(digits[i] >= 10)
            {
                digits[i-1] += (digits[i] / 10);
                digits[i] = digits[i] % 10;
            }
        }
        
        //If the first "digit" is more than one digit, the array is approaching
        // overflow.
        if(this.digits[0] >= 10)
        {
            System.out.println("Warning: Integer is approaching overflow.");
        }
    }


}