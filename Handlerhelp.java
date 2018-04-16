package castle;

public class Handlerhelp extends Handler {
	


	@Override
	public void docmd(String str) {
		System.out.print("迷路了吗？你可以做的命令有：go bye help roomnum");
        System.out.println("如：\tgo east");
	}	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
