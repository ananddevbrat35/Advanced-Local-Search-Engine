package rt;
import java.io.*;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.util.*;
class FileListing {

	List<String> li=new ArrayList<String>();
	List<String> li1=new ArrayList<String>();
	void listFileAndFolder(String dname)
	{
		 
		File i1=new File(dname);
		File []f=i1.listFiles();
		 try {
		for(File i2:f)
		{
			if(i2.isFile())
			{
				
				String s=i2.getName();
				int in=s.lastIndexOf('.');
				if(s.substring(in+1).equals("txt")||s.substring(in+1).equals("pdf"))
				{
					if(s.substring(in+1).equals("txt"))
						li.add(i2.getAbsolutePath());
					if(s.substring(in+1).equals("pdf"))
						li1.add(i2.getAbsolutePath());
				}
			
			}
			else if(i2.isDirectory())
			{
				try {
				listFileAndFolder(i2.getAbsolutePath());
				}
				catch(Exception e) {}
			}
			
		}
		 
		 }
		 catch(Exception e) {}
	}		
}



class PdfRead {
	int total;
	int countOfPages(String inp) 
	{
		int pages=0;
		PdfReader pdfReader;
		try {
			
		 pdfReader = new PdfReader(inp);
		pages = pdfReader.getNumberOfPages();
		total=pages;
		pdfReader.close();
		}
		catch(Exception e)
		{
			return 0;
		}
		 
		
		return pages;
	}
	
	String read(String inp,int i){
		String pageContent="";   
		try {
			//Create PdfReader instance.
			PdfReader pdfReader = new PdfReader(inp);	
		    pageContent = PdfTextExtractor.getTextFromPage(pdfReader, i);  
		    
		      pdfReader.close();
		    
		    } 
		    catch (Exception e) {
			e.printStackTrace();
		    }
		    return pageContent;
		  }
	
	
	
}




class IndexFormation1
{
	HashMap<String,HashMap<Integer,ArrayList<Integer>>> hm;
	HashMap<String,HashMap<Integer,ArrayList<Integer>>> pdfversion;
	ArrayList<ArrayList<Integer>> ans1;
	HashMap<Integer,String> fmap;
//	String []fileName,stopListWord= {"a" ,"an", "a" ,"and" ,"are" ,"as" ,"at", "be","by" ,"for" ,"or","from" ,"has" ,"he" ,"in" ,"is" ,"it" ,"its", "of", "on", "that", "the", "to", "was","were", "will" ,"with"};
	FileReader fr;
	Main aii=new Main();
	
	String fobj=aii.obj11;
	//File obj1;
	BufferedReader br;

	IndexFormation1()
	{
		hm=new HashMap<String,HashMap<Integer,ArrayList<Integer>>>();
		pdfversion=new HashMap<String,HashMap<Integer,ArrayList<Integer>>>();
		//obj1=new File(fobj);
		fmap=new HashMap<Integer,String>();
		ans1=new ArrayList<ArrayList<Integer>>();
	}
	void generateTokens()
	{
		//String []obj2=obj1.list();
		FileListing obj=new FileListing();
		//folder
		try {
		obj.listFileAndFolder(fobj);
		}
		catch(Exception e) {}
		//text
		List<String> li=obj.li;
		//pdf
		List<String> li1=obj.li1;
		StringTokenizer st;
		for(int i=1;i<=li.size();i++)
		{
			fmap.put(i,li.get(i-1));
		}
		for(int i=li.size()+1,mm=1;i<=li1.size()+li.size();i++,mm++)
		{
			fmap.put(i,li1.get(mm-1));
		}
		
		String []brk;int count=0;
		String cline,regex=" +|[.]+|[,]+|[!]+|[:]|[;]|[?]+|[\"]+";
		try {
		for(int i=1;i<=li.size();i++)
		{
			 
			
			fr=new FileReader(li.get(i-1));
		 
			br=new BufferedReader(fr);
			cline=br.readLine();
			
			 
			count=1;
			
			while(cline!=null)
			{
				
				
				
				int y=0;
				st=new StringTokenizer(cline,regex); 
				brk=new String[st.countTokens()];
				while(st.hasMoreTokens())
				{
					brk[y++]=st.nextToken();
				}
				 
				for(int j=0;j<brk.length;j++)
				{
					if(hm.containsKey(brk[j].toLowerCase()))
					{
						HashMap<Integer,ArrayList<Integer>> hmap=hm.get(brk[j].toLowerCase());
						ArrayList<Integer> al;
						if(hmap.containsKey(i))
						{
							al=hmap.get(i);
							al.add(count+j);
								
						}
						else
						{
							al=new ArrayList<Integer>();
							al.add(count+j);
							hmap.put(i, al);
							
						}
						
						
					}
					else
					{
						HashMap<Integer,ArrayList<Integer>> hmap=new HashMap<Integer,ArrayList<Integer>>() ;
						ArrayList<Integer> al=new ArrayList<Integer>();
						al.add(count+j);
						hmap.put(i, al);
						hm.put(brk[j].toLowerCase(), hmap);
					}
				}
				count=brk.length;
				cline=br.readLine();
							
			}
			 	
		}
		
		//////////////////////
	
		for(int i=li.size()+1,lo=0;i<=li.size()+li1.size();i++,lo++)
		{
			int cnt=0;
			PdfReader pdfReader=null ;
			 try {
				PdfRead  ob=new PdfRead ();
				 cnt=ob.countOfPages(li1.get(lo));
				
			count=1;
			 pdfReader = new PdfReader(li1.get(lo));	
 		    
			 }
			 catch(Exception e) {}
			
			
			for(int yy=1;yy<=cnt;yy++)
			{
				
				try {
					 
				cline = PdfTextExtractor.getTextFromPage(pdfReader, yy);
				 
				int y=0;
				st=new StringTokenizer(cline,regex); 
				brk=new String[st.countTokens()];
				while(st.hasMoreTokens())
				{
					brk[y++]=st.nextToken();
				}
				 
				for(int j=0;j<brk.length;j++)
				{
					if(hm.containsKey(brk[j].toLowerCase()))
					{
						HashMap<Integer,ArrayList<Integer>> hmap=hm.get(brk[j].toLowerCase());
						ArrayList<Integer> al;
						if(hmap.containsKey(i))
						{
							al=hmap.get(i);
							al.add(count+j);
								
						}
						else
						{
							al=new ArrayList<Integer>();
							al.add(count+j);
							hmap.put(i, al);
							
						}
						
						
					}
					else
					{
						HashMap<Integer,ArrayList<Integer>> hmap=new HashMap<Integer,ArrayList<Integer>>() ;
						ArrayList<Integer> al=new ArrayList<Integer>();
						al.add(count+j);
						hmap.put(i, al);
						hm.put(brk[j].toLowerCase(), hmap);
					}
					
					
					//for page no
					 
				}
				count=brk.length;
				cline=br.readLine();
				}
				catch(Exception e)
				{
					//System.out.println(e);
				}
			}
			 pdfReader.close();
			 	
		}///////////////
		 
		}
		catch(Exception e)
		{
			System.out.println(e+"  jjj");
		}
		
		
	}
	
	ArrayList<ArrayList<Integer>> processQuery(String s1,int k,String s2)
	{
		if(hm.containsKey(s1)&&hm.containsKey(s2))
		{
			HashMap<Integer,ArrayList<Integer>> al1=hm.get(s1);
			HashMap<Integer,ArrayList<Integer>> al2=hm.get(s2);
			Set<Integer> st1=new HashSet<Integer>();
			Set<Integer> st2=new HashSet<Integer>();
			for(Integer i1:al1.keySet())
			{
				st1.add(i1);
			}
			for(Integer i1:al2.keySet())
			{
				st2.add(i1);
			}
			
			st1.retainAll(st2);
			return positionalIndex(st1,al1,al2,k);
			
		}
		else
		{
			String ss=hm.containsKey(s1)?(s2+": INVALID QUERY TERM :  NOT FOUND"):(s1+": INVALID QUERY TERM :  NOT FOUND");
			System.out.println(ss);
			return null;
		}
		
		
	}
	
	ArrayList<ArrayList<Integer>> positionalIndex(Set<Integer> st,HashMap<Integer,ArrayList<Integer>> al1,HashMap<Integer,ArrayList<Integer>> al2,int k)
	{
		 
		ArrayList<ArrayList<Integer>> ans=new ArrayList<ArrayList<Integer>>();
		for(Integer i1:st)
		{
			ArrayList<Integer> a1=al1.get(i1);
			ArrayList<Integer> a2=al2.get(i1);
			Collections.sort(a1);
			Collections.sort(a2);
			
			ArrayList<Integer> pq=new ArrayList<Integer>();
			int j=0;
			for(int i=0;i<a1.size();i++)
			{
				for(;j<a2.size();j++)
				{
					if(Math.abs(a1.get(i)-a2.get(j))<=k)
					{
						pq.add(a2.get(j));
					}
					else if(a1.get(i)<a2.get(j))
						break;
					
				}
				for(int k1=0;k1<pq.size();k1++)
				{
					int q=pq.get(0);
					if(Math.abs(a1.get(i)-q)>k)
					{
						pq.remove(0);
					}
					else break;
				}
				for(int k1=0;k1<pq.size();k1++)
				{
					ArrayList<Integer> as=new ArrayList<Integer>();
					as.add(i1);
					as.add(a1.get(i));
					as.add(pq.get(k1));
					ans.add(as);
					
				}
				
				
				
			}
			
			 
		}
	 
		return ans;
		
		
	}
	
	void phraseQuery(ArrayList<ArrayList<Integer>> ans)
	{
		ArrayList<Integer> as;
		ArrayList<ArrayList<Integer>> as1=new ArrayList<ArrayList<Integer>>() ;
		ArrayList<ArrayList<Integer>> as2=new ArrayList<ArrayList<Integer>>() ;
		for(int i=0;i<ans.size();i++)
		{
			as=ans.get(i);
			if(as.get(2)-as.get(1)==1)
			{
				as1.add(as);
			}
		}
		if(ans1.isEmpty())
		{
			for(int i=0;i<as1.size();i++)
			{
				ArrayList<Integer> sd=as1.get(i);
				as2.add(sd);
			}
			ans1=as2;
			
		}
		else
		{
			for(int i=0;i<ans1.size();i++)
			{
				ArrayList<Integer> sd=ans1.get(i);
				for(int j=0;j<as1.size();j++)
				{
					ArrayList<Integer> sd1=as1.get(j);
					if(sd.get(0)==sd1.get(0)&&sd.get(2)==sd1.get(1))
					{
						
						ArrayList<Integer> sc=new ArrayList<Integer> ();
						sc.add(sd.get(0));
						sc.add(sd.get(1));
						sc.add(sd.get(2)+1);
						as2.add(sc);
						//ans1.set(i,sc );
					}
				}
				
			}
			ans1=as2;
		}
		//System.out.println(ans1);
	}
	
	
	
}

class SpellingCorrection
{
	int EditDistance(String s1,String s2)
	{
		int M[][]=new int[s1.length()+1][s2.length()+1];
		for(int i=0;i<s1.length()+1;i++)
		{
			M[i][0]=i;
			 
		}
		for(int i=0;i<s2.length()+1;i++)
		{
			M[0][i]=i;
			
		}
		
		for(int i=1;i<=s1.length();i++)
		{
			for(int j=1;j<=s2.length();j++)
			{
				if(s1.charAt(i-1)==s2.charAt(j-1))
				{
					M[i][j]=(int)Math.min(M[i-1][j-1],Math.min(M[i-1][j]+1, M[i][j-1]+1));
				}
				else
				{
					M[i][j]=(int)Math.min(M[i-1][j-1]+1,Math.min(M[i-1][j]+1, M[i][j-1]+1));
				}
			}
		}
		return M[s1.length()][s2.length()];
		
	}					

}





class Indexing {

	
	public static void main(String []args)
	{
		IndexFormation1 obj=new IndexFormation1();
 		
	String []s="ajj.@@... b     nn".split("[@]+|[!]|[.]+|[ ]+" );
	 
	obj.generateTokens();
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	try {
	
	System.out.println(obj.fmap);
	
	TreeMap<String,HashMap<Integer,ArrayList<Integer>>> tmap=new TreeMap<String,HashMap<Integer,ArrayList<Integer>>>();
	 
		tmap.putAll(obj.hm);
		
		for(Map.Entry<String,HashMap<Integer,ArrayList<Integer>>> entry:tmap.entrySet())
		{
		 	//System.out.println(entry.getKey()+" "+entry.getValue());
						
		//	
			
		}
		 TreeSet<String> tset1=new TreeSet<String>();
		 TreeSet<String> tset2=new TreeSet<String>();
		 Set<String> s11=obj.hm.keySet();
		 for(String i1:s11)
		 {
			 tset1.add(i1);
			 StringBuilder b=new StringBuilder();
			 b.append(i1);
			 b.reverse();
			 tset2.add(b.toString());
		 }
		  while(true) {
	System.out.println("enter the type of query: 0 for phrase , 1 for proximity, 2 for wildcard query, 3 for spelling correction , 4 for calculating edit distance between two input");
	
	int ty=Integer.parseInt(br.readLine());
	String str[]=br.readLine().split(" ");
	 
	for(int i=0;i<str.length;i++)
	{
		str[i]=str[i].toLowerCase();
		if(obj.hm.containsKey(str[i]))
		System.out.println(str[i]+" "+obj.hm.get(str[i]));
		
	}
	if(ty==0) {
		if(str.length>1) {
	for(int i=0;i<str.length-1;i++)
	{
		ArrayList<ArrayList<Integer>> asa;
		
		  asa=obj.processQuery(str[i],1,str[i+1]);
		
		//System.out.println(asa);
		if(asa==null)
			break;
		obj.phraseQuery(asa);
		
	}
	HashSet<Integer> hs1=new HashSet<Integer>();
	HashSet<ArrayList<Integer>> hs2=new HashSet<ArrayList<Integer>>();
	 
		for(ArrayList<Integer> aa1: obj.ans1)
		{
			hs1.add(aa1.get(0));
		}
		 
	System.out.println("detailed result, first means document number ,second and third means Position");
	System.out.println(obj.ans1);
	System.out.println("present in document");
		for(Integer q1:hs1)
		{
			System.out.println(obj.fmap.get(q1));	
		}
	}
		if(str.length==1)
		{
			if(obj.hm.containsKey(str[0]))
			{
				//System.out.println(obj.hm.get(str[0]));
			}
		}
		obj.ans1=new ArrayList<ArrayList<Integer>>();
	}	
	else if(ty==1)
	{
		
		ArrayList<ArrayList<Integer>> asa=obj.processQuery(str[0],Integer.parseInt(str[1]),str[2]);
		HashSet<Integer> hs1=new HashSet<Integer>();
		for(ArrayList<Integer> aa1: asa)
		{
			hs1.add(aa1.get(0));
		}
		System.out.println("detailed result, first means document number ,second and third means Position");
		System.out.println(asa);
		System.out.println("present in document");
		for(Integer q1:hs1)
		{
			System.out.println(obj.fmap.get(q1));	
		}
		
	}
	else if(ty==2)
	{
		HashSet<String> q1=new HashSet<String>();
		str[0]=str[0].toLowerCase();
		String s1="",s2="";
		
		
		s1=str[0].substring(0,str[0].indexOf('*'));
		s2=str[0].substring(str[0].lastIndexOf('*')+1);
		StringBuilder b=new StringBuilder();
		 b.append(s2);
		 b.reverse();
		Set<String> a1=(TreeSet)tset1.tailSet(s1);
		Set<String> a2=(TreeSet)tset2.tailSet(b.toString());
		Set<String> a3=new TreeSet();
		Set<String> a4=new TreeSet();
		for(String i1:a2)
		{
			StringBuilder b1=new StringBuilder();
			 b1.append(i1);
			 b1.reverse();
			 a3.add(b1.toString());
		}
		a1.retainAll(a3);
		s1="["+s1+"]";
		
		String co="[a-z0-9A-Z]*",f="";
		String []val=new String[100];int k=0,l=0;
		for(int i=0;i<str[0].length();i++)
		{
			if(str[0].charAt(i)=='*')
			{
				val[k++]=str[0].substring(l,i);
				l=i+1;
				f=f+val[k-1]+co;
			}
		}
		val[k++]=str[0].substring(l,str[0].length());
		f=f+val[k-1]+co;
		for(String i1:a1)
		{
			if(i1.matches(f))
			{
				a4.add(i1);
			}
		}
		System.out.println("TERMS FOR WILDCARD ARE: "+ a4);
		System.out.println("document retrived are:");
		for(String i1:a4)
		{
			
			Set<Integer> sq=obj.hm.get(i1).keySet();
			System.out.print(i1+"= ");
			for(Integer as:sq)
			{
				System.out.print(obj.fmap.get(as)+", ");
			}
			System.out.println();
		}
		
		 
		
	}
	else if(ty==3)
	{
		 
		SpellingCorrection obj1=new SpellingCorrection();
		int occur=0;
		TreeSet<String> asa=new TreeSet<String>();
		Set<String> a44=obj.hm.keySet();
		for(String df:a44)
			asa.add(df);
		if(asa.contains(str[0]))
		{
			occur=obj.hm.get(str[0]).size();
		}
		System.out.println("OCCURANCE OF STRING "+str[0]+" IS: "+occur);
		HashMap<Integer,HashSet<String>> ans1=new HashMap<Integer,HashSet<String>>();
		ArrayList<Integer> h2=new ArrayList<Integer> ();
		
		//if occur is more than 3 then spelling is correct
		if(occur<=3)
		{
			for(String i1:asa)
			{
				int ans=obj1.EditDistance(str[0],i1);
				 
				if(ans1.containsKey(ans))
				{
					HashSet<String> h1=ans1.get(ans);
					h1.add(i1);
				}
				else
				{
					HashSet<String> h1=new HashSet<String> ();
					h1.add(i1);
					ans1.put(ans, h1);
					h2.add(ans);
				}
			}
			//System.out.println(h2);
			Collections.sort(h2);
			System.out.println("ALL POSSIBLE EDIT DISTANCE:"+h2);
			System.out.println("LEAST EDIT DISTANCE="+h2.get(0));
			System.out.println("first three least edit distance is:");
			
			System.out.println("EDIT DISTANCE="+h2.get(0)+":TERM="+ans1.get(h2.get(0)));
			
			System.out.println("EDIT DISTANCE="+h2.get(1)+":TERM="+ans1.get(h2.get(1)));
			
		//	System.out.println("EDIT DISTANCE="+h2.get(2)+":TERM="+ans1.get(h2.get(2)));
			
			
			
			
			
			HashSet<String> s1=ans1.get(h2.get(0));
			HashSet<String> s2=ans1.get(h2.get(1));
			HashSet<String> s3=ans1.get(h2.get(2));
			HashSet<String> s4=new HashSet<String>();	
			
			
			//2 gram
		ArrayList<String> gr=new ArrayList<String>();
			Set<String> sd=new HashSet<String>();
			Set<String> sd1=new HashSet<String>();
			Set<String> sd2=new HashSet<String>();
			for(int j=0;j<=(str[0].length()/2);j++)
			{	 
				sd.add(str[0].substring(j,j+2));
				
				
				
			}
			
			for(String tem:s1)
			{
				int u=0;
				for(int j=0;j<=(tem.length()/2);j++)
				{
					sd1.add(tem.substring(j,j+2));
					sd2.add(tem.substring(j,j+2));
					
				}
				sd2.addAll(sd);
				sd1.retainAll(sd);
				
				
				
			}
			
			
			
		}
		 
		 
		else
		{
			System.out.println("MORE THAN 3 DOCUMENT RETRIVED SO CORRECT SPELLING");
		}
		
		
	}
	else if(ty==4)
	{
		SpellingCorrection obj1=new SpellingCorrection();
		int ans=obj1.EditDistance(str[0],str[1]);
		System.out.println("ANSWER= "+ans);
	}
	//obj.processQuery("devbrat",1,"anand");
	}
	
	//System.out.println(obj.hm.get("devbrat"));
	
	}catch(Exception e)
	{
		System.out.println(e);
	}
	
	}
	
	
}
