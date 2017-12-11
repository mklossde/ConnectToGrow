package test;

import java.util.Arrays;

import org.openon.simpleui.components.UITypes.Tree;

public class TreeSetDemo {

   public static void main(String[] args) {

//	   Tree t=new Tree();
//	   t.add("1").add("2").add("3");
//	   Tree sub=t.getTreeOf("eins/zwei", false,true).add("xxx");
//	   sub.add("yyy");
//	   Tree sub2=sub.getTreeOf("drei", false,true).add("111");
//	   Tree sub3=sub2.getTreeOf("/vier", false,true).add("222");
//	   t.getTreeOf("eins/zwei", false,true).add("zzz");
//	   	   
//	   System.out.println(t);
	   
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("eins/zwei/f")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("/eins/zwei/f")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("eins/zwei/")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("eins/zwei/f")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("/")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("x/")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("x/f")));
	   System.out.println("x:"+Arrays.toString(Tree.splitTreeFile("f")));
   }


}
