package cancer;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;





public class Find
{
    // static int[][] path1=new int[][]{{0,1,0,0},{1,0,1,0},{0,1,0,1},{0,0,1,0}};
    Dbconnect db_obj=new Dbconnect();
    UserProfileNew obj1=new UserProfileNew();
    String random_protein[]={};
    static int tot_noncanc;
    static int tot_canc;
    static int tot_pro;
    static int tot_intr;

    public void Find_fun()
    {
        {
            try {
                ArrayList ar2 = new ArrayList();
                ArrayList ar1 = new ArrayList();
                ResultSet prlist;
                int perc;
                Dbconnect db = new Dbconnect();
                prlist = (ResultSet) db.getAll2();
                while (prlist.next()) {
                    if (prlist.getString(3).equals("N")) {
                        ar1.add(prlist.getString(2));
                    } else {
                        ar2.add(prlist.getString(2));
                    }
                }
                tot_noncanc=ar1.size();
                tot_canc=ar2.size();
                int perc1;
                String v1;
                System.out.println(ar1);
                v1=obj1.perc2;
//                System.out.println(v1);
                perc1=Integer.parseInt(v1);
  //              System.out.println(perc1);
                if (ar1.size() < ar2.size()) {
                    perc = ((ar1.size() * perc1) / 100);
                } else {
                    perc = ((ar2.size() * perc1) / 100);
                }
                
               // System.out.println("perccccccccc"+perc);
                
                Random obj = new Random();
                int c1 = 0;
                int c2 = 0;
                ArrayList ls = new ArrayList();
                ArrayList mat = new ArrayList();
                int[] arry = new int[perc];
                int jj = 0;
                for (int kk = 0; kk < perc; kk++) {
                    int val = obj.getRandom(0, perc);
                    int flg = 0;
                    if (arry.length == 0) {
                        arry[kk] = val;
                    } else {
                        for (int ll = 0; ll < arry.length; ll++) {
                            if (arry[ll] == val) {
                                flg = 1;
                            }
                        }
                        if (flg == 0) {
                            arry[kk] = val;
                        } else {
                            kk--;
                        }
                    }
                }

                random_protein= new String[arry.length*2];

                int ran_count=0;
                
                for (int hh = 0; hh < arry.length; hh++) {
                    java.sql.ResultSet res = db.getCancer(arry[hh]);
                    if (res.last()) {
                        ls.add(res.getString(2));
                         System.out.println("========================================================="+res.getString(2));
                        random_protein[ran_count]=res.getString(2);
                    }
                    ran_count++;
                }
                for (int hh = 0; hh < arry.length; hh++) {
                    ResultSet res = (ResultSet) db.getNonCancer(arry[hh]);
                    if (res.last()) {
                        ls.add(res.getString(2));
                        System.out.println("========================================================="+res.getString(2));
                        random_protein[ran_count]=res.getString(2);
                    }
                    ran_count++;
                }
                for (int gg = 0; gg < ls.size(); gg++) {
                    mat.add(ls.get(gg));
                }
                //InputStream fin = new FileInputStream(new File("D:/input.txt"));
                //FileReader fr = new FileReader(new File("D:/input.txt"));
               // System.out.println("==================================");


              /*  File fnew = new File("E:/input2.txt");
                if(!fnew.exists())
                {
                    fnew.createNewFile();
                }
                Scanner sc = new Scanner(new File("E:/input.txt"));
                PrintWriter p = new PrintWriter(fnew);
                while(sc.hasNextLine())
                {
                    String s = sc.nextLine();
                    p.write(s);
                }*/

                try
                {
                FileInputStream fs = new FileInputStream("D:/input.txt");
                DataInputStream ds = new DataInputStream(fs);
                BufferedReader br = new BufferedReader(new InputStreamReader(ds));
               
                String s;
             //   System.out.println("aaaaaaaaaaa ");
             
                while ((s = br.readLine()) != null)
                {
                    //System.out.println("bbbbbbbbbbbb"+s);
                    String[] stra = s.split("\t");
                   /* System.out.println("aaaaaaaaaaaaaa"+stra[0]);
                     System.out.println("bbbbbbbbbbbbb"+stra[1]);
                        //stra[0]=stra[0].trim();
                        //stra[1]=stra[1].trim();
                    System.out.println("******aaaaaaaaaa"+stra[0]);
                     System.out.println("******bbbbbbbb"+stra[1]);*/

                    for (int gg = 0; gg < ls.size(); gg++)
                    {


                        if (stra[0].equals(ls.get(gg)))
                        {
                            int coun=0;

                            for (int ggg = 0; ggg < mat.size(); ggg++) {
                                 System.out.println(stra[1]+"@@@@@@@@@@@@@@@@@@@@"+mat.get(ggg));
                                 
                                if(mat.get(ggg).equals(stra[1]))
                                {
                                    coun=1;
                                     System.out.println("======================"+mat.get(ggg));
                                     System.out.println("***************"+stra[1]);
                                }
                            }

                            if(coun==0)
                            {
                                mat.add(stra[1]);
                            }
                        }
                        
                        else if (stra[1].equals(ls.get(gg)))
                        {
                           int coun=0;

                            for (int ggg = 0; ggg < mat.size(); ggg++) {
                                 System.out.println("$$$$$$$$$$$$$"+mat.get(ggg));

                                if(mat.get(ggg).equals(stra[0]))
                                {
                                    coun=1;
                                    System.out.println("======================"+mat.get(ggg));
                                     System.out.println("***************"+stra[0]);

                                }
                            }

                            if(coun==0)
                            {
                                mat.add(stra[0]);
                            }
                        }
                    }
                }
                
                br.close();

                }
                catch(Exception er)
                {
                    System.out.println(er);
                }
                int[][] res_ar = new int[mat.size()][mat.size()];
                for (int rrr = 0; rrr < mat.size(); rrr++) {
                    for (int sss = 0; sss < mat.size(); sss++) {
                        res_ar[rrr][sss] = 0;
                    }
                }
                
                int[][] path1=new int[mat.size()][mat.size()];

                String[][] res = new String[mat.size()][mat.size()];

                
                for (int gg = 0; gg < mat.size(); gg++) {
                    for (int ii = 0; ii < mat.size(); ii++) {


                        //===================================

                        FileReader fr1 = new FileReader(new File("D:/input.txt"));
               // System.out.println("==================================");

                        BufferedReader br1 = new BufferedReader(fr1);
                        String s1;
                      //  FileWriter f_out = null;

                        while ((s1 = br1.readLine()) != null)
                        {
                           // System.out.println("aaaaaaaaaaa in  find"+s1);
                           // String[] stra1 = s1.split("\t");

                            String te=(String)mat.get(gg);
                            String et=(String)mat.get(ii);

                           // System.out.println(te+"\t"+et+"\t"+s1);
                                if (s1.equals(te+"\t"+et)||s1.equals(et+"\t"+te))
                                {
                                    res_ar[gg][ii]=1;
                            
                                }

                            
                        }
                        br1.close();








                        //==============================================
                        
                    }
                }
               



                 for (int rrr = 0; rrr < mat.size(); rrr++) {
                    for (int sss = 0; sss < mat.size(); sss++) {
                       System.out.print(res_ar[rrr][sss]+"\t");
                    }
                    System.out.println("");
                }









                int[] degree = new int[mat.size()];
                int[] degree1 = new int[mat.size()];

                
                for (int ww = 0; ww < mat.size(); ww++) {
                    for (int ee = 0; ee < mat.size(); ee++) {
                        //System.out.print(res_ar[ww][ee] + "\t");
                        degree[ww] = degree[ww] + res_ar[ww][ee];
                        path1[ww][ee]=res_ar[ww][ee];
                    //    System.out.println("-------------"+path1[ww][ee]);
                    }
                
                }
                
                for (int ff = 0; ff < mat.size(); ff++) {
                //    System.out.println(degree[ff]);
                    
                }
                float cluster[]=new float[mat.size()];
                
                
                for (int ff = 0; ff < mat.size(); ff++) {
                    float cc = (float) degree[ff] / mat.size();
                    cluster[ff]=cc;
                   // System.out.println(cc);
                }
                BackUp Floyd_obj = new BackUp();
                Floyd_obj.Floyd(path1);


                  



                int arra[]=BackUp.shorts;
                int arrb[]=BackUp.between;

               

                for(int i=0;i<ran_count;i++)
                {
                    //System.out.println(random_protein[i]);
                
                    java.sql.ResultSet rs1 = db.getcancer();
                    String value;
                    int check = 0;
                  
                    while (rs1.next()) {
                  
                        if (random_protein[i].equals(rs1.getString(2))) {

                            check = 1;
                        }
                    }
                    if (check == 1) {
                        value= "C";
                    } else {
                        value="N";
                    }


                    int oo=db_obj.insertGraphValues(random_protein[i],degree[i],arra[i],cluster[i],arrb[i],value);

                    arrb[i]=arrb[i]/(ran_count-1);
                    arra[i]=arra[i]/1000;
                    degree1[i]=degree[i]/(ran_count-1);

                    float final_res=(arrb[i]+degree1[i]+cluster[i]+arra[i]);

              
                    db_obj.insertFinal(random_protein[i],String.valueOf(final_res),value);

                }

                 
                 tot_pro=obj1.tot_pro;
                 tot_intr=obj1.tot_intr;

                 new InputStat(tot_intr,tot_pro,tot_canc,tot_noncanc).setVisible(true);

            } catch (IOException ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (SQLException ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                } catch (Exception ex) {
                    Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

		
	}
}
