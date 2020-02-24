package cancer;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dbconnect
{
   	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public Dbconnect()
 	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/AAC","root","");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public ResultSet check(String un,String pass)
	{
		int k=0;
		try
		{
			ps=con.prepareStatement("select * from login where UNAME=? and PASSWORD=?");
			ps.setString(1,un);
			ps.setString(2,pass);
			rs=ps.executeQuery();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
	
		}
		return rs;

	}

        public ResultSet getAll()
        {
            	try
		{

			ps=con.prepareStatement("select * from hprd");
			rs=ps.executeQuery();
                }
                catch(Exception e)
		{
			System.out.println(e);

		}
		return rs;


        }
	public ResultSet getId()
			{
				
				try
				{
					ps=con.prepareStatement("select UID from login");
					rs=ps.executeQuery();
				}
				catch(Exception ex )
				{
					System.out.println("Error in checking:"+ex);
				}
				return rs;
			}

	public int insertDetails(String uname,String name,String pass,String noi,String type)
	{
	int k=0,k1=0;
		try
		
		{
			ps=con.prepareStatement("insert into login(UNAME,PASSWORD)values(?,?)");
			ps.setString(1,uname);
			ps.setString(2,pass);
			k=ps.executeUpdate();
			if(k==1)
			{
		        ps=con.prepareStatement("insert into Reg(NAME,NOI,TYPE,UID)values(?,?,?,(select UID  from login where UNAME=? and PASSWORD=?))");
			ps.setString(1,name);
			ps.setString(2,noi);
			ps.setString(3,type);
			ps.setString(4,uname);
			ps.setString(5,pass);			
			k1=ps.executeUpdate();
			}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
	
		}
		return k1;
	}
	public ResultSet getDetails(String id)
			{
				try
				{
					ps=con.prepareStatement("select login.*,Res.* from login,Res where login.UID=Res.UID and login.UID=?");
					ps.setString(1,id);
					rs=ps.executeQuery();
				}
				catch(Exception a)
				{
					System.out.println("Error in getdetails="+a);
				}
				return rs;	
			}	
	
                public ResultSet hprd_check(String p[]) throws SQLException
                {
                    int ch;
                    try
				{
                                        p[0]=p[0].trim();
                                        p[1]=p[1].trim();
					ps=con.prepareStatement("select p1,p2 from hprd where (p1=? or p2=?) and (p1=? or p2=?)");

                                        ps.setString(1,p[0]);
                                        ps.setString(2,p[0]);
                                        ps.setString(3,p[1]);
                                        ps.setString(4,p[1]);

                                        //System.out.println(ps);
					rs=ps.executeQuery();
                        
				}
				catch(Exception a)
				{
					System.out.println("Error in getdetails="+a);
				}
				return rs;
		}
                
                public int insertSingle(String p[]) throws SQLException
                {
                   int di;
                   ps=con.prepareStatement("insert into singlelist(protein)values(?)");
                   ps.setString(1, p[0]);
                   di=ps.executeUpdate();
                   
                   ps=con.prepareStatement("insert into singlelist(protein)values(?)");
                   ps.setString(1, p[1]);
                   di=ps.executeUpdate();

                    return di;
                }
                
                public ArrayList getDistinct() throws SQLException
                {
                    int i=0;
                    ArrayList ar=new ArrayList();
                    ps=con.prepareStatement("select distinct(protein) from singlelist");
                    rs=ps.executeQuery();
                    while(rs.next())
                    {
                        ar.add(rs.getString(1));//add each protein(resultset) into arraylist
                    }
                    return ar;
                }

                public ResultSet getcancer()  
                {
                try {
            
                    ps=con.prepareStatement("select * from genesig");
                    rs=ps.executeQuery();
                } catch (SQLException ex) {
                 Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
                }
                    return rs;
                }
                
                public int insertprotein(String p,String ty) throws SQLException
                {
                    int di1;
                   ps=con.prepareStatement("insert into proteinlist(protein,type)values(?,?)");
                   ps.setString(1, p);
                   ps.setString(2, ty);
                   di1=ps.executeUpdate();
                    return di1;
                }
                
         public ResultSet getAll2()
         {
		try
                {
                    ps = con.prepareStatement("select * from proteinlist");
                    rs = ps.executeQuery();
		}
                catch (SQLException ex)
		{
                    Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
		}

                return rs;
	
	}

	public ResultSet getCancer(int limit)
	{
		try
		{
			ps=con.prepareStatement("select * from proteinlist where type='C' order by no asc limit 0,?");
			ps.setInt(1,limit);
			rs=ps.executeQuery();
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return rs;
	}

	public ResultSet getNonCancer(int limit)
	{
		try
		{
			ps=con.prepareStatement("select * from proteinlist where type='N' order by no asc  limit 0,?");
			ps.setInt(1,limit);
                        System.out.println(ps);
			rs=ps.executeQuery();
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return rs;
	}

        public void clear()
	{
	        try
			{
			ps = con.prepareStatement("truncate table bet");
                        rs = ps.executeQuery();
                        ps = con.prepareStatement("truncate table final");
                        rs = ps.executeQuery();
                        ps = con.prepareStatement("truncate table graph");
                        rs = ps.executeQuery();

			}
			catch (SQLException ex)
			{
				Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
			}

	}
	public int insertBetween(String val)
	{
            int count_val=1,di1=0;

		try
		{
            		ps=con.prepareStatement("select count_val from bet where val=?");
					ps.setString(1,val);
					rs=ps.executeQuery();
                        if(rs.next())
                        {
                            count_val=rs.getInt(1);
                            count_val=count_val+1;

                            ps=con.prepareStatement("update bet set count_val=? where val=?");
                            ps.setInt(1,count_val);
                            ps.setString(2,val);
                            di1=ps.executeUpdate();

                        }
                        else
                        {
                            ps=con.prepareStatement("insert into bet(val,count_val)values(?,?)");
                            ps.setString(1,val);
                            ps.setInt(2,count_val);
                            di1=ps.executeUpdate();
                        }


		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return di1;
	}

        public void insertFinal(String protein_name,String final_score,String type)
	{
            int count_val=1,di1=0;

		try
		{
                            ps=con.prepareStatement("insert into final(pname,final_value,type)values(?,?,?)");
                            ps.setString(1,protein_name);
                            ps.setString(2,final_score);
                            ps.setString(3,type);
                            di1=ps.executeUpdate();
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		
	}

	public ResultSet getBetween()
	{
		try
		{
			ps=con.prepareStatement("select * from bet");
			rs=ps.executeQuery();
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return rs;
	}


	public Vector getFinal()
	{
            Vector main=new Vector();
		try
		{
			ps=con.prepareStatement("select * from final order by final_value * 1 desc");
			rs=ps.executeQuery();
                        int i=1;
                        while(rs.next())
                        {
                             Vector sub=new Vector();
                             sub.addElement(i);
                             sub.addElement(rs.getString(2));
                             sub.addElement(rs.getString(3));
                             sub.addElement(rs.getString(4));
                             main.addElement(sub);
                             i++;
                        }
		}
		catch(Exception er)
		{
			System.out.println(er);
		}
		return main;
	}

        public void truncate()
    {
        try {
            ps = con.prepareStatement("truncate table singlelist");
        } catch (SQLException ex) {
            Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs = ps.executeQuery();
            ps = con.prepareStatement("truncate table proteinlist");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

        public int getFinalThirtyCancer()
        {
            int co1=0,value=0;
			double co=0;
            try
            {
                 ps = con.prepareStatement("select 0.3*count(fid) from final");
                  System.out.println(ps);
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     co=rs.getDouble(1);
					 co1=(int)(co+0.5);
                 }
                 ps = con.prepareStatement("select * from final where type='C' and fid<=? limit 0,?");
                 ps.setInt(1,co1);
                 ps.setInt(2,co1);
                 System.out.println(ps);
                 rs = ps.executeQuery();
                  while(rs.next())
                 {
                     value++;
                 }
                 
            }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return value;
        }

         public int  getFinalThirtyNon()
        {
            int co1=0,value=0;
			double co=1;
            try
            {
                 ps = con.prepareStatement("select 0.3*count(fid) from final");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     co=rs.getDouble(1);
					 co1=(int)(co+0.5);

                 }
                 ps = con.prepareStatement("select * from final where type='N' and fid<? limit 0,?");
                 ps.setInt(1,co1);
                 ps.setInt(2,co1);
                 System.out.println(ps);
                 rs = ps.executeQuery();
                 while(rs.next())
                 {
                     value++;
                 }

            }
            catch(Exception er)
            {
                System.out.println("Error int getFinalNon"+er);
            }
            return value;
        }

       public int insertGraphValues(String a,int b,int c,float d,int e,String f)
        {
           int di1=0;
        try {
            
            ps = con.prepareStatement("insert into graph(p,deg,short,cluster,bet,type)values(?,?,?,?,?,?)");
            ps.setString(1,a);
            ps.setInt(2,b);
            ps.setInt(3,c);
            ps.setFloat(4,d);
            ps.setInt(5,e);
            ps.setString(6,f);
///            System.out.println("------------------------------"+ps);

            di1 = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return di1;
       }
         public int getFinalTotalCancer()
        {
            int value=0;

            try
            {
                 ps = con.prepareStatement("select count(fid) from final where type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     value=rs.getInt(1);
                 }
           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return value;
        }

          public int getFinalTotalNonCancer()
        {
            int value=0;

            try
            {
                 ps = con.prepareStatement("select count(fid) from final where type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     value=rs.getInt(1);
                 }
           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return value;
        }

        public ArrayList getAllDegree()
        {
            ArrayList ar=new ArrayList();
               String v1="",v2="";
               String bet1="";
            try
            {
                 ps = con.prepareStatement("select count(gid) from graph where deg between 1 and 2 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where deg between 1 and 2 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);


                  ps = con.prepareStatement("select count(gid) from graph where deg between 3 and 4 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where deg between 3 and 4 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where deg between 5 and 6 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where deg between 5 and 6 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where deg between 7 and 8 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where deg between 7 and 8 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return ar;
        }

 public ArrayList getAllShort()
        {
            ArrayList ar=new ArrayList();
               String v1="",v2="";
               String bet1="";
            try
            {
                 ps = con.prepareStatement("select count(gid) from graph where short between 1 and 30 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between 1 and 30 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);


                  ps = con.prepareStatement("select count(gid) from graph where short between 30 and 60 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between 30 and 60 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where short between 60 and 90 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between 60 and 90 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where short between 90 and 120 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between 90 and 120 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return ar;
        }

public ArrayList getAllCluster()
        {
            ArrayList ar=new ArrayList();
               String v1="",v2="";
               String bet1="";
            try
            {
                 ps = con.prepareStatement("select count(gid) from graph where cluster between 0 and 0.2 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where cluster between 0 and 0.2 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);


                  ps = con.prepareStatement("select count(gid) from graph where cluster between .2 and .4 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between .2 and .4 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where short between .4 and .6 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between .4 and .6 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where short between .6 and .8 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where short between .6 and .8 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return ar;
        }
public ArrayList getAllBet()
        {
            ArrayList ar=new ArrayList();
               String v1="",v2="";
               String bet1="";
            try
            {
                 ps = con.prepareStatement("select count(gid) from graph where bet between 0 and 20 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where bet between 0 and 20 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);


                  ps = con.prepareStatement("select count(gid) from graph where bet between 21 and 40 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where bet between 21 and 40 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where bet between 41 and 60 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where bet between 41 and 60 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where bet between 61 and 80 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where bet between 61 and 80 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                  bet1=v1+"#"+v2;

                 ar.add(bet1);

                 ps = con.prepareStatement("select count(gid) from graph where bet between 81 and 100 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                 ps = con.prepareStatement("select count(gid) from graph where bet between 81 and 100 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                  bet1=v1+"#"+v2;

                 ar.add(bet1);

                  ps = con.prepareStatement("select count(gid) from graph where bet>100 and type='C'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v1=rs.getString(1);
                 }
                  ps = con.prepareStatement("select count(gid) from graph where bet>100 and type='N'");
                 rs = ps.executeQuery();
                 if(rs.next())
                 {
                     v2=rs.getString(1);
                 }
                 bet1=v1+"#"+v2;

                 ar.add(bet1);

           }
            catch(Exception er)
            {
                System.out.println("Error int getFinalCancer"+er);
            }
            return ar;
        }



}

