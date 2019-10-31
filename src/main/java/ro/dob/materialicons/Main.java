package ro.dob.materialicons;

import ro.dob.materialicons.model.ConstantsKt;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            InputArgumentsParser.INSTANCE.parse(args);
        }
        catch (Exception ex)
        {
            if (ConstantsKt.DEBUG)
                ex.printStackTrace();
            try { Thread.sleep(10); }
            catch (Exception ex1) {}
            System.out.println(ConstantsKt.SYNTAX_DESCRIPTION);
        }
    }
}
