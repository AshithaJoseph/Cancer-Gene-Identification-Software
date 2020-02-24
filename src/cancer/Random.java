package cancer;
public class Random
{
	public int getRandom(int Min,int Max)
	{
		return (Min + (int)(Math.random() * ((Max - Min) + 1)));
		
		
	}
// 	public static void main(String args[])
// 	{
// 		random obj=new random();
// 		int a=obj.getRandom(5,7);
// 		System.out.println(a);
// 	}
}