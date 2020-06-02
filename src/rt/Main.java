package rt;
import java.io.BufferedReader;
import javafx.scene.control.*;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType; 

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.scene.text.Text;  
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.sun.prism.paint.Color;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;  

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javafx.scene.layout.*;
import javafx.scene.image.*;
public class Main extends Application {
   static int status=-1;
   static TextArea ta;
	static String obj11;
    @Override
    public void start(Stage primaryStage) throws Exception {
  
    	
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);
        ArrayList<Hyperlink> hyperlink=new ArrayList<Hyperlink>();
        RadioButton pos_rad,pro_rad,wild_rad,spell_rad;
        pos_rad=new RadioButton("PHRASE SEARCH");
        pro_rad=new RadioButton("PROXIMITY SEARCH");
        wild_rad=new RadioButton("WILD CARD QUERY");
        spell_rad=new RadioButton("CHECK POSSIBLE CORRECT SPELLING OF INCORRECT WORD");
        ToggleGroup togglegroup=new ToggleGroup();
        pos_rad.setToggleGroup(togglegroup);
        pro_rad.setToggleGroup(togglegroup);
        wild_rad.setToggleGroup(togglegroup);
        spell_rad.setToggleGroup(togglegroup);
        pos_rad.setSelected(true);
        TextArea textArea = new TextArea();
        ta=textArea;
        VBox hylvbox = new VBox();
        textArea.setMinHeight(100);
        Text t1=new Text("DETAILS APPEARS BELOW");
        t1.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        Text t2=new Text("ENTER YOUR QUERY");
        t2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t3=new Text("CHOOSE SEARCH MODE:  ");
        t3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
         HBox rad=new HBox(t3,pos_rad,pro_rad,wild_rad,spell_rad);
         pos_rad.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
         pro_rad.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
         wild_rad.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
         spell_rad.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
        //textArea.setMaxHeight(100);
        textArea.setFont(Font.font(18));
        textArea.setPromptText("DETAILS APPEARS HERE");
        TextArea textArea1 = new TextArea();
        textArea1.setMinHeight(50);
       textArea1.setMaxHeight(70);
        
        textArea1.setFont(Font.font(18));
        textArea1.setPromptText("Enter your query here");  
     //   textArea1.getParent().requestFocus(); 
        Button button = new Button("CLICK TO SELECT DIRECTORY FOR SEARCHING");
        Button button1 = new Button("CLICK TO  SEARCH");
         
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        button1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));		
        
        
        button.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	  textArea.setText("PLEASE WAIT, INDEX IS BEING CREATED");
                File dir = directoryChooser.showDialog(primaryStage);
              
                if (dir != null) {
                    textArea.setText("CHOSEN DIRECTORY: "+dir.getAbsolutePath()+"\n"+"INDEX CREATED SUCESSFULLY, NOW YOU CAN SEARCH YOUR QUERY");
                    obj11=dir.getAbsolutePath();
                   
                    /////////////////////////////////////////////////////////////
                    
                    
                	IndexFormation1 obj=new IndexFormation1();
                 		
                	String []s="ajj.@@... b     nn".split("[@]+|[!]|[.]+|[ ]+" );
                	 
                	obj.generateTokens();
                	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                	try {
                	
                	System.out.println(obj.fmap);
                	
                	TreeMap<String,HashMap<Integer,ArrayList<Integer>>> tmap=new TreeMap<String,HashMap<Integer,ArrayList<Integer>>>();
                	//TreeMap<String,HashSet<Integer>> sort=new TreeMap<String,HashSet<Integer>>();
                		//sort.putAll(hm);
                		tmap.putAll(obj.hm);
                		
                		for(Map.Entry<String,HashMap<Integer,ArrayList<Integer>>> entry:tmap.entrySet())
                		{
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
                		  
                		  {
                	System.out.println("enter the type of query: 0 for phrase , 1 for proximity, 2 for wildcard query, 3 for spelling correction , 4 for calculating edit distance between two input");
                	status=1;
                			  button1.setOnAction(new EventHandler<ActionEvent>() {
                		        	 
                		            @Override
                		            public void handle(ActionEvent event) {
                		            	 
                		            	 
                		            	String ss=textArea1.getText().toString();
                		        	
                		            	
                	String ss1=textArea1.getText().toString(); 
                 	int ty=0;
                	if(pos_rad.isSelected())
                	{
                		ty=0;
                	}
                	else if(pro_rad.isSelected())
                	{
                		ty=1;
                	}
                	else if(wild_rad.isSelected())
                	{
                		ty=2;
                	}
                	else if(spell_rad.isSelected())
                	{
                		ty=3;
                	}
                	
                //	int ty=Integer.parseInt(ss2[0]);
                	String str[]=ss1.split(" ");
                	 
                	for(int i=0;i<str.length;i++)
                	{
                		str[i]=str[i].toLowerCase();
                		if(obj.hm.containsKey(str[i]))
                		System.out.println(str[i]+" "+obj.hm.get(str[i]));
                		
                	}
                	if(ty==0) {
                		 String asas="";
                		//hyperlink.clear();
                		 for(int jj=0;jj<hyperlink.size();jj++)
                         {
                 			hyperlink.get(jj).setFont(Font.font(18));
                         	hylvbox.getChildren().remove(hyperlink.get(jj));
                         	
                         }
                 		hyperlink.clear();
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
                			hyperlink.add(new Hyperlink(obj.fmap.get(q1)));
                			asas=asas+"\n"+obj.fmap.get(q1);
                		}
                		
                	}
                		if(str.length==1)
                		{
                			if(obj.hm.containsKey(str[0]))
                			{
                				HashMap<Integer,ArrayList<Integer>>  s12=obj.hm.get(str[0]);
                				Set<Integer> s11=s12.keySet();
                				for(Integer q1:s11)
                        		{
                        			System.out.println(obj.fmap.get(q1));	
                        			hyperlink.add(new Hyperlink(obj.fmap.get(q1)));
                        			asas=asas+"\n"+obj.fmap.get(q1);
                        		}
                				//System.out.println(obj.hm.get(str[0]));
                			}
                		}
                		textArea.setText(asas);
                		hyperlinkHandler objhyl=new hyperlinkHandler() ;
                		for(int jj=0;jj<hyperlink.size();jj++)
                        {
                			hyperlink.get(jj).setFont(Font.font(18));
                        	hylvbox.getChildren().add(hyperlink.get(jj));
                        	hyperlink.get(jj).setOnAction(objhyl);
                         }
                        
                		obj.ans1=new ArrayList<ArrayList<Integer>>();
                	}	
                	else if(ty==1)
                	{
                		try {
                		//hyperlink.clear();
                			for(int jj=0;jj<hyperlink.size();jj++)
                            {
                    			hyperlink.get(jj).setFont(Font.font(18));
                            	hylvbox.getChildren().remove(hyperlink.get(jj));
                            	
                            }
                    		hyperlink.clear();
                		ArrayList<ArrayList<Integer>> asa=obj.processQuery(str[0],Integer.parseInt(str[1]),str[2]);
                		HashSet<Integer> hs1=new HashSet<Integer>();
                		 String asas="";
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
                			hyperlink.add(new Hyperlink(obj.fmap.get(q1)));
                			asas=asas+"\n"+obj.fmap.get(q1);
                		}
                		textArea.setText(asas);
                		hyperlinkHandler objhyl=new hyperlinkHandler() ;
                		for(int jj=0;jj<hyperlink.size();jj++)
                        {
                			hyperlink.get(jj).setFont(Font.font(18));
                        	hylvbox.getChildren().add(hyperlink.get(jj));
                         	hyperlink.get(jj).setOnAction(objhyl);
                        }
                		}
                		catch(Exception e)
                		{
                			
                		}
                	}
                	else if(ty==2)
                	{
                		 
                		//hyperlink.clear();
                		for(int jj=0;jj<hyperlink.size();jj++)
                        {
                			hyperlink.get(jj).setFont(Font.font(18));
                        	hylvbox.getChildren().remove(hyperlink.get(jj));
                        	
                        }
                		hyperlink.clear();
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
                		String asas="";
                		HashSet<String> uniquechecker=new HashSet<String>();
                		for(String i1:a4)
                		{
                			
                			Set<Integer> sq=obj.hm.get(i1).keySet();
                			
                			System.out.print(i1+"= ");
                			for(Integer as:sq)
                			{
                				System.out.print(obj.fmap.get(as)+", ");
                				if(!uniquechecker.contains(obj.fmap.get(as)))
                				{
                					System.out.print(obj.fmap.get(as)+", ");
                					asas=asas+obj.fmap.get(as)+"\n";
                					uniquechecker.add(obj.fmap.get(as));
                					hyperlink.add(new Hyperlink(obj.fmap.get(as)));
                				}
                				
                			}
                			 
                			System.out.println();
                		}
                		textArea.setText(asas);
            			hyperlinkHandler objhyl=new hyperlinkHandler() ;
            			for(int jj=0;jj<hyperlink.size();jj++)
                        {
                			
                			//System.out.println(" "+jj+" "+hyperlink.get(jj).getText());
                		if(!hylvbox.getChildren().contains(hyperlink.get(jj)))
                		{
                			hyperlink.get(jj).setFont(Font.font(18));
                            hylvbox.getChildren().add(hyperlink.get(jj));
                            hyperlink.get(jj).setOnAction(objhyl);
            				 
                		}
                        }
                		 
                		
                	}
                	else if(ty==3)
                	{
                		for(int jj=0;jj<hyperlink.size();jj++)
                        {
                			hyperlink.get(jj).setFont(Font.font(18));
                        	hylvbox.getChildren().remove(hyperlink.get(jj));
                        	
                        }
                		hyperlink.clear(); 
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
                			String asas="";
                			String a11,a12,a13,a14;
                			Collections.sort(h2);
                			System.out.println(a11="ALL POSSIBLE EDIT DISTANCE:"+h2);
                			System.out.println(a12="LEAST EDIT DISTANCE="+h2.get(0));
                			System.out.println("first three least edit distance is:");
                			
                			System.out.println(a13="EDIT DISTANCE="+h2.get(0)+":TERM="+ans1.get(h2.get(0)));
                			
                			System.out.println(a14="EDIT DISTANCE="+h2.get(1)+":TERM="+ans1.get(h2.get(1)));
                			asas=a11+"\n"+a12+"\n"+a13+"\n"+a14;
                			textArea.setText(asas);
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
                			System.out.println("MORE THAN 3 DOCUMENT RETRIEVED SO CORRECT SPELLING");
                			textArea.setText("MORE THAN 3 DOCUMENT RETRIEVED SO CORRECT SPELLING");
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
                		        });
                	}
                	        
                	//+++++++++++++
                	  
                	}catch(Exception e)
                	{
                		System.out.println(e);
                	}
                	  
                    ////////////////
                       
                } else {
                    textArea.setText(null);
                     
                }
            	 
            }
              
        });
        VBox root1 = new VBox();
        root1.setPadding(new Insets(15));
        root1.setSpacing(10);
        VBox root = new VBox();
        //root.setPadding(new Insets(10));
        root.setSpacing(5);
         
        root.getChildren().addAll(t1,textArea, button,t2,rad,textArea1,button1);
        VBox root2 = new VBox();
        Text text=new Text("SEARCH RESULTS(CLICK LINK TO OPEN RETRIEVED FILE):");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
      root2.getChildren().add(text);     
        root.setBackground(background("file:///D:/eclipse%20development/rt/src/rt/light red.png"));
         root2.setBackground(background("file:///D:/eclipse%20development/rt/src/rt/light red.png")); 
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hylvbox); 
         root1.getChildren().addAll(root,root2,scrollPane);
        Scene scene = new Scene(root1, 700, 700);
        
        primaryStage.setTitle("ADVANCED LOCAL SEARCH ENGINE ( DEVELOPED BY DEVBRAT ANAND )");
        primaryStage.getIcons().add(new Image("file:///D:/eclipse%20development/rt/src/rt/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Directories");
          
        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        status=0;
        
    }
    static Background background(String path)
    {
    	Image image1 = new Image(path, true);
        BackgroundImage backgroundimage = new BackgroundImage(image1,  
                BackgroundRepeat.REPEAT,  
                BackgroundRepeat.REPEAT,  
                BackgroundPosition.DEFAULT,  
                   BackgroundSize.DEFAULT); 
        Background background = new Background(backgroundimage); 
        return background;
    }
    public static void main(String []args)
	{
		launch(args);
	}
     
}
class hyperlinkHandler implements EventHandler<ActionEvent>
{

	@Override
	public void handle(ActionEvent event) {
		 try {
		Desktop.getDesktop().open(new File(((Hyperlink)event.getSource()).getText()));
		 }
		 catch(Exception e)
		 {
			e.printStackTrace(); 
		 }
	}
	
}