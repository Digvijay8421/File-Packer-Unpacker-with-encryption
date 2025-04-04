import java.io.*;
import java.util.*;

class PRogram479
{
    public static void main(String Arg[]) 
    {
        
        String DirName = "", PackName = "", Header = "";
        FileOutputStream fopackobj = null;
        FileInputStream fiobj = null;
        int i = 0, j = 0, iRet = 0, iCount = 0;
        boolean bret = false;
        Scanner sobj = null;
        File Packobj = null;
        File fobj = null;

        try 
        {

            sobj = new Scanner(System.in);
            byte Buffer[] = new byte[1024];

            System.out.println("---------------------------------------------------------------------");
            System.out.println("-------------- Marvellous Packer Unpacker CUI Module ----------------");
            System.out.println("---------------------------------------------------------------------");

            System.out.println("---------------------------- Packing Activity -----------------------");

            System.out.println("Enter the name of directory that you wnat to open for packing ");
            DirName = sobj.nextLine();

            System.out.println("Enter the name of the file that we want to create : ");
            PackName = sobj.nextLine();

            Packobj = new File(PackName);
            bret = Packobj.createNewFile();
            if(bret == true)
            {
                System.out.println("Packed file gets succesfully created with the name : "+PackName);
            }
            else
            {
                System.out.println("Unable to proceed as pack file is not created");
                return;
            }

            fopackobj = new FileOutputStream(Packobj);

            fobj = new File(DirName);

            if(fobj.exists())
            {
                System.out.println("Directory is succesfully opened");

                File Arr[] = fobj.listFiles();

                for(i = 0; i < Arr.length; i++)
                {
                    if((Arr[i].getName().endsWith(".txt")))
                    {
                        Header = Arr[i].getName()+" "+Arr[i].length();
                        
                        for(j = Header.length(); j < 100; j++)
                        {
                            Header = Header + " ";
                        }

                        // Header writing
                        fopackobj.write(Header.getBytes(),0,100);

                        fiobj = new FileInputStream(Arr[i]);

                        // File data writting
                        while((iRet = fiobj.read(Buffer)) != -1)
                        {
                            fopackobj.write(Buffer, 0, iRet);
                        }

                        fiobj.close();
                        Header = "";
                        iCount++;
                    }
                }
                System.out.println("Total number of files scanned from the directory are : "+Arr.length);
                System.out.println("Total numbr of files packed : "+iCount);

                fiobj.close();
                fopackobj.close();
            }    
            else
            {
                System.err.println("There is no such directroy");
            }
            System.out.println("---------------------------------------------------------------------");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception Occured..: "+eobj);
        }
    }
}