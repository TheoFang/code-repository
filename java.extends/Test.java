class Person{
	String name;
	String age;
	public Person(String name,String age){
		this.name = name;
		this.age = age;
	}
	public void showInfo(){
		System.out.print(this.name + " ");
		System.out.print(this.age + " ");
	}
}

class Student extends Person{
	int StuID;
	public Student(String name,String age, int StuID){
		super(name,age);
		this.StuID = StuID;
	}
	public void showInfo(){
		super.showInfo();
		System.out.print(this.StuID);
	}
}
public class Test{
	public static void main(String[] args) {
		Person P = new Person("zhangsan","22");
		Student S = new Student("lisi","21",1701);
		P.showInfo();
		System.out.println("");
		S.showInfo();
	}
}