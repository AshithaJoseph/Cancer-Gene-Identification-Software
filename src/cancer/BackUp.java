package cancer;
import java.util.*;
 
public class BackUp
{
	static int[][] path;
 
	static int[][] next;
        Dbconnect db_obj=new Dbconnect();
        static int shorts[]={};
        static int between[]={};

	
	public void Floyd(int[][] path1)
	{
		int path_sum[]=new int[path1.length];

		String val[]=new String [path1.length];

		for(int aa=0;aa<path1.length;aa++)
		{
			val[aa]="";
			int space=0;

			for(int bb=0;bb<path1.length;bb++)
			{
				if(path1[aa][bb]==1)
				{
					if(space>0)
					{
						val[aa]=val[aa]+" "+String.valueOf(bb+1)+" "+path1[aa][bb];
					}
					else
					{
						space=space+1;
						val[aa]=val[aa]+String.valueOf(bb+1)+" "+path1[aa][bb];
					}
				}
			}
		}

		/*for(int dd=0;dd<val.length;dd++)
		{
			//System.out.println(val[dd]);
		}*/
	
		Scanner s = new Scanner(System.in);
		Vector<Node> graph = new Vector<Node>();
		int n = path1.length;
		//s.nextLine();
		String node;
		String[] edges;
		for(int i=0; i<n; i++)
		{
// 			node = s.next();
			node = String.valueOf(i+1);
			graph.addElement(new Node(node));
			//edges = s.nextLine().trim().split(" ");
			edges = val[i].trim().split(" ");
			for(int j=0; j<edges.length/2; j++) 
			{
				graph.elementAt(i).edges.addElement(new Node(edges[j*2]));
				graph.elementAt(i).weights.addElement(Integer.parseInt(edges[j*2+1]));
			}
		}
		Collections.sort(graph);
		floydWarshall(graph);

		int path_count=0;

		int imp_count[]=new int[path1.length];
                db_obj.clear();

		String imp[]=new String[path1.length];

		for(int i=0; i<graph.size(); i++) 
		{
			for(int j=0; j<graph.size(); j++) 
			{
				//System.out.print("From " + graph.elementAt(i) + " to " + graph.elementAt(j));
				//System.out.println(" " + path[i][j]);

                            db_obj.insertBetween(String.valueOf(path[i][j]));

				path_sum[path_count]=path_sum[path_count]+path[i][j];
				String temp = getPath(graph, i, j);

				imp[i]=imp[i]+temp;
				//System.out.println(temp);
			}
			path_count++;

		}

               shorts=new int[path1.length];
               between=new int[path1.length];

//		System.out.println("Average Shortest Path:");

		for(int i=0; i<path_count; i++) 
		{
                        int a=path_sum[i]/path1.length;
			//System.out.println(path_sum[i]/path1.length);
                        shorts[i]=a;
  
		}
		
		try
		{
			
//			System.out.println("Betweenness Centrality:");
			
			//System.out.println("");
			java.sql.ResultSet rs=db_obj.getBetween();
			int i=0;
			while(rs.next())
			{
  //                          	System.out.println("Value="+rs.getString(2)+"    Count Value="+(rs.getInt(3)/2));
                                between[i]=rs.getInt(3)/2;
                                i++;
			}
		}
		catch(Exception er)
		{
			System.out.println(er);
		}

               try
               {
                    System.out.println("b4 try");
                    Process p=Runtime.getRuntime().exec("D:/matlab/eigenvector.exe");
                    System.out.println("after try");
               }
               catch(Exception er)
               {
                   System.out.println(er);
               }



	}
 
	public static void floydWarshall(Vector<Node> graph)
	{
		path = new int[graph.size()][graph.size()];
		next = new int[graph.size()][graph.size()];
		for(int i=0; i<graph.size(); i++)
		for(int j=0; j<graph.size(); j++) 
		{
			path[i][j] = 0;
			next[i][j] = -1;
		}
		for(int i=0; i<graph.size(); i++)
		{
			Vector<Node> edges = graph.elementAt(i).edges;
			Vector<Integer> weights = graph.elementAt(i).weights;
			for(int j=0; j<edges.size(); j++)
			{
				int index = Collections.binarySearch(graph, edges.elementAt(j));
				path[i][index] = weights.elementAt(j);
			}
			for(int j=0; j<graph.size(); j++)
			{
				if(path[i][j] == 0)
				path[i][j] = 111;
				if(i == j)
				path[i][j] = 0;
			}
		}
		for(int k=0; k<graph.size(); k++)
		for(int i=0; i<graph.size(); i++)
			for(int j=0; j<graph.size(); j++)
			if(path[i][k] + path[k][j] < path[i][j]) 
			{
				path[i][j] = path[i][k] + path[k][j];
				next[i][j] = k;
			}




                        try{
                Process p=Runtime.getRuntime().exec("C:\\Users\\Seban\\Documents\\NetBeansProjects\\cancer1\\src\\cancer\\eigenvec.exe");
            }catch(Exception e){
                System.out.println("error:"+e);
            }
	}
 
	public static String getPath(Vector<Node> graph, int i, int j)
	{
		if(path[i][j] == 111)
			return "no path";
		int mid = next[i][j];
		if(mid == -1)
			return "";
		else
			return getPath(graph, i, mid) + " " + graph.elementAt(mid) + " " + getPath(graph, mid, j);
	}
}
 
class Node implements Comparable<Node> 
{
	String value;
	Vector<Node> edges;
	Vector<Integer> weights;
	
	public Node(String val)
	{
		value = val;
		edges = new Vector<Node>();
		weights = new Vector<Integer>();
	}
	
	public int compareTo(Node n)
	{
		return value.compareTo(n.value);
	}
	
	public String toString()
	{
		return value;
	}
}