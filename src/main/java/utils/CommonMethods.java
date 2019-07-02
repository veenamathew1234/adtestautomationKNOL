package utils;

import java.util.Map;

public class CommonMethods extends StartUp {

	ObjectFactory objmap;
	
	public CommonMethods(){
		System.out.println("Inside journey page constructor");
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/main/java/uiMap/CommonErrors.properties");
		//Map<String,Object> DataObj=st.beforeClass("testData.json");
	}
	
	
	public String[][] checkErrorComponents() throws Exception
	{
		System.out.println("checkErrorComponents");
		String[][] result={
				{"",""},
				{"",""},
				{"",""},
				{"",""},
				{"",""}
		};
		int i=0,j=0;
		if(driver.findElements(objmap.getLocator("lbl_errorcom1")).size()!=0)
		{
			System.out.println("i in 1="+i);
			System.out.println("inside error 1");
			result[i][0]+="false";
			result[i][1]+="error message1";
			i++;
			System.out.println("result in 1="+result);
		}
		if(driver.findElements(objmap.getLocator("lbl_errorcom2")).size()!=0)
		{
			System.out.println("i in 2="+i);
			System.out.println("inside error 2");
			result[i][0]="false";
			result[i][1]="error message2";
			i++;
			System.out.println("result in 2="+result);
		}
		if(driver.findElements(objmap.getLocator("lbl_errorcom3")).size()!=0)
		{
			//System.out.println("result in 3="+result);
			System.out.println("i in 3="+i);
			System.out.println("inside error 3");
			result[i][0]+="false";
			result[i][1]+="error message3";
			//System.out.println("hello b4");
			i++;
		//	System.out.println("i in 3 post="+i);
			//System.out.println("hi");
		//	System.out.println("result in 3 post="+result);
//			for (String[] x : result)
//			{
//			   for (String y : x)
//			   {
//			        System.out.print(y + " ");
//			   }
//			   System.out.println();
//			}
		}
		if(driver.findElements(objmap.getLocator("lbl_errorcom4")).size()!=0)
		{
			System.out.println("i in 4="+i);
			System.out.println("inside error 4");
			result[i][0]="false";
			result[i][1]="error message4";
			i++;
			System.out.println("result in 4="+result);
		}
		if(driver.findElements(objmap.getLocator("lbl_errorcom5")).size()!=0)
		{
			System.out.println("i in 5="+i);
			System.out.println("inside error 5");
			result[i][0]="false";
			result[i][1]="error message5";
			i++;
			System.out.println("result in 5="+result);
		}
		//System.out.println("result="+result);
		return result;
	}

}
